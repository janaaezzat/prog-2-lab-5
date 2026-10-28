package Enviroment;
import mission.ExplorationMission;
import mission.Mission;
import RoboticUnits.RoboticUnits;
import RoboticUnits.ExplorationRover;
import RoboticUnits.Drone;
import mission.RescueMission;

public class Enviroment {
    public static final String normal="Normal";
    public static final String dustStorm=" Dust Storm";
    public static final String commDisruption="Communication Disruption";
    private String currentCond;
    public Enviroment(){
        this.currentCond=normal;
    }

    public String getCurrentCond() {
        return currentCond;
    }

    public void setCurrentCond(String currentCond) {
        if(currentCond.equals(normal)||currentCond.equals(dustStorm)||currentCond.equals(commDisruption))
        {
            this.currentCond = currentCond;
            System.out.println("Environment condition is "+currentCond);
        }
        else
            System.out.println("Invalid environment condition");

    }
    public boolean startmission(Mission mission)
    {
        switch (currentCond){
            case dustStorm:
                if(mission instanceof ExplorationMission){
                    System.out.println("Exploration missions cannot start because of a dust storm");
                    return false;
                }
                break;
            case commDisruption:
                if(mission instanceof RescueMission){
                    System.out.println("Rescue missions cannot start because of a communication disruption");
                    return false;
                }
                break;
            case normal:
            default:
                return true;
        }
        return true;
    }
    public boolean operateRobot(RoboticUnits roboticUnits){
        switch (currentCond)
        {
            case dustStorm:

                if(roboticUnits instanceof ExplorationRover){
                    System.out.println("Exploration Rover [" + roboticUnits.getID() + "] operations restricted due to dust storm[cite: 13].");
                    return false;
                }
                break;
            case commDisruption:
                if(roboticUnits instanceof Drone){
                    System.out.println("Drone [" + roboticUnits.getID() + "] operation failed. Reason: Remote drones cannot be controlled during communication disruption[cite: 13].");
                    return false;
                }
                break;
            case normal:
            default:
                return true;
        }
        return true;
    }
}
