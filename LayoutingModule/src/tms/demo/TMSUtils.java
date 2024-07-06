package tms.demo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import tinycc.Component;
import tinycc.FlowDirection;
import tinycc.Model;
import tinycc.Port;

public class TMSUtils {

   private static void clearDir(final String... dir) {
      for(final String d : dir) {
         final File dirFile = new File(d);
         for(final File f : dirFile.listFiles()) {
            if(!f.isDirectory()) {
               f.delete();
            }
         }
      }
   }

   public static void clearOutputDirs() {
      clearDir("output/models", "output/visuals", "output/solutions", "output/simulations", "output/objectives");

   }

   public static double countDistinctUsedComponents(final Model m) {
      final Component selection = m.getComponents().stream().filter(c -> c.getName().compareTo("changeable") == 0)
            .findFirst().get();

      return selection.getComponents().size();
   }

   public static double countFlows(final Model m) {
      final Component selection = m.getComponents().stream().filter(c -> c.getName().compareTo("changeable") == 0)
            .findFirst().get();
      final Component p = selection.getComponents().stream()
            .filter(c -> getComponentType(c) != null && getComponentType(c).compareTo("Pump") == 0).findFirst().get();
      final Port outPort = p.getPorts().stream().filter(port -> port.getDirection() == FlowDirection.OUT).findFirst()
            .get();
      final long flows = m.getConnectors().stream().flatMap(c -> c.getEnds().stream())
            .filter(e -> e.getPort().equals(outPort)).count();
      return flows;
   }

   private static Component getComponentByType(final Model m, final String type) {
      return m.getComponents().stream().filter(c -> getComponentType(c).compareTo(type) == 0).findFirst().get();
   }

   private static String getComponentType(final Component c) {
      return c.getProperties().stream().filter(p -> p.getName().compareTo("type") == 0).findFirst().get().getValue();
   }

   public static void rewriteToUriPath(final Path p, final String search, final String replace) {

      final Charset charset = StandardCharsets.UTF_8;

      String content = null;
      try {
         content = new String(Files.readAllBytes(p), charset);
      } catch(final IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      content = content.replaceAll(search, replace);
      try {
         Files.write(p, content.getBytes(charset));
      } catch(final IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

}
