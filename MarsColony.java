import mission.Mission;
import mission.MissionStatus;


public class MarsColony {
    // field mn noo3 Mission (el data type) ya3ni el variable da hayeshawer 3ala object men Mission aw subclass menha
    private Mission activeMission;// variable hy4eel 2sm el mission ely 4a8ala now , lw no active missions hy4awer 3ala null
    
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
   // 8eir keda lw rl requirements tmm w no active missions yeb2a el mission tbd2
    activeMission = mission;
    mission.startMission();
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
            activeMission = null;
        }
    }
}

//34an ay class tanya te2dar tes2al el colony eh el mission elly active delwa2ty?
public Mission getActiveMission() {
    return activeMission; // lw no mission active hyrg3 null
}



}

