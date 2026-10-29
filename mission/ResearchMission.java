package mission;

import HumanPersonnel.HumanPersonnel;
import HumanPersonnel.Scientist;

public class ResearchMission extends Mission {
    private String labLocation;

    public ResearchMission(String missionId, int duration, String labLocation, int foodNeeded, int waterNeeded, int oxygenNeeded, int fuelNeeded) {
        super(missionId, duration, labLocation);
        this.labLocation = labLocation;
        addResourceRequirement("Food", foodNeeded);
        addResourceRequirement("Water", waterNeeded);
        addResourceRequirement("Oxygen", oxygenNeeded);
        addResourceRequirement("Fuel", fuelNeeded);
    }

    @Override
    public boolean checkRequirements() {
        if (assignedPersonnel.isEmpty()) return false;
        for (HumanPersonnel p : assignedPersonnel) {
            if (!(p instanceof Scientist) || !p.canPerform("Research")) {
                return false;
            }
        }
        return true;
    }
    
}
