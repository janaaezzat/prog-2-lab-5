package mission;

import HumanPersonnel.HumanPersonnel;
import HumanPersonnel.MedicalOfficer;
import RoboticUnits.RoboticUnits;
import RoboticUnits.Drone;

public class RescueMission extends Mission {

    public RescueMission(String missionId, int duration, String rescueLocation,int foodNeeded,int waterNeeded , int oxygenNeeded, int medicalSupppliesNeeded) {
        super(missionId, duration,rescueLocation);
    }

    @Override
    public boolean checkRequirements() {
        boolean hasMedic = false;
        for (HumanPersonnel p : assignedPersonnel) {
            if (p instanceof MedicalOfficer && p.canPerform("Medical")) {
                hasMedic = true;
                break;
            }
        }

        boolean hasDrone = false;
        for (RoboticUnits r : assignedRobots) {
            // Checks if it's a robotic unit capable of communication/rescue
            if (r instanceof Drone && r.canPerform("Communication") || r.canPerform("Rescue")) {
                hasDrone = true;
                break;
            }
        }

        return hasMedic && hasDrone;
    }
    
}
