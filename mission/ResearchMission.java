package mission;

public class ResearchMission extends Mission {
    private String researchTopic;

    public ResearchMission(String missionId, int duration, String researchTopic) {
        super(missionId, duration);
        this.researchTopic = researchTopic;
    }

    public String getResearchTopic() {
        return researchTopic;
    }

    @Override
    public boolean checkRequirements() {
        // Example requirement: The research topic should not be empty
        return researchTopic != null && !researchTopic.isEmpty();
    }
    
}
