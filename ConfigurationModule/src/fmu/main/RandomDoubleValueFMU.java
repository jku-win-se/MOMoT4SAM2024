package fmu.main;

import at.ac.tuwien.big.momot.problem.unit.parameter.IParameterValue;

import java.util.Map;

import org.moeaframework.core.PRNG;

public class RandomDoubleValueFMU implements IParameterValue<Double> {

   private final Double initialValue = null;

   @Override
   public Double getInitialValue() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Double nextValue(final Map<String, Object> preceedingValues) {
      switch(preceedingValues.get("name").toString()) {
         case "BatteryVoltage":
            return PRNG.nextDouble(200, 600);
         case "InternalRes":
            return PRNG.nextDouble(.02, .5);
         case "MaxTorque":
            return PRNG.nextDouble(200, 1000);
      }
      return null;
   }

   @Override
   public String toString() {
      return super.toString();
   }
}
