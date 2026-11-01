package mission;

import humanpersonnel.*;
import roboticunits.RoboticUnits;
import facilities.Facilities;

public class RepairMission extends Mission {
    private String repairLocation;
    private Facilities targetFacility;
    private RoboticUnits targetRobot;

    public RepairMission(String missionId, int duration, Object target,//hena zawedna fl const el object target 34an nedelo el robot aw el facility el 3ayznha ttsala7
                     int fuelNeeded, int batteryNeeded,
                     int foodNeeded, int waterNeeded,
                     int oxygenNeeded, int sparePartsNeeded) {
        
    super(missionId, duration, getTargetLocation(target));
    //get target location 34an neb2a 3arfeen e7na ray7een fein 
    if (target instanceof Facilities) {
        this.targetFacility = (Facilities) target; //casting
        this.targetRobot = null;
        this.repairLocation = targetFacility.getLocation();
    }
    else if (target instanceof RoboticUnits) {
        this.targetRobot = (RoboticUnits) target;
        this.targetFacility = null;
        this.repairLocation = targetRobot.getCurrentLocation();
    }

    addResourceRequirement("Fuel", fuelNeeded);
    addResourceRequirement("Battery", batteryNeeded);
    addResourceRequirement("Food", foodNeeded);
    addResourceRequirement("Water", waterNeeded);
    addResourceRequirement("Oxygen", oxygenNeeded);
    addResourceRequirement("SpareParts", sparePartsNeeded);
}

    private static String getTargetLocation(Object target) {
    if (target instanceof Facilities) {
        return ((Facilities) target).getLocation();
    }
    if (target instanceof RoboticUnits) {
        return ((RoboticUnits) target).getCurrentLocation();
    }
    throw new IllegalArgumentException("Invalid repair target.");//a7san mn print and return (garbtaha wasn't a good option )
} //get locations of everything

    public String getRepairLocation() {
        return repairLocation;
    }

    @Override
    public boolean checkRequirements() {
        // Example requirement: The repair location should not be empty
        boolean hasEngineerSkill = false;
        for (HumanPersonnel p : assignedPersonnel) {
            if (p.canPerform("Repair")) {
                hasEngineerSkill = true;
                break;
            }
        }

        boolean hasRepairRobot = false;
        for (RoboticUnits r : assignedRobots) {//added r not target maynfa34 el assigned robot yeb2a hwa el ta3ban
            if ( r != targetRobot  && r.canPerform("Repair")) { // Assumes repair robot checks "Repair" capability
                hasRepairRobot = true;
                break;
            }
        }
        //zawedt deh to check facility wla robot
        boolean validTarget = false;

            if (targetFacility != null) {
                validTarget = !targetFacility.getOperationalStatus();
            }
            else if (targetRobot != null) {
                validTarget = targetRobot.getDamageStatus() > 0;
            }

    return hasEngineerSkill && hasRepairRobot && validTarget;}

    @Override
    protected boolean executeMission() {//deh el logic nafso only accept a false operational to make it true w nafs el kalam ll robot
    if (targetFacility != null) {
        if (targetFacility.getOperationalStatus()) {
            return false;
        }
        targetFacility.setOperationalStatus(true);
        System.out.println("Facility at "+ repairLocation + " has been repaired.");
        return true;
    }
    if (targetRobot != null) {
        if (targetRobot.getDamageStatus() <= 0) {
            return false;
        }
        targetRobot.setDamageStatus(0);
        targetRobot.setAvailable(true);
        targetRobot.rechargeBattery(100);
        System.out.println("Robot "+ targetRobot.getID() + " has been repaired.");
        return true;
    }

    return false;
}



}
    
