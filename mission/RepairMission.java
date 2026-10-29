package mission;

import HumanPersonnel.Engineer;
import HumanPersonnel.HumanPersonnel;
import RoboticUnits.RepairRobot;
import RoboticUnits.RoboticUnits;

public class RepairMission extends Mission {
    private String repairLocation;

    public RepairMission(String missionId, int duration, String repairLocation, int fuelNeeded, int batteryNeeded, int foodNeeded, int waterNeeded, int oxygenNeeded, int sparePartsNeeded) {
        super(missionId, duration, repairLocation);
        this.repairLocation = repairLocation;
        addResourceRequirement("Fuel", fuelNeeded);
        addResourceRequirement("Battery", batteryNeeded);
        addResourceRequirement("Food", foodNeeded);
        addResourceRequirement("Water", waterNeeded);
        addResourceRequirement("Oxygen", oxygenNeeded);
        addResourceRequirement("SpareParts", sparePartsNeeded);
    }

    public String getRepairLocation() {
        return repairLocation;
    }

    @Override
    public boolean checkRequirements() {
        // Example requirement: The repair location should not be empty
        boolean hasEngineer = false;
        for (HumanPersonnel p : assignedPersonnel) {
            if (p instanceof Engineer && p.canPerform("Repair")) {
                hasEngineer = true;
                break;
            }
        }

        boolean hasRepairRobot = false;
        for (RoboticUnits r : assignedRobots) {
            if (r instanceof RepairRobot && r.canPerform("Repair")) { // Assumes repair robot checks "Repair" capability
                hasRepairRobot = true;
                break;
            }
        }

        return hasEngineer && hasRepairRobot && repairLocation != null && !repairLocation.isEmpty();
    }
}
    
