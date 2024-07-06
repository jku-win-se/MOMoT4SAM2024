# Automation Support for System Simulation and Architecture Layout Design in Cyber-Physical Systems Engineering

Simulations have long been part of hardware-centric system domains. As systems become more complex, so do simulation models, their configurations, and eventual analysis. 
Similarly, architectural design is a common practice for complex industrial systems, which comprise many components that can be arranged in different layouts. This paper presents a model-driven approach to automate the simulation configuration and architectural layouting engineering activities by leveraging model-driven optimization techniques.
The approach extends a research solution, MOMoT (Marrying Optimization and Model Transformations), an academic tool that combines search-based algorithms and model transformations. 
MOMoT is extended with a simulation configuration module that leverages the Functional Mock-up Interface standard to execute simulation models and with a layouting module offering a simple architectural description language for architectural models. Our solution is presented in the context of the AIDOaRt research project and the use case by Volvo Construction Equipment (CE) on engineering a battery-driven construction machine. The approach's main goals are enhancing the automation of the engineering process by introducing model-driven techniques in the systems engineering activities while preserving current simulation practices at the organization.

**Contents**
* [Material](#material)
    * [MOMoT](#momot)
    * [FMU Metamodel](#fmu-metamodel)
    * [TinyCC ADL and RCP-based Model Editor](#tinycc-adl-and-rcp-based-model-editor)
* [Installation](#installation-steps)
* [License](#license)

## Material

This repository contains all the supporting artifacts according to the approach's architecture in the figure

![image](https://github.com/jku-win-se/MOMoT4SAM2024/assets/925612/44401109-f97d-4137-b2fa-511b285d0970)

### MOMoT
- (Simulation) Configuration Module [Link](ConfigurationModule/src/fmu/main/batteryFMU.momot)
    - FMU Metamodel [Artifact](ConfigurationModule/metamodel/FMUModel.ecore)
    - FMU Model [Artifact](ConfigurationModule/models/FMU_skeleton.xmi)
    - Simulation Goals [Setup](https://github.com/jku-win-se/MOMoT4SAM2024/blob/main/ConfigurationModule/src/fmu/main/batteryFMU.momot#L66-L79)
    - Search Specification [Setup](https://github.com/jku-win-se/MOMoT4SAM2024/blob/main/ConfigurationModule/src/fmu/main/batteryFMU.momot#L81-L93)

- (Architectural) Layouting Module [Link](LayoutingModule/src/tms/demo/ThermalManagementSystem.momot)
    - TinyCC ADL Metamodel [Artifact](https://github.com/jku-win-se/TinyCC-ADL/blob/main/TinyCC/model/tinycc.ecore)
    - TinyCC Model [Artifact](LayoutingModule/models/TinyCC_input.xmi)
    - Layout Goals [Setup](https://github.com/jku-win-se/MOMoT4SAM2024/blob/main/LayoutingModule/src/tms/demo/ThermalManagementSystem.momot#L84-L89)
    - Layouting Rules [Artifact](LayoutingModule/transformations/tinycc.henshin)
    - Search Specification [Setup](https://github.com/jku-win-se/MOMoT4SAM2024/blob/main/LayoutingModule/src/tms/demo/ThermalManagementSystem.momot#L60-L72)


### FMU Metamodel

<img src="https://github.com/jku-win-se/MOMoT4SAM2024/assets/925612/ff0ac3ca-24b0-4dd6-83e8-3115bc5b2371" width="400" />

> [!WARNING]
> Links to artifacts to be added soon.

### TinyCC ADL and RCP-based Model Editor
TinyCC is a simple architectural description language (ADL) for system engineers. The TinyCC model consists of hierarchical sets of components and connectors. Components have input or output ports, determined by the value of the dir property (defined by the FlowDir enumeration). Connectors provide two or more connector ends, allowing the creation of a hierarchical network of components and connectors. Components, connectors, ends, and ports are all model elements that can be extended with user-defined properties. Each property can hold a type and a value.

<img src="https://github.com/jku-win-se/MOMoT4SAM2024/assets/925612/a24effd1-fd11-4f93-bec2-5a8d3a1f464e" width="600" />

In TinyCC, architectural models can be combined with simulation models, such as functional mock-up units (FMUs). To facilitate this combination, an abstract metaclass called SimElement is introduced. It provides a file-based mapping mechanism for components and connectors through the simModelPath property.

A EMF Rich-Client Platform standalone tree-based editor is available [here](https://github.com/jku-win-se/TinyCC-ADL).


> [!IMPORTANT]
> To SAM 2024 Reviewers: we are continuously revising the ADL to incorporate specific modeling features. The last change is  applied to the Property metaclass. 
> - types \[\*\] \-\> type \[0..1]
> - values \[\*\] \-\> value \[0..1]
>   
> The figure and description in the paper will be updated accordingly if accepted for publication.

## Installation Steps

- In an Eclipse workspace, import all projects

- Install Xtext

  - Repository site: https://download.eclipse.org/releases/2023-03/
  - Select "Modeling" -> "Xtext Complete SDK"

- Install Henshin

  - Repository site: https://download.eclipse.org/modeling/emft/henshin/updates/1.8.0/

- Install MOMoT's configuration language

  - Repository site: http://martin-fleck.github.io/momot/eclipse/updates/latest/stable/
  - Select "MOMoT" -> "MOMoT Configuration Language"

In order to run the Configuration Module with additional plotting utilities, python has to be installed and a conda environment set up accordingly:

- Create new conda environment from "environment.yaml" located in "ConfigurationModule" project
  - Navigate to "scripts" folder in "ConfigurationModule" project and use the following to setup the conda environment in subdirectory "scripts/plotting": "conda env create --file environments.yaml --prefix ." (requires conda installed!)

Navigate to configuration of the module in "batteryFMU.momot" located in "fmu.main" (Configuration Module) or "ThermalManagementSystem.momot" located in "tms.demo" (Layouting Module)

- Run the provided sample configuration (.momot file) or adapted configuration
    - Run as "MOMoT Orchestration File" with additional VM argument "--add-opens java.base/java.util=ALL-UNNAMED"

<details>
  <summary>Configuration Module Example Output</summary>
  
  ```
Search started.
-------------------------------------------------------
Search
-------------------------------------------------------
InputModel:      models/FMU.xmi
Objectives:      [TotalTractiveEnergy, TotalBatteryLosses, SoC]
NrObjectives:    3
Constraints:     []
NrConstraints:   0
Transformations: [transformations/fmu.henshin]
Units:           [Rule setInput(name, val)]
SolutionLength:  3
PopulationSize:  10
Iterations:      100
MaxEvaluations:  1000
AlgorithmRuns:   1
---------------------------
Run 'NSGAII' 1 times...
log4j:WARN No appenders could be found for logger (no.ntnu.ihb.fmi4j.importer.fmi2.AbstractModelInstance).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
[18:23:29.799] Run 1 of 1 started.
[18:24:20.597] Run 1 of 1 terminated after 0:00:50.794 (50794 ms).
[18:24:20.597] Total runtime for 1 seeds: 0:00:50.798 (50798 ms).
-------------------------------------------------------
Results
-------------------------------------------------------
- Save objectives of all algorithms to 'output/objectives/objective_values.txt'
---------------------------
Objectives of all algorithms
---------------------------
1.72594043438937 0.8852611877183272 -63.40850045443646
1.72594043438937 1.073015691445456 -63.83175415900486

- Save solutions of all algorithms to 'output/solutions/objective_values.txt'
- Save solutions of all algorithms to 'output/solutions/objective_values.txt'
- Save models of all algorithms to 'output/models/'
Exit Code: 0
Search finished.
  ```
  </details>
  ```

  
<details>
  <summary>Layouting Module Example Output</summary>
  
  ```

  Search started.
-------------------------------------------------------
Search
-------------------------------------------------------
InputModel:      models/TinyCC_input.xmi
Objectives:      [FlowCountMax, FlowCountMin, SolutionLength]
NrObjectives:    3
Constraints:     [allTypesUsed]
NrConstraints:   1
Transformations: [transformations/tinycc.henshin]
Units:           [SequentialUnit connectComponent(var toId, var fromId, var toType), SequentialUnit changeComponent(var fromId, var toId)]
SolutionLength:  10
PopulationSize:  30
Iterations:      33
MaxEvaluations:  1000
AlgorithmRuns:   1
---------------------------
Run 'NSGAII' 1 times...
[18:30:51.973] Run 1 of 1 started.
[18:30:59.423] Run 1 of 1 terminated after 0:00:07.446 (7446 ms).
[18:30:59.424] Total runtime for 1 seeds: 0:00:07.450 (7450 ms).
-------------------------------------------------------
Analysis
-------------------------------------------------------
---------------------------
Analysis Results
---------------------------
---------------------------
- Save Analysis to 'output/analysis/analysis.txt'
- Save Indicator BoxPlots to 'output/analysis/'
-------------------------------------------------------
Results
-------------------------------------------------------
- Save objectives of all algorithms to 'output/objectives/objective_values.txt'
---------------------------
Objectives of all algorithms
---------------------------
-2.0 2.0 7.0
-3.0 3.0 7.0

- Save solutions of all algorithms to 'output/solutions/all_solutions.txt'
- Save solutions of all algorithms to 'output/solutions/all_solutions.txt'
- Save models of all algorithms to 'output/models/'
Search finished.
  ```
  </details>
  ```

## License

Eclipse Public License - v 2.0
