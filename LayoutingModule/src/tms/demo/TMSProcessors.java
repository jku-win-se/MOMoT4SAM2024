package tms.demo;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.util.EcoreUtil;

import tinycc.Component;
import tinycc.Connector;
import tinycc.ConnectorEnd;
import tinycc.FlowDirection;
import tinycc.Model;
import tinycc.Port;
import tinycc.TinyccPackage;

public class TMSProcessors {

   protected static Component componentById(final Model m, final String id) {
      return m.getComponents().stream().filter(c -> c.getName().compareTo(id) == 0).findFirst().get();
   }

   protected static Component componentByName(final Model m, final String id) {
      return findComponentRec(m, id, m.getComponents());
   }

   protected static Component componentByType(final Model m, final String type) {
      return m.getComponents().stream().filter(c -> componentTypeOf(c).compareTo(type) == 0).findFirst().get();
   }

   static String componentTypeOf(final Component c) {
      return c.getProperties().stream().filter(p -> p.getName().compareTo("type") == 0).findFirst().get().getValue();
   }

   public static void connectDanglingOutsToComponent(final Model m, final String macroComponentId,
         final String componentId) {
      final Component to = componentByName(m, componentId);
      final Component changeable = m.getComponents().stream().filter(c -> c.getName().compareTo(macroComponentId) == 0)
            .findFirst().get();

      final List<Component> danglingComps = changeable.getComponents().stream()
            .filter(c -> outPortOf(c) != null && connectionToPort(m, outPortOf(c)) == null)
            .collect(Collectors.toList());

      for(final Component c : danglingComps) {
         final EClassifier cClassifier = TinyccPackage.eINSTANCE.getEClassifier("Connector");
         final EClassifier ceClassifier = TinyccPackage.eINSTANCE.getEClassifier("ConnectorEnd");

         final Connector addedConn = (Connector) EcoreUtil.create((EClass) cClassifier);
         final ConnectorEnd e1 = (ConnectorEnd) EcoreUtil.create((EClass) ceClassifier);
         final ConnectorEnd e2 = (ConnectorEnd) EcoreUtil.create((EClass) ceClassifier);
         e1.setPort(outPortOf(c));
         e2.setPort(inPortOf(to));
         addedConn.setName(c.getName() + "#to#" + to.getName());
         addedConn.getEnds().addAll(List.of(e1, e2));

         m.getConnectors().add(addedConn);
      }
   }

   // public static void postProcessorSuite(final String modelsPath) {
   // ModelPackage.eINSTANCE.eClass();
   //
   // final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
   // reg.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
   //
   // final ResourceSet resSet = new ResourceSetImpl();
   //
   // final File dir = new File(modelsPath);
   //
   // // Get all .xmi files in the directory
   // final File[] files = dir.listFiles((d, name) -> name.endsWith(".xmi"));
   //
   // if(files != null) {
   // for(final File file : files) {
   // // Get the resource for each file
   // final Resource resource = resSet.getResource(URI.createFileURI(file.getAbsolutePath()), true);
   //
   // // Get the first model element and cast it to the right type
   // if(!resource.getContents().isEmpty()) {
   // final EObject rootElement = resource.getContents().get(0);
   //
   // // Example: Modify the model
   // if(rootElement instanceof MyRootElement) {
   // final MyRootElement root = (MyRootElement) rootElement;
   // System.out.println("Original Name: " + root.getName());
   // root.setName("NewName_" + file.getName());
   // System.out.println("Updated Name: " + root.getName());
   // }
   //
   // // Save the resource
   // try {
   // resource.save(null);
   // } catch(final IOException e) {
   // e.printStackTrace();
   // }
   // }
   // }
   // } else {
   // System.out.println("No .xmi files found in the directory.");
   // }
   // }

   private static SimpleEntry<Connector, ConnectorEnd> connectionToPort(final Model m, final Port p) {
      return m.getConnectors().stream().map(c -> {
         for(final ConnectorEnd end : c.getEnds()) {
            if(end.getPort() == p) {
               return new AbstractMap.SimpleEntry<>(c, end);
            }
         }
         return null;
      }).filter(e -> e != null).findFirst().orElse(null);
   }

   public static void connectToUniqueType(final Model m, final String componentId, final String type) {
      final Component connect = componentById(m, componentId);
      final Component to = selectedComponentByType(m, type);
      final Component changeable = m.getComponents().stream().filter(c -> c.getName().compareTo("changeable") == 0)
            .findFirst().get();

      changeable.getComponents().add(connect);
      m.getComponents().remove(connect);

      final Port toOutPort = outPortOf(to);

      final EClassifier cClassifier = TinyccPackage.eINSTANCE.getEClassifier("Connector");
      final EClassifier ceClassifier = TinyccPackage.eINSTANCE.getEClassifier("ConnectorEnd");

      final Connector addedConn = (Connector) EcoreUtil.create((EClass) cClassifier);
      final ConnectorEnd e1 = (ConnectorEnd) EcoreUtil.create((EClass) ceClassifier);
      final ConnectorEnd e2 = (ConnectorEnd) EcoreUtil.create((EClass) ceClassifier);
      e1.setPort(toOutPort);
      e2.setPort(inPortOf(connect));
      addedConn.setName(to.getName() + "#to#" + connect.getName());
      addedConn.getEnds().addAll(List.of(e1, e2));

      m.getConnectors().add(addedConn);
   }

   private static Component findComponentRec(final Model m, final String componentId,
         final List<Component> currentComponents) {
      for(final Component c : currentComponents) {
         if(c.getName().compareTo(componentId) == 0) {
            return c;
         }
         final Component found = findComponentRec(m, componentId, c.getComponents());
         if(found != null) {
            return found;
         }
      }
      return null;
   }

   public static Port inPortOf(final Component c) {
      return c.getPorts().stream().filter(p -> p.getDirection() == FlowDirection.IN).findFirst().orElse(null);
   }

   private static Port outPortOf(final Component c) {
      return c.getPorts().stream().filter(p -> p.getDirection() == FlowDirection.OUT).findFirst().orElse(null);
   }

   public static void placeBehindUniqueType(final Model m, final String componentId, final String type) {
      final Component place = componentById(m, componentId);
      final Component behind = selectedComponentByType(m, type);
      final Component changeable = m.getComponents().stream().filter(c -> c.getName().compareTo("changeable") == 0)
            .findFirst().get();

      changeable.getComponents().add(place);
      m.getComponents().remove(place);

      final Port behindOutPort = outPortOf(behind);
      final SimpleEntry<Connector, ConnectorEnd> ceOutPair = connectionToPort(m, behindOutPort);
      if(ceOutPair != null) {
         final Connector conn = ceOutPair.getKey();
         final ConnectorEnd end = ceOutPair.getValue();

         conn.setName(place.getName() + "#to#" + conn.getName().split("#to#")[1]);
         end.setPort(outPortOf(place));
      }
      final EClassifier cClassifier = TinyccPackage.eINSTANCE.getEClassifier("Connector");
      final EClassifier ceClassifier = TinyccPackage.eINSTANCE.getEClassifier("ConnectorEnd");

      final Connector addedConn = (Connector) EcoreUtil.create((EClass) cClassifier);
      final ConnectorEnd e1 = (ConnectorEnd) EcoreUtil.create((EClass) ceClassifier);
      final ConnectorEnd e2 = (ConnectorEnd) EcoreUtil.create((EClass) ceClassifier);
      e1.setPort(behindOutPort);
      e2.setPort(inPortOf(place));
      addedConn.setName(behind.getName() + "#to#" + place.getName());
      addedConn.getEnds().addAll(List.of(e1, e2));

      m.getConnectors().add(addedConn);
   }

   public static void placeInFrontOfUniqueType(final Model m, final String componentId, final String type) {
      final Component place = componentById(m, componentId);
      final Component inFrontOf = selectedComponentByType(m, type);
      final Component changeable = m.getComponents().stream().filter(c -> c.getName().compareTo("changeable") == 0)
            .findFirst().get();

      changeable.getComponents().add(place);
      m.getComponents().remove(place);

      final Port inFrontOfInPort = inPortOf(inFrontOf);
      final SimpleEntry<Connector, ConnectorEnd> ceInPair = connectionToPort(m, inFrontOfInPort);
      final Connector conn = ceInPair.getKey();
      final ConnectorEnd end = ceInPair.getValue();

      conn.setName(conn.getName().split("#to#")[0] + "#to#" + place.getName());
      end.setPort(inPortOf(place));

      final EClassifier cClassifier = TinyccPackage.eINSTANCE.getEClassifier("Connector");
      final EClassifier ceClassifier = TinyccPackage.eINSTANCE.getEClassifier("ConnectorEnd");

      final Connector addedConn = (Connector) EcoreUtil.create((EClass) cClassifier);
      final ConnectorEnd e1 = (ConnectorEnd) EcoreUtil.create((EClass) ceClassifier);
      final ConnectorEnd e2 = (ConnectorEnd) EcoreUtil.create((EClass) ceClassifier);
      e1.setPort(outPortOf(place));
      e2.setPort(inFrontOfInPort);
      addedConn.setName(place.getName() + "#to#" + inFrontOf.getName());
      addedConn.getEnds().addAll(List.of(e1, e2));

      m.getConnectors().add(addedConn);

   }

   protected static List<Component> selectedAsList(final Model m) {
      return m.getComponents().stream().filter(c -> c.getName().compareTo("changeable") == 0).findFirst().get()
            .getComponents();
   }

   protected static Component selectedComponentByName(final Model m, final String id) {
      return m.getComponents().stream().filter(c -> c.getName().compareTo("changeable") == 0).findFirst().get()
            .getComponents().stream().filter(c -> c.getName().compareTo(id) == 0).findFirst().get();
   }

   protected static Component selectedComponentByType(final Model m, final String type) {
      return m.getComponents().stream().filter(c -> c.getName().compareTo("changeable") == 0).findFirst().get()
            .getComponents().stream().filter(c -> componentTypeOf(c).compareTo(type) == 0).findFirst().get();
   }

}
