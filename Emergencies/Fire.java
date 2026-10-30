package emergencies;
import facilities.*;
import humanpersonnel.*;
import roboticunits.*;
import java.util.List;
import java.util.ArrayList;


public class Fire extends Emergency{
    Laboratories lab;
    FireResponse[] fireResponders=new FireResponse[3]; //3 fire responder personnel will be needed
    RoboticUnits roboticResponder; //1 repair robot will be needed
    MedicalOfficer medicalAssistance; //1 medical officer will be needed fl a5er to treat lab occupants
    public Fire(String location,Laboratories lab,int emergencyHandleTime){
        super(location,emergencyHandleTime);
        if(lab==null){
            System.out.println("Invalid lab.");
            return;
        }
        else{
            this.lab=lab;
            lab.consumeOxygen(5); 
        }
        lab.setOperationalStatus(false); //lab becomes damaged
        for(HumanPersonnel human:lab.getPersonnelList()){ //occupants of lab will need medical assistance
            human.increaseFatigue(3);
            human.decreaseHealth(3);
        }
        }
    @Override
    public boolean checkResponders(List<HumanPersonnel>humans,List<RoboticUnits>robots){
        int foundCount=0; //to search for 3 fire responders
        fireResponders = new FireResponse[3];
        medicalAssistance = null;
        roboticResponder = null;
        for(HumanPersonnel h:humans){  
             if (h.canHandleFireEmergency()){ //fire emergency needs one fire responder
                fireResponders[foundCount] = (FireResponse) h; //safe casting bec canHandleFireEmergency returns true fopr fireReponse type only
                foundCount++;
                if (foundCount == 3) {
                    break;
                }
             }
        }
        for(RoboticUnits r:robots){  //fire emergency needs one repair robot
            if (r.canPerform("Repair")){
                roboticResponder=r;
                break;
            }
        }
        for (HumanPersonnel h : humans) { //not a necessary requirement to handle fire emergency, but will be used later if laboratory has occupants 
            if (h.canProvideMedicalAssistance()) {
                medicalAssistance = (MedicalOfficer) h;
            break;
            }
            }
        if (foundCount == 3 && roboticResponder != null) {
            for (int i = 0; i < 3; i++) {
                fireResponders[i].setAvailable(false);
                fireResponders[i].setCurrentLocation(location);
            }
            roboticResponder.setAvailable(false);
            roboticResponder.setCurrentLocation(location);
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public void cantHandleEmergency(){
        status=false;  //unresolved
        failureReason="No available responders to handle emergency.";
        }

    @Override
    public void handledSuccessfully(){
        if(lab.getPersonnelList().size()>0 && medicalAssistance!=null){  //if there are humans in lab that need medical treatment
            medicalAssistance.setAvailable(false);
            medicalAssistance.setCurrentLocation(location);
            for(HumanPersonnel human:lab.getPersonnelList()){ //traet occupants
            human.decreaseFatigue(3);
            human.increaseHealth(3);
        }
        }
        status=true; //emergency resolved
        for (FireResponse responder : fireResponders) {
           responder.setAvailable(true);
        }
        roboticResponder.setAvailable(true);
        if (medicalAssistance != null) {
           medicalAssistance.setAvailable(true);
        }
        lab.setOperationalStatus(true); //lab is repaired
}    

}