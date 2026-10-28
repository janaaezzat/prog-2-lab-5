package mission;

import java.util.List;
import java.util.ArrayList;

public class ExplorationMission extends Mission {

    private List<HumanPersonnel> personnel;//bet5azen el personnel elly el mission hatesta5demhom
    private List<RoboticUnits> robots;//bet5azen el robots elly hatesharek
    public ExplorationMission(String missionId, int duration) {
        super(missionId, duration);
        personnel = new ArrayList<>(); //creating list fadya then a3mel .add()lma 23oz 27ot gowaha
        robots = new ArrayList<>();
    }

    public void addPersonnel(HumanPersonnel person) {
        personnel.add(person);
    }

    public void addRobot(RoboticUnits robot) {
        robots.add(robot);
    }

    @Override
    public boolean checkRequirements() {
 // check requirements
       
    
    }

}
