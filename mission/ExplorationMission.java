package mission;
import enviroment.Enviroment;
import humanpersonnel.HumanPersonnel;
import roboticunits.RoboticUnits;

public class ExplorationMission extends Mission {
    //4elt el person and robot lists 34an kanet bt3ml shadowing ll main mission class lists homa aleady byt3amal fehom
    //list of assigned w keda fa lma check req kanet ht2ra kanet htb2a b null 34an bt2ra of main ely kano shadowed
    
    //zawedt dol for execution
    private boolean surfaceExplored = false;

    public ExplorationMission(String missionId, int duration, int fuelNeeded, int batteryNeeded, int foodNeeded, int waterNeeded, int oxygenNeeded) {
        super(missionId, duration, "Surface"); 

        // Initialize required resources for the exploration mission
        addResourceRequirement("Fuel", fuelNeeded);
        addResourceRequirement("Battery", batteryNeeded);
        addResourceRequirement("Food", foodNeeded);
        addResourceRequirement("Water", waterNeeded);   
        addResourceRequirement("Oxygen", oxygenNeeded);
    }
     // 4elt mn hena el instance of 3la asas enohom makano4 3ayzenha fl pdf

    @Override
    public boolean isEnvironment(Enviroment Enviroment) {
        if(Enviroment.getCurrentCond()!= null && Enviroment.getCurrentCond().equals(enviroment.Enviroment.dustStorm)){
            System.out.println("Exploration missions cannot start because of a dust storm.");
            failMission(enviroment.Enviroment.dustStorm);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkRequirements() {
        boolean hasScientistSkill = false;
        for (HumanPersonnel p : assignedPersonnel) {
            if (p.canPerform("Research")) {
                hasScientistSkill = true;
                break;
            }
        }

        boolean hasSuitableVehicle = false;
        for (RoboticUnits r : assignedRobots) {
            if ( r.canPerform("Exploration")) {
                hasSuitableVehicle = true;
                break;
            }
        }

        return hasScientistSkill && hasSuitableVehicle;
    }
    //new getters
    public boolean isSurfaceExplored() {
    return surfaceExplored;
    }

    @Override
    protected boolean executeMission() {
    surfaceExplored = true;
    System.out.println("Exploration of the Surface completed successfully.");

    return true;
}


}

