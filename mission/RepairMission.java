package mission;

public class RepairMission extends Mission {
    private String repairLocation;

    public RepairMission(String missionId, int duration, String repairLocation) {
        super(missionId, duration);
        this.repairLocation = repairLocation;
    }

    public String getRepairLocation() {
        return repairLocation;
    }

    @Override
    public boolean checkRequirements() {
        // Example requirement: The repair location should not be empty
        return repairLocation != null && !repairLocation.isEmpty();
    }
    
}
