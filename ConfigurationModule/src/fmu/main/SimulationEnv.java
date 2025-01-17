package fmu.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import fmu.FMUSurrogate;
import fmumodel.FMU;
import fmumodel.Input;
import fmumodel.Output;

public class SimulationEnv implements Serializable {

   static final long serialVersionUID = 5235136950L;

   private static FMUSurrogate f = null;
   public static int simulationTime = -1;
   public static String fmuPath = null;
   //
   // public static double calcAuxload(final double batteryVoltage, final double maxTorque) {
   // return 600 + (batteryVoltage - 340) * 2 + (maxTorque - 500) * 0.75;
   // }
   //
   // public static double calcMaxPower(final double batteryVoltage, final double internalRes) {
   // return 100000 - internalRes * 100000 + batteryVoltage * 100;
   // }
   //
   // public static double getSoC(final FMU vb) {
   // return vb.getOutput().get(outputToIndices.get("SoC")).getValue();
   // }
   //
   // public static double getTotalBatteryLosses(final FMU vb) {
   // return vb.getOutput().get(outputToIndices.get("TotalBatteryLosses")).getValue();
   // }
   //
   // public static double getTotalTractiveEnergy(final FMU fmu) {
   // final Map<String, Double> inputs = fmu.getInput().stream()
   // .collect(Collectors.toMap(Input::getName, Input::getValue));
   //
   // if(outputToIndices == null) {
   // System.out.println("Create output indices...");
   // SimulationEnv.outputToIndices = new HashMap<>();
   // SimulationEnv.inputToIndices = new HashMap<>();
   //
   // for(int i = 0; i < vb.getOutputs().size(); i++) {
   // SimulationEnv.outputToIndices.put(vb.getOutputs().get(i).getName(), i);
   // }
   // for(int i = 0; i < vb.getInputs().size(); i++) {
   // SimulationEnv.inputToIndices.put(vb.getInputs().get(i).getName(), i);
   // }
   // SimulationEnv.testFMU = new VehicleBatteryFMU();
   // }
   //
   // final double batteryVoltage = inputs.get("BatteryVoltage");
   // final double internalRes = inputs.get("InternalRes");
   // final double maxTorque = inputs.get("MaxTorque");
   // final double auxload = SimulationEnv.calcAuxload(batteryVoltage, maxTorque);
   // final double maxPower = SimulationEnv.calcMaxPower(batteryVoltage, internalRes);
   //
   // vb.getInputs().get(inputToIndices.get("Auxload")).setValue(auxload);
   // vb.getInputs().get(inputToIndices.get("MaxPower")).setValue(maxPower);
   //
   // SimulationEnv.testFMU.setup(auxload, batteryVoltage, internalRes, maxTorque, maxPower);
   // SimulationEnv.testFMU.simulate(simulationTime);
   // //
   // final double totalTractiveEnergy = testFMU.getLast("TotalTractiveEnergy");
   // final double totalBatteryLosses = testFMU.getLast("TotalBatteryLosses");
   // final double soC = testFMU.getLast("SoC");
   //
   // vb.getOutputs().get(outputToIndices.get("TotalTractiveEnergy")).setValue(totalTractiveEnergy);
   // vb.getOutputs().get(outputToIndices.get("TotalBatteryLosses")).setValue(totalBatteryLosses);
   // vb.getOutputs().get(outputToIndices.get("SoC")).setValue(soC);
   //
   // SimulationEnv.testFMU.reset();
   //
   // return totalTractiveEnergy;
   // }

   public static void init(final int simulationTime, final String fmuPath) {
      SimulationEnv.simulationTime = simulationTime;
      SimulationEnv.fmuPath = fmuPath;
   }

   public static void runSimulation(final FMU fmu, final int seconds) {

   }

   public static SimulationResult runSimulation(final FMU fmu, final Map<String, Double> setInputs) {

      final FMUSurrogate f = new FMUSurrogate(fmuPath);

      final Set<String> doNotResetNames = setInputs.keySet();

      final HashMap<String, Double> inputValues = new HashMap<>(setInputs);
      fmu.getInput().stream().filter(i -> !doNotResetNames.contains(i.getName()))
            .forEach(i -> inputValues.put(i.getName(), i.getValue()));

      f.setup(new ArrayList<>(inputValues.values()), new ArrayList<>(inputValues.keySet()));
      f.simulate(.1, simulationTime);

      for(final Input i : fmu.getInput()) {
         i.setValue(inputValues.get(i.getName()));
      }

      final Map<String, Double> simulationResultOutputs = f.getFinalOutputs();
      for(final Output o : fmu.getOutput()) {
         o.setValue(simulationResultOutputs.get(o.getName()));
      }

      return new SimulationResult(simulationResultOutputs);
   }

}
