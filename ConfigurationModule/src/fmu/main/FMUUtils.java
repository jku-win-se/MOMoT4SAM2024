package fmu.main;

import at.ac.tuwien.big.momot.util.MomotUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

import fmumodel.FMU;
import fmumodel.FmumodelPackage;
import fmumodel.Input;
import fmumodel.Output;
import no.ntnu.ihb.fmi4j.importer.fmi2.Fmu;
import no.ntnu.ihb.fmi4j.modeldescription.variables.Causality;
import no.ntnu.ihb.fmi4j.modeldescription.variables.ModelVariables;
import no.ntnu.ihb.fmi4j.modeldescription.variables.TypedScalarVariable;

public class FMUUtils {
   public static void initFMUModel(final String fmuPath, final Map<String, Double> defaultValues) {
      final HenshinResourceSet set = new HenshinResourceSet();
      final File fmuFile = new File("models/FMU_skeleton.xmi");
      final EGraph graph = MomotUtil.loadGraph(set, fmuFile.getAbsolutePath());
      final FMU fmuModel = (FMU) graph.getRoots().get(0);
      Fmu f = null;
      try {
         f = Fmu.from(new File(fmuPath));
      } catch(final FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch(final IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      final EClassifier iClassifier = FmumodelPackage.eINSTANCE.getEClassifier("Input");
      final EClassifier oClassifier = FmumodelPackage.eINSTANCE.getEClassifier("Output");

      final ModelVariables mv = f.getModelDescription().getModelVariables();
      for(final TypedScalarVariable<?> v : mv.getVariables()) {
         if(v.getCausality() == Causality.INPUT) {
            final Input i = (Input) EcoreUtil.create((EClass) iClassifier);
            i.setName(v.getName());
            if(defaultValues.containsKey(v.getName())) {
               i.setValue(defaultValues.get(v.getName()));
            }
            fmuModel.getInput().add(i);
         } else if(v.getCausality() == Causality.OUTPUT) {
            final Output o = (Output) EcoreUtil.create((EClass) oClassifier);
            o.setName(v.getName());
            fmuModel.getOutput().add(o);
         }
      }
      MomotUtil.saveGraph(graph, "models/FMU.xmi");
   }
}
