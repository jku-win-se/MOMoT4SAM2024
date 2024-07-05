package momot.search;

import at.ac.tuwien.big.moea.SearchExperiment;
import at.ac.tuwien.big.moea.experiment.executor.listener.SeedRuntimePrintListener;
import at.ac.tuwien.big.moea.print.IPopulationWriter;
import at.ac.tuwien.big.moea.print.ISolutionWriter;
import at.ac.tuwien.big.moea.search.algorithm.EvolutionaryAlgorithmFactory;
import at.ac.tuwien.big.moea.search.algorithm.LocalSearchAlgorithmFactory;
import at.ac.tuwien.big.moea.search.algorithm.provider.IRegisteredAlgorithm;
import at.ac.tuwien.big.moea.search.fitness.dimension.IFitnessDimension;
import at.ac.tuwien.big.momot.ModuleManager;
import at.ac.tuwien.big.momot.TransformationResultManager;
import at.ac.tuwien.big.momot.TransformationSearchOrchestration;
import at.ac.tuwien.big.momot.problem.solution.TransformationSolution;
import at.ac.tuwien.big.momot.problem.unit.parameter.IParameterValue;
import at.ac.tuwien.big.momot.problem.unit.parameter.random.RandomListValue;
import at.ac.tuwien.big.momot.search.fitness.EGraphMultiDimensionalFitnessFunction;
import at.ac.tuwien.big.momot.search.fitness.IEGraphMultiDimensionalFitnessFunction;
import at.ac.tuwien.big.momot.search.fitness.dimension.AbstractEGraphFitnessDimension;
import at.ac.tuwien.big.momot.util.MomotUtil;
import fmu.main.ConfigurationPlotting;
import fmu.main.FMUUtils;
import fmu.main.RandomDoubleValueFMU;
import fmu.main.SimulationEnv;
import fmu.main.SimulationResult;
import fmumodel.FMU;
import fmumodel.FmumodelPackage;
import fmumodel.Input;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.core.Population;
import org.moeaframework.util.progress.ProgressListener;

@SuppressWarnings("all")
public class batteryFMU {
  protected static String attribute = "simResults";

  protected static String fmuPath = "fmu-lib/BEV_5in_9out.fmu";

  protected static int simDuration = 2474;

  protected static final String INITIAL_MODEL = "models/FMU.xmi";

  protected static final int SOLUTION_LENGTH = 3;

  public class batteryFMUFitnessFunction extends EGraphMultiDimensionalFitnessFunction {
    @Override
    protected void preprocessEvaluation(final TransformationSolution solution) {
      final FMU root = MomotUtil.<FMU>getRoot(solution.execute(), FMU.class);
      final Function1<Input, Pair<String, Double>> _function = (Input it) -> {
        String _name = it.getName();
        double _value = it.getValue();
        return Pair.<String, Double>of(_name, Double.valueOf(_value));
      };
      final Map<String, Double> inputs = CollectionLiterals.<String, Double>newImmutableMap(((Pair<? extends String, ? extends Double>[])Conversions.unwrapArray(ListExtensions.<Input, Pair<String, Double>>map(root.getInput(), _function), Pair.class)));
      Double _get = inputs.get("InternalRes");
      double _multiply = ((_get).doubleValue() * 100000);
      double _minus = (100000 - _multiply);
      Double _get_1 = inputs.get("BatteryVoltage");
      double _multiply_1 = ((_get_1).doubleValue() * 100);
      double _plus = (_minus + _multiply_1);
      Pair<String, Double> _mappedTo = Pair.<String, Double>of("MaxPower", Double.valueOf(_plus));
      Double _get_2 = inputs.get("BatteryVoltage");
      double _minus_1 = ((_get_2).doubleValue() - 340);
      double _multiply_2 = (_minus_1 * 2);
      double _plus_1 = (600 + _multiply_2);
      Double _get_3 = inputs.get("MaxTorque");
      double _minus_2 = ((_get_3).doubleValue() - 500);
      double _multiply_3 = (_minus_2 * 0.75);
      double _plus_2 = (_plus_1 + _multiply_3);
      Pair<String, Double> _mappedTo_1 = Pair.<String, Double>of("AuxLoad", Double.valueOf(_plus_2));
      final Map<String, Double> calcInputs = CollectionLiterals.<String, Double>newImmutableMap(_mappedTo, _mappedTo_1);
      solution.setAttribute(batteryFMU.attribute, SimulationEnv.runSimulation(root, calcInputs));
    }
  }

  protected final String[] modules = new String[] { "transformations/fmu.henshin" };

  protected final String _parameterValueKey_0 = "fmu::fmu::setInput::val";

  protected final String _parameterValueKey_1 = "fmu::fmu::setInput::name";

  protected final int populationSize = 10;

  protected final int maxEvaluations = 1000;

  protected final int nrRuns = 1;

  protected String baseName;

  protected IParameterValue<?> _createParameterValue_0() {
    RandomDoubleValueFMU _randomDoubleValueFMU = new RandomDoubleValueFMU();
    return _randomDoubleValueFMU;
  }

  protected IParameterValue<?> _createParameterValue_1() {
    List<String> _of = List.<String>of("BatteryVoltage", "InternalRes", "MaxTorque");
    RandomListValue<String> _randomListValue = new RandomListValue<String>(_of);
    return _randomListValue;
  }

  protected double _createObjectiveHelper_0(final TransformationSolution solution, final EGraph graph, final EObject root) {
    double _xblockexpression = (double) 0;
    {
      final SimulationResult simResult = solution.<SimulationResult>getAttribute(batteryFMU.attribute, SimulationResult.class);
      _xblockexpression = simResult.getOutput("TotalTractiveEnergy");
    }
    return _xblockexpression;
  }

  protected IFitnessDimension<TransformationSolution> _createObjective_0(final TransformationSearchOrchestration orchestration) {
    return new AbstractEGraphFitnessDimension("TotalTractiveEnergy", at.ac.tuwien.big.moea.search.fitness.dimension.IFitnessDimension.FunctionType.Minimum) {
       @Override
       protected double internalEvaluate(TransformationSolution solution) {
          EGraph graph = solution.execute();
          EObject root = MomotUtil.getRoot(graph);
          return _createObjectiveHelper_0(solution, graph, root);
       }
    };
  }

  protected double _createObjectiveHelper_1(final TransformationSolution solution, final EGraph graph, final EObject root) {
    double _xblockexpression = (double) 0;
    {
      final SimulationResult simResult = solution.<SimulationResult>getAttribute(batteryFMU.attribute, SimulationResult.class);
      _xblockexpression = simResult.getOutput("BatteryLosses_W_");
    }
    return _xblockexpression;
  }

  protected IFitnessDimension<TransformationSolution> _createObjective_1(final TransformationSearchOrchestration orchestration) {
    return new AbstractEGraphFitnessDimension("TotalBatteryLosses", at.ac.tuwien.big.moea.search.fitness.dimension.IFitnessDimension.FunctionType.Minimum) {
       @Override
       protected double internalEvaluate(TransformationSolution solution) {
          EGraph graph = solution.execute();
          EObject root = MomotUtil.getRoot(graph);
          return _createObjectiveHelper_1(solution, graph, root);
       }
    };
  }

  protected double _createObjectiveHelper_2(final TransformationSolution solution, final EGraph graph, final EObject root) {
    double _xblockexpression = (double) 0;
    {
      final SimulationResult simResult = solution.<SimulationResult>getAttribute(batteryFMU.attribute, SimulationResult.class);
      _xblockexpression = simResult.getOutput("SoC");
    }
    return _xblockexpression;
  }

  protected IFitnessDimension<TransformationSolution> _createObjective_2(final TransformationSearchOrchestration orchestration) {
    return new AbstractEGraphFitnessDimension("SoC", at.ac.tuwien.big.moea.search.fitness.dimension.IFitnessDimension.FunctionType.Maximum) {
       @Override
       protected double internalEvaluate(TransformationSolution solution) {
          EGraph graph = solution.execute();
          EObject root = MomotUtil.getRoot(graph);
          return _createObjectiveHelper_2(solution, graph, root);
       }
    };
  }

  protected ModuleManager createModuleManager() {
    ModuleManager manager = new ModuleManager();
    for(String module : modules) {
       manager.addModule(URI.createFileURI(new File(module).getPath().toString()).toString());
    }
    manager.setParameterValue(_parameterValueKey_0, _createParameterValue_0());
    manager.setParameterValue(_parameterValueKey_1, _createParameterValue_1());
    return manager;
  }

  protected IEGraphMultiDimensionalFitnessFunction createFitnessFunction(final TransformationSearchOrchestration orchestration) {
    IEGraphMultiDimensionalFitnessFunction function = new batteryFMU.batteryFMUFitnessFunction();
    function.addObjective(_createObjective_0(orchestration));
    function.addObjective(_createObjective_1(orchestration));
    function.addObjective(_createObjective_2(orchestration));
    return function;
  }

  protected IRegisteredAlgorithm<NSGAII> _createRegisteredAlgorithm_0(final TransformationSearchOrchestration orchestration, final EvolutionaryAlgorithmFactory<TransformationSolution> moea, final LocalSearchAlgorithmFactory<TransformationSolution> local) {
    IRegisteredAlgorithm<NSGAII> _createNSGAII = moea.createNSGAII();
    return _createNSGAII;
  }

  protected ProgressListener _createListener_0() {
    SeedRuntimePrintListener _seedRuntimePrintListener = new SeedRuntimePrintListener();
    return _seedRuntimePrintListener;
  }

  protected EGraph createInputGraph(final String initialGraph, final ModuleManager moduleManager) {
    EGraph graph = moduleManager.loadGraph(initialGraph);
    return graph;
  }

  protected TransformationSearchOrchestration createOrchestration(final String initialGraph, final int solutionLength) {
    TransformationSearchOrchestration orchestration = new TransformationSearchOrchestration();
    ModuleManager moduleManager = createModuleManager();
    EGraph graph = createInputGraph(initialGraph, moduleManager);
    orchestration.setModuleManager(moduleManager);
    orchestration.setProblemGraph(graph);
    orchestration.setSolutionLength(solutionLength);
    orchestration.setFitnessFunction(createFitnessFunction(orchestration));
    
    EvolutionaryAlgorithmFactory<TransformationSolution> moea = orchestration.createEvolutionaryAlgorithmFactory(populationSize);
    LocalSearchAlgorithmFactory<TransformationSolution> local = orchestration.createLocalSearchAlgorithmFactory();
    orchestration.addAlgorithm("NSGAII", _createRegisteredAlgorithm_0(orchestration, moea, local));
    
    return orchestration;
  }

  protected SearchExperiment<TransformationSolution> createExperiment(final TransformationSearchOrchestration orchestration) {
    SearchExperiment<TransformationSolution> experiment = new SearchExperiment<TransformationSolution>(orchestration, maxEvaluations);
    experiment.setNumberOfRuns(nrRuns);
    experiment.addProgressListener(_createListener_0());
    return experiment;
  }

  protected void deriveBaseName(final TransformationSearchOrchestration orchestration) {
    EObject root = MomotUtil.getRoot(orchestration.getProblemGraph());
    if(root == null || root.eResource() == null || root.eResource().getURI() == null)
    	baseName = getClass().getSimpleName();
    else
    	baseName = root.eResource().getURI().trimFileExtension().lastSegment();
  }

  protected TransformationResultManager handleResults(final SearchExperiment<TransformationSolution> experiment) {
    ISolutionWriter<TransformationSolution> solutionWriter = experiment.getSearchOrchestration().createSolutionWriter();
    IPopulationWriter<TransformationSolution> populationWriter = experiment.getSearchOrchestration().createPopulationWriter();
    TransformationResultManager resultManager = new TransformationResultManager(experiment);
    Population population;
    population = 
    	TransformationResultManager.createApproximationSet(experiment, (String[])null);
    System.out.println("- Save objectives of all algorithms to 'output/objectives/objective_values.txt'");
    TransformationResultManager.saveObjectives(
    	"output/objectives/objective_values.txt",
    	population
    );
    System.out.println("---------------------------");
    System.out.println("Objectives of all algorithms");
    System.out.println("---------------------------");
    System.out.println(TransformationResultManager.printObjectives(
    	population
    ));
    
    population = 
    	TransformationResultManager.createApproximationSet(experiment, (String[])null);
    System.out.println("- Save solutions of all algorithms to 'output/solutions/objective_values.txt'");
    TransformationResultManager.savePopulation(
    	"output/solutions/objective_values.txt",
    	population,
    	populationWriter
    );
    System.out.println("- Save solutions of all algorithms to 'output/solutions/objective_values.txt'");
    TransformationResultManager.saveSolutions(
    	"output/solutions/",
    	baseName,
    	MomotUtil.asIterables(
    		population,
    		TransformationSolution.class),
    	solutionWriter
    );
    
    population = 
    	TransformationResultManager.createApproximationSet(experiment, (String[])null);
    System.out.println("- Save models of all algorithms to 'output/models/'");
    TransformationResultManager.saveModels(
    	"output/models/",
    	baseName,
    	population
    );
    
    return resultManager;
  }

  public void printSearchInfo(final TransformationSearchOrchestration orchestration) {
    System.out.println("-------------------------------------------------------");
    System.out.println("Search");
    System.out.println("-------------------------------------------------------");
    System.out.println("InputModel:      " + INITIAL_MODEL);
    System.out.println("Objectives:      " + orchestration.getFitnessFunction().getObjectiveNames());
    System.out.println("NrObjectives:    " + orchestration.getNumberOfObjectives());
    System.out.println("Constraints:     " + orchestration.getFitnessFunction().getConstraintNames());
    System.out.println("NrConstraints:   " + orchestration.getNumberOfConstraints());
    System.out.println("Transformations: " + Arrays.toString(modules));
    System.out.println("Units:           " + orchestration.getModuleManager().getUnits());
    System.out.println("SolutionLength:  " + orchestration.getSolutionLength());
    System.out.println("PopulationSize:  " + populationSize);
    System.out.println("Iterations:      " + maxEvaluations / populationSize);
    System.out.println("MaxEvaluations:  " + maxEvaluations);
    System.out.println("AlgorithmRuns:   " + nrRuns);
    System.out.println("---------------------------");
  }

  public void performSearch(final String initialGraph, final int solutionLength) {
    TransformationSearchOrchestration orchestration = createOrchestration(initialGraph, solutionLength);
    deriveBaseName(orchestration);
    printSearchInfo(orchestration);
    SearchExperiment<TransformationSolution> experiment = createExperiment(orchestration);
    experiment.run();
    System.out.println("-------------------------------------------------------");
    System.out.println("Results");
    System.out.println("-------------------------------------------------------");
    handleResults(experiment);
  }

  public static void initialization() {
    FmumodelPackage.eINSTANCE.eClass();
    FMUUtils.initFMUModel(batteryFMU.fmuPath, Map.<String, Double>of("AuxLoad", Double.valueOf(600.0), "BatteryVoltage", Double.valueOf(340.0), "InternalRes", Double.valueOf(0.1), "MaxTorque", Double.valueOf(500.0), "MaxPower", Double.valueOf(100000.0)));
    SimulationEnv.init(batteryFMU.simDuration, batteryFMU.fmuPath);
    System.out.println("Search started.");
  }

  public static void finalization() {
    boolean _isDirectory = Files.isDirectory(Paths.get("scripts", "plotting"));
    if (_isDirectory) {
      ConfigurationPlotting.plotSimulationResult("output/models", 
        Map.<String, IFitnessDimension.FunctionType>of("BatteryLosses_W_", IFitnessDimension.FunctionType.Minimum, "TotalTractiveEnergy", 
          IFitnessDimension.FunctionType.Maximum, "SoC", IFitnessDimension.FunctionType.Maximum));
    }
    System.out.println("Search finished.");
  }

  public static void main(final String... args) {
    initialization();
    batteryFMU search = new batteryFMU();
    search.performSearch(INITIAL_MODEL, SOLUTION_LENGTH);
    finalization();
  }
}
