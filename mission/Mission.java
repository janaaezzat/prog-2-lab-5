package mission;
import HumanPersonnel.HumanPersonnel;
import RoboticUnits.RoboticUnits;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Mission {
    private String missionId; // nefr2 bein el missions
    private int duration; //34an mission t5ls me7taga kam unit time
    private int elapsedTime;// el wa2t ely 3ada mn bedayet el mission starting mn 0
    // hn use it fi el timing management elapsedTime >= duration keda el mission completed 
    private MissionStatus status;
    private String failureReason;
    protected String targetLocation; //el location ely el mission hatet3ml feha

    protected List<HumanPersonnel> assignedPersonnel;//el personnel participated in mission
    protected List<RoboticUnits> assignedRobots;//el robots participated in mission

    protected Map<String, Integer> requiredResources; //deh keda 34an ex "Fuel" -> 50, "Battery" -> 10 fa ye7ot e7tyagat kol mission w tet2ara ba3dein

 public Mission(String missionId, int duration, String targetLocation) {
        this.missionId = missionId;
        this.duration = duration;
        this.elapsedTime = 0; // lesa mabd2na4 n3ed
        this.status = MissionStatus.ASSIGNED; 
        this.failureReason = "";
        this.targetLocation = targetLocation;

        this.assignedPersonnel = new ArrayList<>();// hn add feha 2odam 
        this.assignedRobots = new ArrayList<>();
        this.requiredResources = new HashMap<>();
    }

public String getMissionId() {
    return missionId;
   }

public int getDuration() {
    return duration;
}

public int getElapsedTime() {
    return elapsedTime;
}

public MissionStatus getStatus() {
    return status;
    //colony can use it 34an t3rf el status directly
}

public String getFailureReason() {
    return failureReason;
} 

public void addPersonnel(HumanPersonnel person) {//deh el 4elt 34anha el private list
    assignedPersonnel.add(person);
}

public void addRobot(RoboticUnits robot) {
    assignedRobots.add(robot);
}

//deh hena el subclasses (el missions ely htb2a 3ndna) hy3mlha override 34an y7ddo el requirements ely kol mission me7taga
//deh el settup bta3 list of req

public void addResourceRequirement(String resourceName, int amount) {
        if (amount > 0) {
            requiredResources.put(resourceName, amount);
        }
    }
//its getter
    public Map<String, Integer> getRequiredResources() {
        return requiredResources;
    }

    //mission hat handle enaha te3adel their locations for later
    public void updateAgentLocations() {
        for (HumanPersonnel p : assignedPersonnel) {
            p.setCurrentLocation(targetLocation);
        }
        for (RoboticUnits r : assignedRobots) {
            r.setCurrentLocation(targetLocation);
        }
    }

//start bt8ayar mission state w agent states
public void startMission() {
    //el colony ht call it lw kol el requirements tmm w mafee4 active mission
    if (status == MissionStatus.ASSIGNED) {
        status = MissionStatus.IN_PROGRESS;
        updateAgentLocations();
        for (HumanPersonnel p : assignedPersonnel) {
            p.setAvailable(false);
        }
        for (RoboticUnits r : assignedRobots) {
            r.setAvailable(false);
        }
    }
}

public void advanceTime() {
//colony bt call it
    if (status == MissionStatus.IN_PROGRESS) {
       // y3ny lw el mission already bad2t bnsm7 ll wa2t eno y3dy 

        elapsedTime++;// zawed one unit

        if (elapsedTime >= duration) {
            completeMission();
        }
        for (HumanPersonnel p : assignedPersonnel) {
             if (!p.isAvailable()) { // If they are working on a mission
                p.increaseFatigue(2.0); // Fatigue ticks up every time unit
            }
        }

        for (RoboticUnits r : assignedRobots) {
            if (!r.isAvailable()) { // If the robot is active
                r.drainBattery(5); // Battery drains every time unit
            }
        }
    }
 }

//keda complete aw fail el mission w release el personnel w el robots 34an yest5dmo fe missions tanya
public void completeMission() {
    if (status == MissionStatus.IN_PROGRESS) {
        status = MissionStatus.COMPLETED;
        releaseAgents();
    }
}
//deh el validation en feh b2a el 7agat ely el list m7tagaha
public abstract boolean checkRequirements();//el missions (subclasses )ht3mlha override 7asab its requirements


public void failMission(String reason) {
    if (status != MissionStatus.COMPLETED &&
        status != MissionStatus.FAILED) {
     //bygely reason el failure
        status = MissionStatus.FAILED;
        failureReason = reason;
        releaseAgents();
    }
}
private void releaseAgents() {//handles enaha ba3d ma el mission t5ls aw failed el personnel w el robots ely est5dmto yb2o available 34an yest5dmo fe missions tanya
    for (HumanPersonnel p : assignedPersonnel) {
        p.setAvailable(true);
    }
    for (RoboticUnits r : assignedRobots) {
        r.setAvailable(true);
    }
}
}