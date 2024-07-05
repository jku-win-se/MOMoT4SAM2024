package fmu.main;

import at.ac.tuwien.big.moea.search.fitness.dimension.IFitnessDimension.FunctionType;
import at.ac.tuwien.big.moea.util.FileUtil;
import at.ac.tuwien.big.momot.util.MomotUtil;

import com.opencsv.CSVWriter;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

import fmu.FMUSurrogate;
import fmumodel.FMU;

public class ConfigurationPlotting {
   static protected void callPyScript(final String scriptName) {
      try {
         // Set the path to your Conda environment activation script

         // Set the name of your Conda environment
         final String condaEnvironmentName = Path.of(".", "scripts", "plotting", "python").toString();

         // Set the path to your Python script
         final String pythonScriptPath = Path.of(".", "scripts", scriptName).toString();
         // Create a command to activate the Conda environment and run the Python script

         // Create a ProcessBuilder
         final ProcessBuilder processBuilder = new ProcessBuilder(condaEnvironmentName, pythonScriptPath);

         // Redirect error stream to standard output
         processBuilder.redirectErrorStream(true);

         // Start the process
         final Process process = processBuilder.start();

         // Read the output of the process
         final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
         String line;
         while((line = reader.readLine()) != null) {
            System.out.println(line);
         }

         // Wait for the process to finish
         final int exitCode = process.waitFor();
         System.out.println("Exit Code: " + exitCode);

      } catch(IOException | InterruptedException e) {
         e.printStackTrace();
      }
   }

   protected static PrintStream getFilePrintStream(final File file) {
      FileUtil.checkDirectory(file.getParentFile());
      try {
         return new PrintStream(new BufferedOutputStream(new FileOutputStream(file)), true, "UTF-8");
      } catch(FileNotFoundException | UnsupportedEncodingException e) {
         System.err.println(e.getMessage());
         return null;
      }
   }

   public static void plot(final FMU m, final String outFile, final Map<String, FunctionType> outputToType) {
      final FMUSurrogate f = new FMUSurrogate(SimulationEnv.fmuPath);

      final HashMap<String, Double> inputValues = new HashMap<>();
      m.getInput().stream().forEach(i -> inputValues.put(i.getName(), i.getValue()));

      f.setup(new ArrayList<>(inputValues.values()), new ArrayList<>(inputValues.keySet()));
      f.simulate(.1, SimulationEnv.simulationTime);

      //
      // final List<Double> totalTractiveEnergy = f.get("TotalTractiveEnergy");
      // final List<Double> totalBatteryLosses = f.get("TotalBatteryLosses");
      // final List<Double> soC = f.get("SoC");

      final Map<String, List<Double>> outputVals = new HashMap<>();
      for(final String outputName : outputToType.keySet()) {
         outputVals.put(outputName, f.get(outputName));
      }

      // Specify the file path
      final String filePath = "output/simulations/" + outFile;

      try(final CSVWriter writer2 = new CSVWriter(new FileWriter(filePath))) {
         // Write the header
         final List<String> header = outputToType.keySet().stream().collect(Collectors.toList());
         header.add(0, "t");
         writer2.writeNext(header.toArray(new String[0]));

         // Write the data
         final int size = outputVals.values().iterator().next().size();
         for(int i = 0; i < size; i++) {
            final String[] row = new String[1 + outputToType.size()];
            row[0] = String.valueOf((i + 1) * .1);
            for(int j = 1; j < row.length; j++) {
               row[j] = outputVals.get(header.get(j)).get(i).toString();
            }

            writer2.writeNext(row);
         }

      } catch(final IOException e) {
         e.printStackTrace();
      }
   }

   public static void plotSimulationResult(final String modelsPath, final Map<String, FunctionType> outputToType) {
      final HenshinResourceSet set = new HenshinResourceSet();
      final File modelDir = new File(modelsPath);
      for(final File modelFile : modelDir.listFiles()) {
         final EGraph graph = MomotUtil.loadGraph(set, modelFile.getAbsolutePath());
         final FMU m = (FMU) graph.getRoots().get(0);
         plot(m, modelFile.getName() + ".csv", outputToType);
      }
      callPyScript("fmu_simulation_viz.py");
   }
}
