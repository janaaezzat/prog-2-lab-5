package emergencies;
import humanpersonnel.HumanPersonnel;
import roboticunits.RoboticUnits;
import java.util.List; 

abstract public class Emergency{
    //emergency happens either in a facility or on martian surface
    //so emergency location attribute will either be a facility location or a martian surface location 
    //the 2 subclasses fire and oxygen leak occur in a facility,so they don't need to take location as an argument in their constructor,ana 7a assign el emergency location bta3hom bl facility location
    //that's why i used overloading 3shn a3ml 2 constructors lel emergency class,wa7da lel subclasses el leha location argument w wa7da lel subclasses el malhash location argument
    //rover break down msh bt7sal fe facility
    //fire and oxygen leak happen in a facility
    //el medical treatment ta2reban nour 3amlalha facility fa sebtaha zy ma heya 
    protected String location; //where an emergency happens
    //responders can be humans or robots
    protected boolean status; // bta3t el emergency false:unresolved,true:resolved
    protected String failureReason=null;//failure reason if emergency is unresolved, null otherwise
    protected int emergencyHandleTime;
    protected int elapsedTime;//time ely 3ada since emergency was reported, colony will advance time by calling advanceTime function
    

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
    public Emergency(int emergencyHandleTime){
        if(emergencyHandleTime<=0){
            System.out.println("Invalid emergency handle time");
            return;
        }
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
    // check fi responders monzbeen wla la2
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




