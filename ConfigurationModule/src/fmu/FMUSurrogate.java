package fmu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import no.ntnu.ihb.fmi4j.importer.fmi2.CoSimulationSlave;
import no.ntnu.ihb.fmi4j.importer.fmi2.Fmu;
import no.ntnu.ihb.fmi4j.modeldescription.variables.Causality;
import no.ntnu.ihb.fmi4j.modeldescription.variables.ModelVariables;
import no.ntnu.ihb.fmi4j.modeldescription.variables.TypedScalarVariable;

public class FMUSurrogate {
   private Fmu fmu = null;
   private final CoSimulationSlave slave;
   private final Map<String, List<Double>> outputs;
   private final Map<String, Long> outputToPortMap;
   private final Map<String, Long> inputToPortMap;

   public FMUSurrogate(final String fmuPath) {
      this.outputs = new HashMap<>();
      this.outputToPortMap = new HashMap<>();
      this.inputToPortMap = new HashMap<>();

      try {
         fmu = Fmu.from(new File(fmuPath));
      } catch(final IOException e) {
         e.printStackTrace();
      }
      slave = fmu.asCoSimulationFmu().newInstance();
      final ModelVariables mv = fmu.getModelDescription().getModelVariables();
      for(final TypedScalarVariable<?> v : mv.getVariables()) {
         if(v.getCausality() == Causality.OUTPUT) {
            this.outputs.put(v.getName(), new ArrayList<>());
            this.outputToPortMap.put(v.getName(), v.getValueReference());
         } else if(v.getCausality() == Causality.INPUT) {
            this.inputToPortMap.put(v.getName(), v.getValueReference());
         }
      }

   }

   public List<Double> get(final String outPort) {
      return this.outputs.get(outPort);
   }

   public Map<String, Double> getFinalOutputs() {
      return this.outputs.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get(entry.getValue().size() - 1)));
   }

   public double getLast(final String outPort) {
      return this.outputs.get(outPort).get(this.outputs.get(outPort).size() - 1);
   }

   public void reset() {
      slave.reset();
      this.outputs.replaceAll((k, v) -> new ArrayList<>());
   }

   public void setup(final List<Double> inputValues, final List<String> inputNames) {
      slave.simpleSetup();
      slave.setupExperiment();
      slave.enterInitializationMode();

      final double[] values = inputValues.stream().mapToDouble(x -> x).toArray();

      final long[] ports = inputNames.stream().mapToLong(i -> inputToPortMap.get(i)).toArray();
      slave.writeReal(ports, values);
      slave.getWrapper().writeReal(ports, values);
      slave.exitInitializationMode();
   }

   public void simulate(final double stopAt) {
      if(!slave.doStep(0, stopAt)) {
         throw new RuntimeException("Simulation broke");
      }
      final double[] outs = new double[outputs.size()];
      final long[] outPorts = outputToPortMap.values().stream().mapToLong(l -> l).toArray();
      slave.readReal(outPorts, outs);

      int i = 0;
      for(final String outputName : outputToPortMap.keySet()) {
         this.outputs.get(outputName).add(outs[i]);
         i++;
      }

   }

   public void simulate(final double stepSize, final double stopAt) {
      double t = 0;
      while(t <= stopAt) {
         if(!slave.doStep(t, stepSize)) {
            throw new RuntimeException("Simulation broke");
         }
         final double[] outs = new double[outputs.size()];
         final long[] outPorts = outputToPortMap.values().stream().mapToLong(l -> l).toArray();
         slave.readReal(outPorts, outs);

         int i = 0;
         for(final String outputName : outputToPortMap.keySet()) {
            this.outputs.get(outputName).add(outs[i]);
            i++;
         }

         t += stepSize;
      }
   }

   public void terminate() {
      this.outputs.clear();
      slave.terminate();
      slave.close();
      fmu.close();
   }
}
