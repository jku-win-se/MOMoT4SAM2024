package fmu.main;

import java.io.Serializable;
import java.util.Map;

public class SimulationResult implements Serializable {
   private static final long serialVersionUID = 88592645602456L;
   Map<String, Double> finalOutputValues;

   public SimulationResult(final Map<String, Double> finalOutputValues) {
      this.finalOutputValues = finalOutputValues;
   }

   public double getOutput(final String outputName) {
      return this.finalOutputValues.get(outputName);
   }
}
