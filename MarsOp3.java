//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Arrays;

import facilities.Facilities;
import facilities.Habitat;
import humanpersonnel.Engineer;
import humanpersonnel.Scientist;
import roboticunits.ExplorationRover;
import roboticunits.RepairRobot;

import mission.ExplorationMission;

import emergencies.OxygenLeak;

import enviroment.Enviroment;

public class MarsOp3 {

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("       MARS-OPS COLONY SIMULATION");
        System.out.println("========================================");

        MarsColony colony = new MarsColony();

        // Create one operational habitat containing colony resources.
        Habitat habitat = new Habitat(
                "Habitat A",
                1500,
                true,
                250,  // Oxygen
                150,  // Water
                150,  // Food
                200,  // Fuel
                150,  // Battery
                50,   // Medical supplies
                50    // Spare parts
        );

        colony.addFacility(habitat);

        // Create personnel.
        Scientist scientist = new Scientist(
                "Alex",
                "S001",
                "Habitat A",
                Arrays.asList("Research"),
                100
        );

        Engineer engineer = new Engineer(
                "Sam",
                "E001",
                "Habitat A",
                Arrays.asList("Repair"),
                100
        );

        colony.addHuman(scientist);
        colony.addHuman(engineer);

        // Create robots.
        ExplorationRover rover = new ExplorationRover(
                "R001",
                "Habitat A",
                100,
                0,
                Arrays.asList("Exploration")
        );

        RepairRobot repairRobot = new RepairRobot(
                "RR001",
                "Habitat A",
                100,
                0,
                Arrays.asList("Repair")
        );

        colony.addRobot(rover);
        colony.addRobot(repairRobot);

        System.out.println("\nColony setup completed.");

        System.out.println("\nInitial Colony Status:");
        colony.colonyStatusReport();

        System.out.println("\n\n========================================");
        System.out.println("SCENARIO 1: SUCCESSFUL EXPLORATION");
        System.out.println("========================================");

        ExplorationMission exploration = new ExplorationMission(
                "EXP-001",
                2,      // Duration
                10,     // Fuel
                10,     // Battery
                5,      // Food
                5,      // Water
                5       // Oxygen
        );

        // Assign the required scientist and exploration rover.
        exploration.addPersonnel(scientist);
        exploration.addRobot(rover);

        System.out.println("\n--- BEFORE EXECUTION ---");

        System.out.println("Mission ID: " + exploration.getMissionId());
        System.out.println("Mission Status: " + exploration.getStatus());
        System.out.println("Surface explored: " + exploration.isSurfaceExplored());
        System.out.println("Scientist available: " + scientist.isAvailable());
        System.out.println("Rover available: " + rover.isAvailable());

        colony.submitMission(exploration);

        // Advance time until the mission finishes.
        colony.advanceTime(2);

        System.out.println("\n--- AFTER EXECUTION ---");

        System.out.println("Mission Status: " + exploration.getStatus());
        System.out.println("Surface explored: " + exploration.isSurfaceExplored());
        System.out.println("Scientist available: " + scientist.isAvailable());
        System.out.println("Rover available: " + rover.isAvailable());
        System.out.println("Rover battery: " + rover.getBattery());

        colony.colonyStatusReport();

        System.out.println("\n\n========================================");
        System.out.println("SCENARIO 2: MISSION REJECTION");
        System.out.println("========================================");

        ExplorationMission rejectedMission = new ExplorationMission(
                "EXP-002",
                2,
                10,
                10,
                5,
                5,
                5
        );

        // Deliberately assign an engineer instead of a scientist.
        // The engineer does not have the Research capability.
        rejectedMission.addPersonnel(engineer);
        rejectedMission.addRobot(rover);

        System.out.println("\nSubmitting exploration mission with an unsuitable agent...");

        colony.submitMission(rejectedMission);

        System.out.println("Mission Status: " + rejectedMission.getStatus());
        System.out.println("Failure Reason: " + rejectedMission.getFailureReason());

        System.out.println("Is this mission active? "
                + (colony.getActiveMission() == rejectedMission));

        System.out.println("Engineer available: " + engineer.isAvailable());
        System.out.println("Rover available: " + rover.isAvailable());


        System.out.println("\n\n========================================");
        System.out.println("SCENARIO 3: RESOURCE SHORTAGE");
        System.out.println("========================================");

        ExplorationMission shortageMission = new ExplorationMission(
                "EXP-003",
                2,
                5000,   // Deliberately require more fuel than available
                10,
                5,
                5,
                5
        );

        shortageMission.addPersonnel(scientist);
        shortageMission.addRobot(rover);

        int fuelBefore = Facilities.getFuelTotal();

        System.out.println("\nFuel before submission: " + fuelBefore);
        System.out.println("Required fuel: 5000");

        colony.submitMission(shortageMission);

        int fuelAfter = Facilities.getFuelTotal();

        System.out.println("\nMission Status: " + shortageMission.getStatus());
        System.out.println("Failure Reason: " + shortageMission.getFailureReason());
        System.out.println("Fuel after submission: " + fuelAfter);

        System.out.println("Were resources unchanged? "
                + (fuelBefore == fuelAfter));

        System.out.println("Is this mission active? "
                + (colony.getActiveMission() == shortageMission));

        System.out.println("\n\n========================================");
        System.out.println("SCENARIO 4: OXYGEN LEAK EMERGENCY");
        System.out.println("========================================");

        int oxygenBefore = habitat.getOxygen();

        System.out.println("\nHabitat: " + habitat.getLocation());
        System.out.println("Oxygen before leak: " + oxygenBefore);
        System.out.println("Habitat operational: " + habitat.getOperationalStatus());

        // Create an oxygen leak that loses 20 oxygen units.
        OxygenLeak oxygenLeak = new OxygenLeak(
                habitat,
                20,
                2
        );

        System.out.println("\nOxygen after leak: " + habitat.getOxygen());

        // The colony searches for an engineer and repair robot.
        colony.reportEmergency(oxygenLeak);

        System.out.println("\n--- EMERGENCY RESULT ---");

        System.out.println("Emergency handled: " + oxygenLeak.getStatus());
        System.out.println("Failure Reason: " + oxygenLeak.getFailureReason());
        System.out.println("Habitat operational: " + habitat.getOperationalStatus());
        System.out.println("Engineer available: " + engineer.isAvailable());
        System.out.println("Repair robot available: " + repairRobot.isAvailable());
        System.out.println("Oxygen after emergency: " + habitat.getOxygen());


        System.out.println("========================================");
        System.out.println("SCENARIO 5: ENVIRONMENTAL EFFECT");
        System.out.println("========================================");

        // 1. Set environment to Dust Storm
        Enviroment environment=new Enviroment();
        environment.setCurrentCond(Enviroment.dustStorm);

        // 2. Create your exploration mission
        ExplorationMission exp004 = new ExplorationMission("EXP-004", 3, 10, 10, 5, 5, 5);

        System.out.println("Attempting exploration during a dust storm...");

        // 3. CHECK THE ENVIRONMENT FIRST!
        if (!exp004.isEnvironment(environment) || !environment.startmission(exp004)) {
            // If environment blocks it, fail the mission cleanly
            exp004.failMission("Mission cannot start under current environmental conditions.");
        } else {
            exp004.startMission();
        }

        // 4. Print status to verify it failed and is NOT active
        System.out.println("Mission Status: " + exp004.getStatus());
        System.out.println("Failure Reason: " + exp004.getFailureReason());
        System.out.println("Is this mission active? " + (colony.getActiveMission() == exp004));

        // Reset environment back to normal for the rest of the simulation
        environment.setCurrentCond(Enviroment.normal);


        System.out.println("\n\n========================================");
        System.out.println("       FINAL COLONY STATUS");
        System.out.println("========================================");

        colony.colonyStatusReport();

        System.out.println("\nActive mission: " + colony.getActiveMission());

        System.out.println("\nAll five required demonstration scenarios finished.");
    }

    
}
