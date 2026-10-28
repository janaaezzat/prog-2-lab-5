package mission;

public class RescueMission extends Mission {
    private String rescueLocation;

    public RescueMission(String missionId, int duration, String rescueLocation) {
        super(missionId, duration);
        this.rescueLocation = rescueLocation;
    }

    public String getRescueLocation() {
        return rescueLocation;
    }

    @Override
    public boolean checkRequirements() {
        // Example requirement: The rescue location should not be empty
        return rescueLocation != null && !rescueLocation.isEmpty();
    }
    
}
