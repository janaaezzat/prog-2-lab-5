package mission;
import HumanPersonnel.HumanPersonnel;
import RoboticUnits.ExplorationRover;
import RoboticUnits.RoboticUnits;
import java.util.List;
import java.util.ArrayList;

public class ExplorationMission extends Mission {

    private List<HumanPersonnel> personnel;//bet5azen el personnel elly el mission hatesta5demhom
    private List<RoboticUnits> robots;//bet5azen el robots elly hatesharek
    public ExplorationMission(String missionId, int duration, int fuelNeeded, int batteryNeeded) {
        super(missionId, duration);
        personnel = new ArrayList<>(); //creating list fadya then a3mel .add()lma 23oz 27ot gowaha
        robots = new ArrayList<>();

        // Initialize required resources for the exploration mission
        addResourceRequirement("Fuel", fuelNeeded);
        addResourceRequirement("Battery", batteryNeeded);
    }

    public void addPersonnel(HumanPersonnel person) {
        personnel.add(person);
    }

    public void addRobot(RoboticUnits robot) {
        robots.add(robot);
    }

    @Override
    public boolean checkRequirements() {
    if (personnel.isEmpty() || robots.isEmpty()) {//ye4ofhom fadyeen wla eh first
        return false;
    }

    for (HumanPersonnel p : personnel) {
        if (!p.canPerform("Research") && !p.canPerform("Exploration")) {//ba3dha yet2aked mn el personnel el skill wl health
            return false;
        }
    }
   
    boolean hasValidRover = false;
    for (RoboticUnits r : robots) {
        if (r instanceof ExplorationRover) {//w nafso ll rover 
            if (r.canPerform("Exploration")) {
                hasValidRover = true;
                break;
            }
        }
    }

    return hasValidRover;
}
}
