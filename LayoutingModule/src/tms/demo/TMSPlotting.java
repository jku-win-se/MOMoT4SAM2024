package tms.demo;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import at.ac.tuwien.big.momot.util.MomotUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import tinycc.Component;
import tinycc.Connector;
import tinycc.Model;

public class TMSPlotting {
   public static String addNewlineBetweenLowerAndUpper(final String str) {
      final StringBuilder result = new StringBuilder();
      int lastNewlineIndex = -1; // Start from -1 to handle the beginning of the string

      for(int i = 0; i < str.length() - 1; i++) {
         result.append(str.charAt(i));
         if(Character.isLowerCase(str.charAt(i)) && Character.isUpperCase(str.charAt(i + 1))) {
            // Check the length of the substring from the last newline (or beginning) to the current position
            if(i - lastNewlineIndex >= 8) {
               result.append('\n');
               lastNewlineIndex = i;
            }
         }
      }
      result.append(str.charAt(str.length() - 1)); // Append the last character

      return result.toString();

   }

   private static String getBlockLabel(final Component b) {
      final String l = b.getName();

      return l + " :"
            + addNewlineBetweenLowerAndUpper(l + " :" + TMSProcessors.componentTypeOf(b)).substring(l.length() + 2)
            + "\n<<component>>";
   }

   public static void plotChangeableLayoutsAsGraph(final String modelsPath, final List<String> ignoreComponentTypes) {
      final HenshinResourceSet set = new HenshinResourceSet();
      final File modelDir = new File(modelsPath);
      for(final File modelFile : modelDir.listFiles()) {
         final EGraph graph = MomotUtil.loadGraph(set, modelFile.getAbsolutePath());
         final Model m = (Model) graph.getRoots().get(0);

         final List<Node> nodes = new ArrayList<>();

         for(final Connector c : m.getConnectors()) {
            // c.getEnds().stream().filter(e -> e.getPort().getDirection() == FlowDirection.IN).findFirst();
            // if(!c.getName().contains("#to#")) {
            // continue;
            // }
            final String[] fromTo = c.getName().contains("#to#") ? c.getName().split("#to#") : c.getName().split("->");

            final String fromType = TMSProcessors.componentTypeOf(TMSProcessors.componentByName(m, fromTo[0]));
            final String toType = TMSProcessors.componentTypeOf(TMSProcessors.componentByName(m, fromTo[1]));

            if(ignoreComponentTypes.contains(fromType) || ignoreComponentTypes.contains(toType)) {
               continue;
            }

            nodes.add(node(getBlockLabel(TMSProcessors.componentByName(m, fromTo[0])))
                  .link(to(node(getBlockLabel(TMSProcessors.componentByName(m, fromTo[1]))))));

         }
         final Graph g = graph("example1").graphAttr().with(Rank.dir(LEFT_TO_RIGHT))

               .graphAttr().with("nodesep", 0.13).graphAttr().with("ranksep", 0.13) // Decrease space between ranks
               .nodeAttr().with(Font.name("arial")).nodeAttr().with("shape", "rectangle").nodeAttr()
               .with("margin", "0.0,0.0") // Decrease padding inside nodes
               .nodeAttr().with(attr("shape", "rectangle")).nodeAttr().with(Font.name("arial")).linkAttr()
               .with("class", "link-class").with(nodes);

         try {
            Graphviz.fromGraph(g).width(2100).render(Format.PNG)
                  .toFile(new File("output/visuals/" + modelFile.getName() + ".png"));
         } catch(final IOException e1) {
            e1.printStackTrace();
         }
      }

   }
}
