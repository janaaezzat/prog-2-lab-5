import Facilities.*;
import HumanPersonnel.*;
import RoboticUnits.*;
import java.util.List;
import java.util.ArrayList;


public class OxygenLeak extends Emergency{
    Habitat habitat;
    HumanPersonnel humanResponder=null;
    RoboticUnits roboticResponder=null;
    public OxygenLeak(String location,Habitat habitat){
        super(location);
        this.habitat=habitat;
        if(habitat.getOxygen()<10){
            habitat.status=false;  //oxygen level must never fall below zero
        }
        else{
            habitat.consumeOxygen(10); //10 units leaked
            }
        }
    @Override
    public boolean checkRequirements(List<HumanPersonnel>humans,List<RoboticUnits>robots){
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

        return false;
    }
    @Override
    public void cantHandleEmergency(){
        status=false;
        System.out.println("No available responders to handle emergency.");
        failureReason="No available responders to handle emergency.";
        if(habitat.getOxygen()<10){
            habitat.status=false;  //oxygen level must never fall below zero
        }
        else{
            habitat.consumeOxygen(10); //same amount deducted again bec repair failed
            }
    }

    @Override
    public void canHandleEmergency(){
        status=true;
        humanResponder.setAvailable(true);
        roboticResponder.setAvailable(true);
        System.out.println("Habitat repaired successfully.");

}}


      

