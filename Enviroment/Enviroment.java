package enviroment;
import mission.ExplorationMission;
import mission.Mission;
import mission.RescueMission;

public class Enviroment {
    public static final String normal="Normal";
    public static final String dustStorm="Dust Storm";
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


    public boolean canContinueMission(Mission mission) {
        switch (currentCond) {
            case dustStorm:
                if (mission instanceof ExplorationMission) {
                    System.out.println("Exploration mission interrupted due to a dust storm.");
                    return false;
                }
                break;

            case commDisruption:
                if (mission instanceof RescueMission) {
                    System.out.println("Rescue mission interrupted due to communication disruption.");
                    return false;
                }
                break;
        }

        return true;
    }

}