import java.util.ArrayList;
import java.util.List;
import facilities.Facilities;
import humanpersonnel.HumanPersonnel;
import roboticunits.RoboticUnits;
import java.util.Map;
import mission.Mission;
import mission.MissionStatus;
import emergencies.Emergency;
import enviroment.Enviroment;

public class MarsColony {
    // field mn noo3 Mission (el data type) ya3ni el variable da hayeshawer 3ala object men Mission aw subclass menha
    private Mission activeMission;// variable hy4eel 2sm el mission ely 4a8ala now , lw no active missions hy4awer 3ala null
    private List<Facilities> facilities;//hatlef 3la facilities t3raf homa meen 34an later hatnady mnhom for resources
    private List<HumanPersonnel>humans;
    private List<RoboticUnits> robots;
    private Enviroment enviroment; //new Because currentCond is an instance variable in Enviroment, so each colony needs an environment object to remember its current conditions.

    public MarsColony() {
        this.facilities = new ArrayList<>();
        this.humans=new ArrayList<>();
        this.robots=new ArrayList<>();
        this.activeMission = null;
        this.enviroment = new Enviroment();//new 
    }

    //these 3 methods will be used by client class(client should first create an object then pass it to these 
    // methods)to add facility/human/robot to the colony
    public void addFacility(Facilities facility) {
        if (facility != null) {
            facilities.add(facility);
        }
    }
    public void addHuman(HumanPersonnel human) {
        if (human!= null) {
            humans.add(human);
        }
    }
    public void addRobot(RoboticUnits robot) {
        if (robot != null) {
            robots.add(robot);
        }
    }
    //methods to assign a list of humans/robots to a facility->el client beyet3amel ma3 el colony class fa 7aycall el method da wel 
    // colony heya el 7atet3amel w t call addPersonnel aw addRoboticUnit el mawgoodeen f class el facilities
    public void assignHumansToFacility(Facilities facility, List<HumanPersonnel> humanList) {
            //client class passes a list of type human personnel and passes a facility object to this method 
             if (facility != null && humanList != null) {
            for (HumanPersonnel human : humanList) {
                facility.addPersonnel(human); //el method da f facility class
            }
        }
    }
    public void assignRobotsToFacility(Facilities facility, List<RoboticUnits> robotList) {
        if (facility != null && robotList != null) {
            for (RoboticUnits robot : robotList) {
                facility.addRoboticUnits(robot); //el method da f facility class
            }
        }
    }
    //setters and getters for environment condition
    public void setEnvironmentCondition(String condition) {
        enviroment.setCurrentCond(condition);
    }

    public String getEnvironmentCondition() {
        return enviroment.getCurrentCond();
    }


    //el method deh hast2bal mission gedida, w tet2aked en momken tebda2 wala la2
    public void submitMission(Mission mission) {

    if (activeMission != null) {
        mission.failMission("Another mission is already active");
        return; // maynf34 aktar mn mission active 7ata me4 hkmel ba2y checking requirments
    }
    //new


    if (!mission.checkRequirements()) {
        mission.failMission("Mission requirements for Personnel and/or Robots are not satisfied");
        return; // mission mal2t4 its needed req to start
    }
    //zadet 34an check of requirements
    boolean resourcesSecured = consumeColonyResources(mission.getRequiredResources());
    if (!resourcesSecured) {
        mission.failMission("Insufficient colony resources for this mission.");
        return;
    }


   // 8eir keda lw rl requirements tmm w no active missions yeb2a el mission tbd2
    activeMission = mission;
    mission.startMission();
    System.out.println("Mission " + mission.getMissionId() + " successfully started!");
}
  
// Ne7arrak el simulation time
public void advanceTime(int units) {
    if (units < 0) {
        System.out.println("Time units cannot be negative.");
        return;
    }

    for (int i = 0; i < units; i++) {

        if (activeMission != null) {
           //bet2ool lel mission el shaghala en wa2t wa7ed (unit y3ny) 3adda (new) checks for environment kol 4owaya
        if (!enviroment.canContinueMission(activeMission)) {
            activeMission.failMission("Mission interrupted due to environmental conditions.");
    }
    else {
        activeMission.advanceTime();
    }
        }

        if (activeMission != null && (activeMission.getStatus() == MissionStatus.COMPLETED || activeMission.getStatus() == MissionStatus.FAILED)) {
             //Law el mission khalaset aw failed, ben5ally activeMission = null 3ashan el colony te2dar testa2bal mission gedeeda
            System.out.println("Mission " + activeMission.getMissionId() + " ended with status: " + activeMission.getStatus());
             activeMission = null;
        }
    }
}

//34an ay class tanya te2dar tes2al el colony eh el mission elly active delwa2ty?
public Mission getActiveMission() {
    return activeMission; // lw no mission active hyrg3 null
}

// deh el hatbda2 te4oof el requirements for missions colony wide
public boolean consumeColonyResources(Map<String, Integer> requirements) {
    // 1. PRE-CHECK: Verify if colony-wide totals have enough for EVERY requested resource
    for (Map.Entry<String, Integer> entry : requirements.entrySet()) {//by2ra el resources mn el hashmap bta3t kol mission w yet2aked en el colony 3ndha kfaya mn kol resource
        String resName = entry.getKey();
        int needed = entry.getValue();
        int availableTotal = 0;

        switch (resName) {
            case "Oxygen": availableTotal = Facilities.getOxygenTotal(); break;
            case "Water": availableTotal = Facilities.getWaterTotal(); break;
            case "Food": availableTotal = Facilities.getFoodTotal(); break;
            case "Fuel": availableTotal = Facilities.getFuelTotal(); break;
            case "Battery": availableTotal = Facilities.getBatteryTotal(); break;
            case "MedicalSupplies": availableTotal = Facilities.getMedicalSuppliesTotal(); break;
            case "SpareParts": availableTotal = Facilities.getSparePartsTotal(); break;
        }

        if (availableTotal < needed) {
            System.out.println("Resource Shortage: Not enough " + resName + " available colony-wide.");
            return false; // All-or-nothing failed: consume nothing!
        }
    }
    //hena yebda2 yes7ab ba3d ma 3amal check en feh 
    for (Map.Entry<String, Integer> entry : requirements.entrySet()) {
        String resName = entry.getKey();//get key w get value dol taba3 el hashmap (let's learn about it ablaha b2a ma3l4)
        int needed = entry.getValue();

        for (Facilities f : facilities) {
            //yebda2 yelef 3la el facilities w ya5od el 3ayzo mn el makan el feh 
            if (f.getOperationalStatus() && needed > 0) {
                int taken = 0;
                switch (resName) {
                    case "Oxygen":
                        taken = Math.min(f.getOxygen(), needed);
                        if (taken > 0) { f.consumeOxygen(taken); needed -= taken; }
                        break;
                    case "Water":
                        taken = Math.min(f.getWater(), needed);
                        if (taken > 0) { f.consumeWater(taken); needed -= taken; }
                        break;
                    case "Food":
                        taken = Math.min(f.getFood(), needed);
                        if (taken > 0) { f.consumeFood(taken); needed -= taken; }
                        break;
                    case "Fuel":
                        taken = Math.min(f.getFuel(), needed);
                        if (taken > 0) { f.consumeFuel(taken); needed -= taken; }
                        break;
                    case "Battery":
                        taken = Math.min(f.getBattery(), needed);
                        if (taken > 0) { f.consumeBattery(taken); needed -= taken; }
                        break;
                    case "MedicalSupplies":
                        taken = Math.min(f.getMedicalSupplies(), needed);
                        if (taken > 0) { f.consumeMedicalSupplies(taken); needed -= taken; }
                        break;
                    case "SpareParts":
                        taken = Math.min(f.getSpareParts(), needed);
                        if (taken > 0) { f.consumeSpareParts(taken); needed -= taken; }
                        break;
                }
            }
            if (needed == 0) break;
        }
        if (needed > 0) {
                System.out.println("Failed to collect all required " + resName + ".");
                return false;
            }
    }
    return true;
}
public void reportEmergency(Emergency emergency){ 
//client class should create an emergency object (of any type) and pass it to this method to be handled
    if(emergency==null){
        System.out.println("Invalid emergency.");
        return;
    }
    if(emergency.getStatus()==true){
        System.out.println("This emergency is already resolved.");
        return;
    }
    if(!emergency.checkResponders(humans,robots)){ //list of colony's agents is passed to the method
            System.out.println("No responders available to handle emergency");
            emergency.cantHandleEmergency();
    }
   else{
            emergency.advanceTime(emergency.getEmergencyHandleTime());//handling each emergency type should advance simulation by its emergencyHandleTime attribute
            emergency.handledSuccessfully(); 
        }
    // Clear the active mission if it was failed or completed
    if (activeMission != null && (activeMission.getStatus() == MissionStatus.FAILED || activeMission.getStatus() == MissionStatus.COMPLETED)) {
    System.out.println("Active mission " + activeMission.getMissionId() + " is no longer active.");
    activeMission = null;
}
} 

  public void colonyStatusReport(){
    //this prints the total of each resource colony-wide(not each facility feeha ad eh)
    //should it be more detailed to specify how much each facility has?
        if(facilities.size()==0){
            System.out.println("There are no facilities added to the colony.");
            return;
            }
        else{ 
            //getters of total resources are static so i called them using class name directly
            System.out.println("Colony-wide oxygen resource total: "+Facilities.getOxygenTotal()+" units");
            System.out.println("Colony-wide water resource total: "+Facilities.getWaterTotal()+" units");
            System.out.println("Colony-wide food resource total: "+Facilities.getFoodTotal()+" units");
            System.out.println("Colony-wide fuel resource total: "+Facilities.getFuelTotal()+" units");
            System.out.println("Colony-wide battery power total: "+Facilities.getBatteryTotal()+" units");
            System.out.println("Colony-wide medical supplies total: "+Facilities.getMedicalSuppliesTotal()+" units");
            System.out.println("Colony-wide spare parts total: "+Facilities.getSparePartsTotal()+" units");
        
        }
        }
    public boolean submitMission(Mission mission, List<RoboticUnits> assignedRobots) {
        // 1. Check if the mission itself is allowed by the environment
        if (!mission.isEnvironment(enviroment)) {
            mission.setStatus(MissionStatus.FAILED);
            return false;
        }

        // 2. Check if any assigned robot is crippled by the environment
        for (RoboticUnits robot : assignedRobots) {
            if (!robot.canOperate(enviroment)) {
                System.out.println("Mission failed: Assigned unit " + robot.getClass().getSimpleName() + " is incompatible with current environment.");
                mission.setStatus(MissionStatus.FAILED);
                return false;
            }
        }

        // If all checks pass, proceed with normal assignment
        this.activeMission = mission;
        mission.setStatus(MissionStatus.ASSIGNED);
        return true;
    }


}


