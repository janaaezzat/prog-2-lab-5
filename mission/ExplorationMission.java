package mission;
import HumanPersonnel.HumanPersonnel;
import HumanPersonnel.Scientist;
import RoboticUnits.ExplorationRover;
import RoboticUnits.RoboticUnits;

public class ExplorationMission extends Mission {
    //4elt el person and robot lists 34an kanet bt3ml shadowing ll main mission class lists homa aleady byt3amal fehom
    //list of assigned w keda fa lma check req kanet ht2ra kanet htb2a b null 34an bt2ra of main ely kano shadowed
    
    public ExplorationMission(String missionId, int duration, int fuelNeeded, int batteryNeeded, int foodNeeded, int waterNeeded, int oxygenNeeded) {
        super(missionId, duration, "Surface"); 

        // Initialize required resources for the exploration mission
        addResourceRequirement("Fuel", fuelNeeded);
        addResourceRequirement("Battery", batteryNeeded);
        addResourceRequirement("Food", foodNeeded);
        addResourceRequirement("Water", waterNeeded);   
        addResourceRequirement("Oxygen", oxygenNeeded);
    }

    @Override
    public boolean checkRequirements() {
        boolean hasScientist = false;
        for (HumanPersonnel p : assignedPersonnel) {
            if (p instanceof Scientist && p.canPerform("Research")) {
                hasScientist = true;
                break;
            }
        }

        boolean hasRover = false;
        for (RoboticUnits r : assignedRobots) {
            if (r instanceof ExplorationRover && r.canPerform("Exploration")) {
                hasRover = true;
                break;
            }
        }

        return hasScientist && hasRover;
    }
}

