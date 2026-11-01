package humanpersonnel;
import java.util.List;

public abstract class HumanPersonnel {
    private String name, identificationNum, currentLocation;
    private  double healthStatus, fatigueLevel;
    private boolean available;
    protected List<String> skills;//zawedt deh 34an ye check skills 3nd kol mission
    public HumanPersonnel(String name, String identificationNum, String currentLocation, List <String> skills){
        if(name==null|| name.isEmpty()) // badelt el tarteeb only 34an lw string bi null hydrb nullpointerexception lw 4af isEmpty el awal
            System.out.println("Invalid name");
        else
        this.name = name;
        if(identificationNum==null ||identificationNum.isEmpty())
            System.out.println("Invalid ID");
        else
            this.identificationNum = identificationNum;
        if(currentLocation==null||currentLocation.isEmpty())
            System.out.println("Invalid location");
        else
            this.currentLocation = currentLocation;
        this.healthStatus = 100; //yebda2 ye3ml wa7ed gdeed lang b 100 health w zero fatigue (ta7t 3enwan nour "enta betsta3bat?")
        this.skills=skills;
        this.fatigueLevel=0.0;
        this.available=true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name==null || name.isEmpty())
            System.out.println("Invalid name");
        else
        this.name = name;
    }

    public String getIdentificationNum() {
        return identificationNum;
    }

    public void setIdentificationNum(String identificationNum) {
        if(identificationNum==null || identificationNum.isEmpty())
            System.out.println("Invalid ID");
        else
        this.identificationNum = identificationNum;
    }

    public double getHealthStatus() {
        return healthStatus;
    }

  

    public String getCurrentLocation() {
         if (currentLocation == null || currentLocation.isEmpty()) {
        throw new IllegalStateException("Person's location is invalid.");
        }
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        if(currentLocation==null|| currentLocation.isEmpty())
            System.out.println("Invalid location");
        else
        this.currentLocation = currentLocation;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public double getFatigueLevel() {
        return fatigueLevel;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
       //4elt ely zawdnaha 3ala rowan
        this.available = available;
    }

    public abstract void duty();
    public boolean canPerform(String type){

        //zawedt hena check 3la skill type 34an can perform this specific misssion w keda ked ael subclass by-check 3aleiha 
        return this.available && this.healthStatus>50.0 && this.fatigueLevel<50.0 && this.skills != null && this.skills.contains(type);
    }

    //increases fatigue level kol ma bnadvance time, ensuring it does not exceed 100
    public void increaseFatigue(double amount) {
        if (amount <= 0) {
            System.out.println("Fatigue increase amount cannot be negative.");
            return;
        }
    this.fatigueLevel = Math.min(100.0, this.fatigueLevel + amount);
    
    }

    // Decrease health (e.g., due to an emergency or harsh environment) lw hnst5dmha for env aw emergencies o.w ne4lha
    public void decreaseHealth(double amount) {
    if (amount <= 0) {
        System.out.println("Health decrease amount cannot be negative or zero.");
        return;
    }
    this.healthStatus = Math.max(0.0, this.healthStatus - amount);
    }

    public void increaseHealth(double amount) {
    if (amount <= 0) {
        System.out.println("Health increase amount cannot be negative or zero.");
        return;
    }
    this.healthStatus = Math.min(100.0, this.healthStatus + amount);
    }

   public void decreaseFatigue(double amount) {
    if (amount <= 0) {
        System.out.println("Fatigue decrease amount cannot be negative or zero.");
        return;
    }
    this.fatigueLevel = Math.max(0.0, this.fatigueLevel - amount);
  } 
    public boolean canHandleOxygenLeak(){
    return false; //method is overridden in engineer subclass to return true after checking availabilty and health
     }
    public boolean canHandleFireEmergency(){
    return false; // ay 7ad me4 3amel override lel method deh hy return false automatically y3ny ma3ndoosh skill ely y5llih y3ml fire emergency
     }
    public boolean canProvideMedicalAssistance(){
    return false;
    }
    
}
