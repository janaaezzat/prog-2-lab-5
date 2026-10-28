
import java.util.ArrayList;
import java.util.List;

import Facilities.Facilities;
import java.util.Map;

import mission.Mission;
import mission.MissionStatus;

public class MarsColony {
    // field mn noo3 Mission (el data type) ya3ni el variable da hayeshawer 3ala object men Mission aw subclass menha
    private Mission activeMission;// variable hy4eel 2sm el mission ely 4a8ala now , lw no active missions hy4awer 3ala null
    
    private List<Facilities> facilities;//hatlef 3la facilities t3raf homa meen 34an later hatnady mnhom for resources

    public MarsColony() {
        this.facilities = new ArrayList<>();
        this.activeMission = null;
    }


    public void addFacility(Facilities facility) {
        if (facility != null) {
            facilities.add(facility);
        }
    }


    //el method deh hast2bal mission gedida, w tet2aked en momken tebda2 wala la2
    public void submitMission(Mission mission) {

    if (activeMission != null) {
        mission.failMission("Another mission is already active");
        return; // maynf34 aktar mn mission active 7ata me4 hkmel ba2y checking requirments
    }

    if (!mission.checkRequirements()) {
        mission.failMission("Mission requirements are not satisfied");
        return; // mission mal2t4 its needed req to start
    }

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
           //bet2ool lel mission el shaghala en wa2t wa7ed (unit y3ny) 3adda
            activeMission.advanceTime();
        }

        if (activeMission != null &&
            (activeMission.getStatus() == MissionStatus.COMPLETED ||
             activeMission.getStatus() == MissionStatus.FAILED)) {
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

    // 2. CONSUMPTION: If all checks pass, pull resources from operational facilities
    for (Map.Entry<String, Integer> entry : requirements.entrySet()) {
        String resName = entry.getKey();//get key w get value dol taba3 el hashmap (let's learn about it ablaha b2a ma3l4)
        int needed = entry.getValue();

        for (Facilities f : facilities) {//yebda2 yelef 3la el facilities w ya5od el 3ayzo mn el makan el feh 
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
    }
    return true;
}
}

