package HumanPersonnel;
import java.util.List;

public abstract class HumanPersonnel {
    private String name, identificationNum, currentLocation;
    private  double healthStatus, fatigueLevel;
    private boolean available;
    protected List<String> skills;
    public HumanPersonnel(String name, String identificationNum, String currentLocation, List <String> skills, double healthStatus){
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
        if(healthStatus<0.0 || healthStatus>100.0)
            System.out.println("Health Status cannot be negative or greater than 100");
        else
            this.healthStatus = healthStatus;
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

    public void setHealthStatus(double healthStatus) {
        if(healthStatus<0.0 || healthStatus>100.0)
            System.out.println("Health Status cannot be negative or greater than 100");
        else
        this.healthStatus = healthStatus;
    }

    public String getCurrentLocation() {
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

    public void setFatigueLevel(double fatigueLevel) {
        if(fatigueLevel<0.0 || fatigueLevel>100.0)
        {
            System.out.println("Fatigue level cannot be negative or greater than 100");
            fatigueLevel=0.0;


        }
      else 
        this.fatigueLevel=fatigueLevel; //8yrt hna kanet btreturn double 5aletaha void
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


    public void increaseFatigue(double amount) {//increases fatigue level by a specified amount, ensuring it does not exceed 100
        if (amount < 0) {
            System.out.println("Fatigue increase amount cannot be negative.");
            return;
        }
    this.fatigueLevel = Math.min(100.0, this.fatigueLevel + amount);
    
    }

    // Decrease health (e.g., due to an emergency or harsh environment)
    public void decreaseHealth(double amount) {//use for emergencies aw lw feh 7aga fl environment
    if (amount > 0) {
        this.healthStatus = Math.max(0.0, this.healthStatus - amount);
        }
    }

    public void increaseHealth(double amount) {//after successful medical treatment
    if (amount > 0) {
        this.healthStatus = Math.min(100.0, this.healthStatus + amount);
        }
    }

    public void reduceFatigue(double amount) {//after successful medical treatment
    if (amount > 0) {
        this.fatigueLevel = Math.max(0.0, this.fatigueLevel - amount);
        }
    }
}
