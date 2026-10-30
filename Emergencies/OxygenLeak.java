package emergencies;
import Facilities.*;
import HumanPersonnel.*;
import RoboticUnits.*;
import java.util.List;
import java.util.ArrayList;


public class OxygenLeak extends Emergency{
    Habitat habitat;
    int oxygenLeakAmount;
    HumanPersonnel humanResponder; //will be used to search for an engineer in colony's list of human personnel
    RoboticUnits roboticResponder; //will be used to search for a repair robot in colony's list of robotic units
    public OxygenLeak(String location,Habitat habitat,int oxygenLeakAmount,int emergencyHandleTime){
        super(location,emergencyHandleTime);
        if(habitat==null){
            System.out.println("Invalid habitat");
            return;
        }
        if(oxygenLeakAmount<=0){
            System.out.println("Invalid oxygen leak amount");
            return;
        }
        this.habitat=habitat;
        this.oxygenLeakAmount=oxygenLeakAmount;
        if(habitat.getOxygen()<oxygenLeakAmount){
            habitat.setOperationalStatus(false);  //oxygen level must never fall below zero
        }
        else{
            habitat.consumeOxygen(oxygenLeakAmount); //leaked amount
            }
        }
    @Override
    public boolean checkResponders(List<HumanPersonnel>humans,List<RoboticUnits>robots){
        humanResponder = null; //not found yet
        roboticResponder = null;
        for(HumanPersonnel h:humans){  
             if (h.canHandleOxygenLeak()){ //oxygen leak needs one engineer
                humanResponder=h;
                break;
             }
        }
        for(RoboticUnits r:robots){  //oxygen leak needs one repair robot
            if (r.canPerform("Repair")){
                roboticResponder=r;
                break;
            }
        }
        if (humanResponder != null && roboticResponder != null) {
            humanResponder.setAvailable(false);
            humanResponder.setCurrentLocation(location);
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
        if(habitat.getOxygen()<oxygenLeakAmount){ //same amount should be deducted again bec repair failed
            habitat.setOperationalStatus(false);  //oxygen level must never fall below zero
        }
        else{
            habitat.consumeOxygen(oxygenLeakAmount);
            }
    }

    @Override
    public void handledSuccessfully(){
        status=true;
        humanResponder.setAvailable(true);
        roboticResponder.setAvailable(true);
        habitat.setOperationalStatus(true);
        System.out.println("Habitat repaired successfully.");

}}


      

