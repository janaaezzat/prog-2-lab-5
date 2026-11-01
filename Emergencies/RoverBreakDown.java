package emergencies;
import humanpersonnel.*;
import roboticunits.*;
import java.util.List;
import mission.ExplorationMission;
import mission.MissionStatus;
import mission.RepairMission;

public class RoverBreakDown extends Emergency {

    private ExplorationRover rover; // The rover that has broken down
    private ExplorationMission mission;// The exploration mission that the rover is part of
    private RepairMission repairMission; //Creating the repair mission does not start it automatically

    public RoverBreakDown(String location,ExplorationRover rover,ExplorationMission mission,int emergencyHandleTime) {

        super(location, emergencyHandleTime);

        if (rover == null || mission == null) {
            System.out.println("Invalid rover or mission.");
            return;
        }

        if (!mission.hasRobot(rover)) {
            System.out.println("This rover is not assigned to this mission.");
            return;
        }

        this.rover = rover;
        this.mission = mission;
        //mafee4 exploartuion mission 3la el mars colony 5ales 4a8la 34an el rover ybooz feeha
        if (mission.getStatus() != MissionStatus.IN_PROGRESS) {
        System.out.println("The mission is not in progress.");
         return;
}
        // Apply the breakdown effects to the rover and mission
        applyBreakdown();
    }
  @Override 
 // heya abstract method mn Emergency class so lazem 23mlha override
  // Checks if the rover and mission are valid and if the mission is in progress
  public boolean checkResponders(List<HumanPersonnel> humans,List<RoboticUnits> robots) {

    return rover != null&& mission != null&& mission.getStatus() == MissionStatus.FAILED&& mission.hasRobot(rover);
}
 @Override
public void cantHandleEmergency() {
    status = false;
    failureReason = "Rover breakdown could not be processed.";
}

@Override
public void handledSuccessfully() {
    status = true;
    failureReason = null;
}

 private void applyBreakdown() {
    //totally damaged
    rover.setDamageStatus(100.0);
    rover.setAvailable(false);
    rover.setCurrentLocation(location);
     // Mark the mission as failed due to rover breakdown
    mission.failMission("Exploration rover broke down.");

    // Set the rover's status to unavailable again because mission failure will release the rover and all agents assigned to the mission (still lesa bayez)
    rover.setAvailable(false);
    repairMission = new RepairMission("Repair-" + rover.getID(),2,rover,5, 10, 0, 0, 0, 2);
  }
  public RepairMission getRepairMission() {
        return repairMission;
    }


}
    

