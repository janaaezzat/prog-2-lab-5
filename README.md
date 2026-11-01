# prog 2 lab 5
# MARS-OPS — Mars Colony Management Simulation

## 1. Project Overview

MARS-OPS is a Java-based simulation of a Mars colony. It models the management of colony personnel, robotic units, facilities, resources, missions, environmental conditions, and emergencies.

The system allows a colony operator to manage colony resources, assign personnel and robots to missions, advance simulation time, and respond to emergencies that affect colony operations.

The project uses Object-Oriented Programming concepts, including inheritance, abstraction, encapsulation, polymorphism, and object relationships.

## 2. Main System Components

### MarsColony

`MarsColony` represents the colony and manages its main components.

Its responsibilities include:
- Maintaining lists of facilities, personnel, and robots.
- Managing the currently active mission.
- Accepting or rejecting mission submissions.
- Allocating colony-wide resources.
- Advancing simulation time.
- Receiving and handling emergencies.
- Generating colony resource reports.

Only one mission can be active at a time.

### Personnel

The `HumanPersonnel` class represents colony crew members.

Specialized personnel types include:
- `Engineer`
- `Scientist`
- `MedicalOfficer`
- `FireResponse`

Personnel have attributes such as health, fatigue, availability, location, and capabilities.

### Robotic Units

The `RoboticUnits` class represents the colony's robots.

Specialized robotic units include:
- `ExplorationRover`
- `RepairRobot`
- `Drone`

Robots have battery levels, damage status, availability, location, and capabilities.

### Facilities

The `Facilities` class represents colony facilities and manages their resources and operational status.

Facility types include:
- `Habitat`
- `Laboratories`
- `CommunicationStations`
- `PowerStations`
- `StorageFacilities`

Facilities can store and provide resources required by missions and emergencies.

### Missions

`Mission` is an abstract class representing a colony mission.

The four mission types are:
- `ExplorationMission`
- `RepairMission`
- `ResearchMission`
- `RescueMission`

Each mission has a duration, target location, assigned personnel, assigned robots, required resources, and a status.

### Emergencies

The abstract `Emergency` class represents an emergency affecting the colony.

Implemented emergency types include:
- `Fire`
- `OxygenLeak`
- `MedicalEmergency`
- `RoverBreakDown`

Each emergency has its own responder requirements, effects, and resolution behavior.

### Environment

The `Enviroment` class represents the current environmental conditions affecting colony operations.

The supported conditions include:
- Normal conditions.
- Dust storms.
- Communication disruptions.

## 3. Mission States and Lifecycle

Every mission follows a lifecycle represented by the `MissionStatus` enumeration.

| State | Description |
|---|---|
| `ASSIGNED` | Mission has been created and is awaiting execution. |
| `IN_PROGRESS` | Mission has passed its requirements and resource checks and has started. |
| `COMPLETED` | Mission has finished successfully. |
| `FAILED` | Mission has been rejected or has failed during execution. |

### Mission Execution Rules

1. The colony accepts a mission submission.
2. The colony checks whether another mission is already active.
3. Environmental conditions are checked.
4. Mission requirements and agent capabilities are checked.
5. Required resources are secured.
6. If all checks pass, the mission becomes active and enters `IN_PROGRESS`.
7. Simulation time advances the mission.
8. When the required duration is reached, the mission executes.
9. The mission becomes `COMPLETED` or `FAILED`.
10. Assigned agents become available again when the mission ends.

Rejected missions become `FAILED` and do not become the colony's active mission.

## 4. Resource Allocation Rules

Mission resources are managed at the colony level.

The colony checks resource requirements against the available colony-wide totals before consuming anything.

The supported resource types include:
- Oxygen
- Water
- Food
- Fuel
- Battery
- Medical Supplies
- Spare Parts

### Resource Allocation Process

1. The mission specifies its required resources.
2. The colony checks whether sufficient quantities exist.
3. Only resources held by operational facilities are used.
4. Resources may be collected from multiple operational facilities.
5. If any required resource is insufficient, the mission is rejected and no resources should be consumed.
6. If all requirements are satisfied, the required quantities are deducted.

This follows an all-or-nothing resource allocation approach.

## 5. Emergency Handling Rules

Emergencies are submitted to the colony through `reportEmergency()`.

The colony checks whether suitable responders are available. If the requirements are satisfied, the emergency is processed; otherwise, it is marked as unresolved with a failure reason.

### Oxygen Leak

- An affected habitat is identified.
- Oxygen is deducted when the leak is detected.
- Suitable personnel and a repair-capable robot are required.
- If the leak is repaired, the initial oxygen deduction represents the total loss.
- If the emergency remains unresolved, the same amount is deducted again.
- Oxygen quantities must not become negative.

### Fire

- The affected laboratory becomes unavailable.
- Oxygen is consumed.
- Fire-response personnel and a repair-capable robot are required.
- Medical assistance may be used to treat affected personnel.
- The laboratory's operational state is updated after successful handling.

### Rover Breakdown

- The affected rover receives a damage status indicating a breakdown.
- The rover becomes unavailable and remains at the breakdown location.
- Its exploration mission fails.
- A separate `RepairMission` is created with the required repair resources.
- The colony schedules the repair mission separately.

### Medical Emergency

- The system checks for an available medical officer.
- The relevant facility must be operational.
- At least five medical supplies must be available.
- Upon successful treatment, the patient's health increases.

## 6. Environmental Effects

The colony supports three environmental conditions.

### Normal Conditions

Normal colony operations are permitted.

### Dust Storm

- Surface exploration missions cannot begin.
- Active rover missions are interrupted according to the environmental mission checks.

### Communication Disruption

- Certain communication-dependent missions cannot begin.
- Remote drone operations may be restricted depending on their capabilities and operating conditions.

Environmental conditions are checked when missions are submitted and while active missions progress.

## 7. Design Decisions and Assumptions

The following decisions were made where the specification allowed implementation flexibility:

- The colony manages mission scheduling and resource allocation centrally.
- Only one mission can be active at a time.
- Facilities do not own or schedule missions.
- Personnel and robots can exist independently of the colony and are managed through colony collections.
- Mission requirements are checked using agent capabilities rather than relying exclusively on concrete personnel or robot types.
- A repair mission can target either a damaged facility or a damaged robotic unit.
- A rover breakdown creates a separate repair mission rather than automatically starting the repair.
- The repair mission is submitted and scheduled by the colony.
- Rescue missions transport the rescued person to a communication station and provide health recovery.
- Research missions use an operational laboratory and personnel with research capability.
- No separate equipment-management system is modeled; mission suitability is represented through the implemented capabilities and resource requirements.

## 8. UML Design

The project includes the following UML diagrams:

1. Class Diagram
2. Object Diagram
3. State Machine Diagram
4. Sequence Diagrams:
   - Mission Submission and Execution
   - Oxygen Leak Emergency
   - Environmental Restriction
5. Use Case Diagram

The diagrams represent the implemented Java structure and interactions.

## 9. Compilation and Execution

**To be finalized after the test runner is implemented and verified.**

The main class is `MarsOp3`.

Final instructions will include:
- Required Java version.
- Project compilation instructions.
- How to execute `MarsOp3`.
- How to run the required demonstration scenarios.

## 10. Demonstration Scenarios

The main class will demonstrate the five scenarios required by the lab:

1. Successful Exploration
2. Mission Rejection
3. Resource Shortage
4. Oxygen Leak Emergency
5. Environmental Effect

The exact execution steps and expected results will be documented after the test scenarios are implemented and verified.
