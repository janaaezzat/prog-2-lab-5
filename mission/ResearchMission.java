package mission;

import humanpersonnel.HumanPersonnel;
import facilities.Laboratories;
import enviroment.Enviroment;

public class ResearchMission extends Mission {
    private Laboratories laboratory;
    private boolean researchCompleted = false;

    public ResearchMission(String missionId, int duration, Laboratories laboratory, int foodNeeded, int waterNeeded, int oxygenNeeded, int fuelNeeded) {
        super(missionId, duration, getLabLocation(laboratory));
        this.laboratory = laboratory;
        addResourceRequirement("Food", foodNeeded);
        addResourceRequirement("Water", waterNeeded);
        addResourceRequirement("Oxygen", oxygenNeeded);
        addResourceRequirement("Fuel", fuelNeeded);
    }

    private static String getLabLocation(Laboratories laboratory) {
    if (laboratory == null) {
        throw new IllegalArgumentException("Laboratory cannot be null.");
        }
        return laboratory.getLocation();
    }

    public boolean isResearchCompleted() {
    return researchCompleted;
    }

    @Override
    public boolean checkRequirements() {
    
    if (!laboratory.getOperationalStatus()) {
        return false;
        }
    
    for (HumanPersonnel p : assignedPersonnel) {
        if (p.canPerform("Research")) {
            return true;
            }
        }
        return false;
    }

    @Override
    protected boolean executeMission() {
    researchCompleted = true;
    System.out.println("Research mission completed successfully at " + laboratory.getLocation() + ".");
    return true;
    }
    public boolean isEnvironment(Enviroment Enviroment) {
        return true;
    }
    
    
}
