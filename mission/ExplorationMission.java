package mission;
import HumanPersonnel.HumanPersonnel;
import HumanPersonnel.Scientist;
import RoboticUnits.ExplorationRover;
import RoboticUnits.RoboticUnits;
import java.util.List;
import java.util.ArrayList;

public class ExplorationMission extends Mission {

    private List<HumanPersonnel> personnel;  //bet5azen el personnel elly el mission hatesta5demhom
    private List<RoboticUnits> robots;  //bet5azen el robots elly hatesharek
    
    public ExplorationMission(String missionId, int duration, int fuelNeeded, int batteryNeeded, int foodNeeded, int waterNeeded, int oxygenNeeded) {
        super(missionId, duration, "Surface"); 
        personnel = new ArrayList<>();  //creating list fadya then a3mel .add()lma 23oz 27ot gowaha
        robots = new ArrayList<>();

        // Initialize required resources for the exploration mission
        addResourceRequirement("Fuel", fuelNeeded);
        addResourceRequirement("Battery", batteryNeeded);
        addResourceRequirement("Food", foodNeeded);
        addResourceRequirement("Water", waterNeeded);   
        addResourceRequirement("Oxygen", oxygenNeeded);
    }

    public void addPersonnel(HumanPersonnel person) {
        personnel.add(person);
    }

    public void addRobot(RoboticUnits robot) {
        robots.add(robot);
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

