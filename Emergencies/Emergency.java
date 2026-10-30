package emergencies;
import facilities.Facilities;
import humanpersonnel.HumanPersonnel;
import roboticunits.RoboticUnits;
import java.util.List;
import java.util.ArrayList;

abstract public class Emergency{
    protected String location; //either a facility or martian surface
    //responders can be humans or robots
    protected boolean status; //false:unresolved,true:resolved
    protected String failureReason=null;
    protected int emergencyHandleTime;
    protected int elapsedTime;
    

    public Emergency(String location, int emergencyHandleTime){
        //client class will create an emergency object and report it to colony
        if(location==null||location.equals("")){
            System.out.println("Invalid location");
            return;
        }
        if(emergencyHandleTime<=0){
            System.out.println("Invalid emergency handle time");
            return;
        }
        this.location=location;
        this.status=false; //initially unresolved
        this.elapsedTime=0;
        this.emergencyHandleTime=emergencyHandleTime;
        
    }

    public String getLocation(){
        return location;
    }
    public boolean getStatus(){
        return status;
        }
    public int getEmergencyHandleTime() {
    return emergencyHandleTime;
    }
    public int getElapsedTime(){
        return elapsedTime;
    }
    public String getFailureReason(){
        return failureReason;
    }

    public abstract boolean checkResponders(List<HumanPersonnel>humans,List<RoboticUnits>robots); //list of all colony agents is passed to this function
    public abstract void cantHandleEmergency(); //colony calls it when requirments are not met
    public abstract void handledSuccessfully();//colony calls it when requirments are met 
    public void advanceTime(int time){ //number of time units to advance,colony calls it to advance simulation with value emergencyHandleTime
        for(int i=0;i<time;i++){
            if(elapsedTime>=emergencyHandleTime){
                return; 
                }
            else{
                elapsedTime++; }    
        }
            }
}   




