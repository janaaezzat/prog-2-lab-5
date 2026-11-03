package mission;

import humanpersonnel.HumanPersonnel;
import roboticunits.RoboticUnits;
import enviroment.Enviroment;

public class RescueMission extends Mission {
    private String communicationStationLocation;
    private HumanPersonnel rescuedPerson; //pass object mn el human dah el hanroo7 nnkzo byd5ol ll const
    private boolean rescueCompleted = false;
    private static final double HEALTH_RECOVERY = 30.0;

    public RescueMission(String missionId, int duration,String communicationStationLocation,int foodNeeded,int waterNeeded , int oxygenNeeded, int medicalSuppliesNeeded,HumanPersonnel rescuedPerson) {
        super(missionId, duration,getRescueLocation(rescuedPerson)); //el target location hwa el location ely el person mawgood feha 
        addResourceRequirement("Food", foodNeeded);
        addResourceRequirement("Water", waterNeeded);
        addResourceRequirement("Oxygen", oxygenNeeded);
        addResourceRequirement("MedicalSupplies", medicalSuppliesNeeded);

        this.rescuedPerson = rescuedPerson;
        this.communicationStationLocation = communicationStationLocation;
    }

    private static String getRescueLocation(HumanPersonnel person) {
    if (person == null) {
        throw new IllegalArgumentException("Rescued person cannot be null.");
    }
    return person.getCurrentLocation();
    }

    @Override
    public boolean checkRequirements() {
        boolean hasMedic = false;
        for (HumanPersonnel p : assignedPersonnel) {
            if (p.canPerform("Medical")) {
                hasMedic = true;
                break;
            }
        }

        boolean hasDrone = false;
        for (RoboticUnits r : assignedRobots) {
            // Checks if it's a robotic unit capable of communication/rescue
            if ((r.canPerform("Communication") && r.canPerform("Rescue"))) {
                hasDrone = true;
                break;
            }
        }

        return hasMedic && hasDrone;
    }
    public boolean isRescueCompleted() {
        return rescueCompleted;
    }

    @Override
    protected boolean executeMission() {
    if (rescuedPerson == null) {
            return false;
        }
        rescuedPerson.setCurrentLocation(communicationStationLocation);
        rescuedPerson.increaseHealth(HEALTH_RECOVERY); //nezwed el health ka treatment w heya already gowaha el validation (increaseHealth)
        rescueCompleted = true;
        System.out.println("Rescue mission completed successfully.");
        System.out.println(rescuedPerson.getName() +" was rescued and transported to " + communicationStationLocation + ".");
        System.out.println("Health after treatment: " + rescuedPerson.getHealthStatus());
        return true;
    }

    @Override
    public boolean isEnvironment(Enviroment Enviroment ) {
        if(Enviroment.getCurrentCond()!= null && Enviroment.getCurrentCond().equals(enviroment.Enviroment.commDisruption)){
            System.out.println("Rescue missions cannot start because of a communication disruption.");
            failMission(enviroment.Enviroment.commDisruption);
            return false;
        }
        return true;
    }
}
