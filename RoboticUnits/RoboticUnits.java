package RoboticUnits;

import java.util.ArrayList;
import java.util.List;

public abstract class RoboticUnits {
    private int battery;
    private double damageStatus;
    private String currentLocation,ID;
    private boolean available;
    protected List<String> capabilities; // What this robot can do
    public RoboticUnits(String ID, String currentLocation, int battery2, double damageStatus2, List<String> capabilities2){

       if(ID==null||ID.isEmpty())
            System.out.println("Invalid ID");
        else
            this.ID = ID;
        if(currentLocation==null||currentLocation.isEmpty())
            System.out.println("Invalid location");
        else
            this.currentLocation = currentLocation;
         if(battery2<0||battery2>100){
            System.out.println("Battery cannot be negative or greater than 100");
        }
        else
            this.battery = battery2;
        if(damageStatus2<0.0||damageStatus2>100.0) {
            System.out.println("Damage Status cannot be negative or greater than 100");
        }
        else
            this.damageStatus = damageStatus2;
        this.available=true;

        //zay skill list of personnel hena m7tageen n3raf skill of robot
        this.capabilities = capabilities2 != null ? capabilities2 : new ArrayList<>();

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        if(ID==null||ID.isEmpty())
            System.out.println("Invalid ID");
        else
            this.ID = ID;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        if(currentLocation==null||currentLocation.isEmpty())
            System.out.println("Invalid location");
        else
            this.currentLocation = currentLocation;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        if(battery<0||battery>100){
            System.out.println("Battery cannot be negative or greater than 100");
        }
        else
            this.battery = battery;
    }

    public double getDamageStatus() {
        return damageStatus;
    }

    public void setDamageStatus(double damageStatus) {
        if(damageStatus<0.0||damageStatus>100.0) {
            System.out.println("Damage Status cannot be negative or greater than 100");
        }
        else
            this.damageStatus = damageStatus;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
       // 4elt el cond zy in personnal
            this.available = available;
    }
    public abstract void task();
    public boolean canPerform(String type)//changed to canPerform bas 4abah el pdf 34an me4 3rfa leh 7asetha hatl8btny w 8yrtaha f ba2eet el robots
    {   // me4 el mafrood el 3ks <80 ???? so2al hna (done 3mltaha <20 instead of >20 34an deh damage me4 health)
        return this.available&&this.battery > 20 && this.damageStatus < 20.0 && this.capabilities.contains(type);
      }

    // Drain battery power (ensuring it never drops below 0) (used in time advancement brdo)
    public void drainBattery(int amount) {
    if (amount > 0) {
        this.battery = Math.max(0, this.battery - amount);
        }
    }

    // Increase damage status (e.g., during rough terrain or dust storms)(lw rowan won't use f environment yeb2a ne4lha)
    public void increaseDamage(double amount) {
    if (amount > 0) {
        this.damageStatus = Math.min(100.0, this.damageStatus + amount);
        }
    }

}

