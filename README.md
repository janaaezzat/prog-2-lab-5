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
Each has different set of skills that will be needed in missions and emergencies.

### Robotic Units

The `RoboticUnits` class represents the colony's robots.

Specialized robotic units include:
- `ExplorationRover`
- `RepairRobot`
- `Drone`

Robots have battery levels, damage status, availability, location, and capabilities.
Each has different set of skills that will be needed in missions and emergencies.

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

|     State      |                          Description                                     |
|----------------|--------------------------------------------------------------------------|
| `ASSIGNED`     | Mission has been created and is awaiting execution.                      |
| `IN_PROGRESS`  | Mission has passed its requirements and resource checks and has started. |
| `COMPLETED`    | Mission has finished successfully.                                       |
| `FAILED`       | Mission has been rejected or has failed during execution.                |

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
7. If requirements are not met, resources are not deducted.

This follows an all-or-nothing resource allocation approach.

## 5. Emergency Handling Rules

Emergencies are submitted to the colony through `reportEmergency()`.

The colony checks whether suitable responders are available. If the requirements are satisfied, the emergency is processed; otherwise, it is marked as unresolved with a failure reason.

### Oxygen Leak

- An affected habitat is identified.
- Oxygen is deducted when the leak is detected.
- One personnel of type engineer and one repair-capable robot are required.
- If the leak is repaired, the initial oxygen deduction represents the total loss.
- If the emergency remains unresolved, the same amount is deducted again.
- Oxygen quantities must not become negative.

### Fire

- The affected laboratory becomes unavailable.
- Oxygen is consumed.
- Three Fire-response personnel and one repair-capable robot are required.
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

The MARS-OPS system models three environmental conditions through the `Enviroment` class.

### Normal Conditions
- Normal colony and mission operations are permitted, subject to mission requirements and resource availability.

### Dust Storm
- Environmental conditions may prevent or interrupt missions that are incompatible with a dust storm.
- Whether a mission can continue is determined by the environmental checks applied to that mission.

### Communication Disruption
- Communication-dependent operations may be affected by communication disruption.
- The impact on a mission or robotic unit depends on the environmental rules implemented for that operation.

### Environmental Checks
- The colony checks environmental conditions when determining whether a mission can be submitted or continue running.
- During mission execution, the environment is checked through `canContinueMission()`.
- If environmental conditions prevent an active mission from continuing, the mission may fail, and its assigned personnel and robots are released.
- Environmental effects are evaluated alongside mission requirements, resource availability, and the capabilities of assigned agents.

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


## 9. Demonstration Scenarios and Testing

The `MarsOp3` main class demonstrates the five required scenarios specified in the lab assignment. Each scenario creates the necessary objects, performs an operation, and displays the resulting mission, resource, personnel, or facility state.

All five scenarios are executed sequentially using one colony instance and its facilities, personnel, and robotic units.

### Scenario 1: Successful Exploration

**Setup:**
- Create an operational habitat containing sufficient colony resources.
- Create a scientist with Research capability.
- Create an exploration rover with Exploration capability.
- Assign the scientist and rover to an exploration mission.

**Execution:**
- Submit the exploration mission to the colony.
- Advance simulation time until the mission duration is reached.

**Expected Result:**
- The mission starts successfully and reaches `COMPLETED`.
- The surface exploration result becomes `true`.
- The scientist and rover become available again.
- The rover's battery decreases during mission execution.
- The required mission resources are deducted from the colony.

**Demonstrated Rules:** Mission requirements, capability-based assignment, resource consumption, mission execution, time advancement, and agent release.

**Implementation Note:** The current implementation does not include a separate equipment-management system, so equipment validation is not demonstrated by this scenario.

### Scenario 2: Mission Rejection

**Setup:**
- Create an exploration mission.
- Assign an engineer who does not possess the required Research capability.
- Assign an exploration rover.

**Execution:**
- Submit the mission to the colony.

**Expected Result:**
- The mission is immediately marked `FAILED`.
- A failure reason is recorded because the assigned personnel do not satisfy the mission requirements.
- The mission never becomes the colony's active mission.
- The assigned agents remain available.

**Demonstrated Rules:** Mission requirement validation, capability checking, immediate rejection, and failure-reason recording.

### Scenario 3: Resource Shortage

**Setup:**
- Create an exploration mission requiring 5000 units of fuel.
- The colony has only 190 units of fuel remaining at this point in the demonstration.
- Assign suitable personnel and a rover.

**Execution:**
- Record the colony's fuel total before submission.
- Submit the mission.
- Record the fuel total again after submission.

**Expected Result:**
- The mission is marked `FAILED`.
- The failure reason indicates insufficient colony resources.
- The fuel total remains unchanged at 190 units.
- No partial resource consumption occurs.
- The mission never becomes active.

**Demonstrated Rules:** Colony-wide resource checking and all-or-nothing resource allocation.

### Scenario 4: Oxygen Leak Emergency

**Setup:**
- Use the operational Habitat A facility.
- Create an oxygen leak with a specified loss of 20 oxygen units.
- Ensure an engineer and a repair-capable robot are available.

**Execution:**
- Record the habitat's oxygen level before the leak.
- Create the oxygen leak.
- Submit the emergency through `MarsColony.reportEmergency()`.

**Expected Result:**
- The emergency is detected and the affected habitat is identified.
- The specified 20 oxygen units are deducted from the habitat.
- Suitable responders are identified and dispatched.
- The emergency is successfully handled.
- The habitat remains operational after successful repair.
- The engineer and repair robot become available again.

In the demonstrated run, oxygen decreases from 245 to 225 units.

**Demonstrated Rules:** Emergency detection, responder selection, emergency handling, oxygen consumption, facility-state management, and responder release.

### Scenario 5: Environmental Effect

**Setup:**
- Set the colony's environmental condition to `Dust Storm`.
- Create an exploration mission with ID `EXP-004`.

**Execution:**
- Submit the exploration mission through `MarsColony.submitMission()`.
- The colony checks environmental compatibility before checking the remaining mission requirements.
- Restore the environmental condition to Normal after the test.

**Expected Result:**
- The dust storm prevents the exploration mission from starting.
- Mission `EXP-004` is marked `FAILED`.
- The failure reason indicates that environmental conditions prevent the mission from starting.
- The mission does not become the colony's active mission.

**Demonstrated Rules:** Environmental compatibility checking, colony-level mission submission, environmental restrictions, and mission failure handling.


# Test output

========================================
       MARS-OPS COLONY SIMULATION
========================================
Facility was created successfully!

Colony setup completed.

Initial Colony Status:
Colony-wide oxygen resource total: 250 units
Colony-wide water resource total: 150 units
Colony-wide food resource total: 150 units
Colony-wide fuel resource total: 200 units
Colony-wide battery power total: 150 units
Colony-wide medical supplies total: 50 units
Colony-wide spare parts total: 50 units


========================================
SCENARIO 1: SUCCESSFUL EXPLORATION
========================================

--- BEFORE EXECUTION ---
Mission ID: EXP-001
Mission Status: ASSIGNED
Surface explored: false
Scientist available: true
Rover available: true
Mission EXP-001 successfully started!
Exploration of the Surface completed successfully.
Mission EXP-001 ended with status: COMPLETED

--- AFTER EXECUTION ---
Mission Status: COMPLETED
Surface explored: true
Scientist available: true
Rover available: true
Rover battery: 90
Colony-wide oxygen resource total: 245 units
Colony-wide water resource total: 145 units
Colony-wide food resource total: 145 units
Colony-wide fuel resource total: 190 units
Colony-wide battery power total: 140 units
Colony-wide medical supplies total: 50 units
Colony-wide spare parts total: 50 units


========================================
SCENARIO 2: MISSION REJECTION
========================================

Submitting exploration mission with an unsuitable agent...
Mission Status: FAILED
Failure Reason: Mission requirements for Personnel and/or Robots are not satisfied
Is this mission active? false
Engineer available: true
Rover available: true


========================================
SCENARIO 3: RESOURCE SHORTAGE
========================================

Fuel before submission: 190
Required fuel: 5000
Resource Shortage: Not enough Fuel available colony-wide.

Mission Status: FAILED
Failure Reason: Insufficient colony resources for this mission.
Fuel after submission: 190
Were resources unchanged? true
Is this mission active? false


========================================
SCENARIO 4: OXYGEN LEAK EMERGENCY
========================================

Habitat: Habitat A
Oxygen before leak: 245
Habitat operational: true

Oxygen after leak: 225
Habitat repaired successfully.

--- EMERGENCY RESULT ---
Emergency handled: true
Failure Reason: null
Habitat operational: true
Engineer available: true
Repair robot available: true
Oxygen after emergency: 225
========================================
SCENARIO 5: ENVIRONMENTAL EFFECT
========================================
Environment condition is Dust Storm
Attempting exploration during a dust storm...
Exploration missions cannot start because of a dust storm.
Mission rejected due to environmental conditions: Dust Storm
Mission Status: FAILED
Failure Reason: Dust Storm
Is this mission active? false
Environment condition is Normal