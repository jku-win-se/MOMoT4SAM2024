# Automation Support for System Simulation and Architecture Layout Design in Cyber-Physical Systems Engineering

Simulations have long been part of hardware-centric system domains. As systems become more complex, so do simulation models, their configurations, and eventual analysis. 
Similarly, architectural design is a common practice for complex industrial systems, which comprise many components that can be arranged in different layouts. This paper presents a model-driven approach to automate the simulation configuration and architectural layouting engineering activities by leveraging model-driven optimization techniques.
The approach extends a research solution, MOMoT (Marrying Optimization and Model Transformations), an academic tool that combines search-based algorithms and model transformations. 
MOMoT is extended with a simulation configuration module that leverages the Functional Mock-up Interface standard to execute simulation models and with a layouting module offering a simple architectural description language for architectural models. Our solution is presented in the context of the AIDOaRt research project and the use case by Volvo Construction Equipment (CE) on engineering a battery-driven construction machine. The approach's main goals are enhancing the automation of the engineering process by introducing model-driven techniques in the systems engineering activities while preserving current simulation practices at the organization.

## Material

This repository contains all the supporting artifacts according to the approach's architecture in the figure

![image](https://github.com/jku-win-se/MOMoT4SAM2024/assets/925612/44401109-f97d-4137-b2fa-511b285d0970)

### TinyCC ADL and RCP-based Model Editor
TinyCC is a simple architectural description language (ADL) for system engineers. The TinyCC model consists of hierarchical sets of components and connectors. Components have input or output ports, determined by the value of the dir property (defined by the FlowDir enumeration). Connectors provide two or more connector ends, allowing the creation of a hierarchical network of components and connectors. Components, connectors, ends, and ports are all model elements that can be extended with user-defined properties. Each property can hold a type and a value.

<img src="https://github.com/jku-win-se/MOMoT4SAM2024/assets/925612/1652e028-bbfc-4854-a823-de0e753967a3" width="600" />

In TinyCC, architectural models can be combined with simulation models, such as functional mock-up units (FMUs). To facilitate this combination, an abstract metaclass called SimElement is introduced. It provides a file-based mapping mechanism for components and connectors through the simModelPath property.

A EMF Rich-Client Platform standalone tree-based editor is available [here](https://github.com/jku-win-se/TinyCC-ADL).


> [!IMPORTANT]
> To SAM 2024 Reviewers: we are continuously revising the ADL to incorporate specific modeling features. The last change is  applied to the Property metaclass. 

> types [*] -> type [0..1]
> values [*] -> value [0..1]

> The figure and description in the paper will be updated accordingly if accepted for publication.

## License

Eclipse Public License - v 2.0
