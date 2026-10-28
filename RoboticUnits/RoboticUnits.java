package RoboticUnits;

public abstract class RoboticUnits {
    private int battery;
    private double damageStatus;
    private String currentLocation,ID;
    private boolean available;
    public RoboticUnits(String ID, String currentLocation, int battery, double damageStatus){

       if(ID.isEmpty()||ID==null)
            System.out.println("Invalid ID");
        else
            this.ID = ID;
        if(currentLocation.isEmpty()||currentLocation==null)
            System.out.println("Invalid location");
        else
            this.currentLocation = currentLocation;
         if(battery<0||battery>100){
            System.out.println("Battery cannot be negative or greater than 100");
        }
        else
            this.battery = battery;
        if(damageStatus<0.0||damageStatus>100.0) {
            System.out.println("Damage Status cannot be negative or greater than 100");
        }
        else
            this.damageStatus = damageStatus;
        this.available=true;

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        if(ID.isEmpty()||ID==null)
            System.out.println("Invalid ID");
        else
            this.ID = ID;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        if(currentLocation.isEmpty()||currentLocation==null)
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
    {   // me4 el mafrood el 3ks <80 ???? so2al hna
        return this.available&&this.battery>20&&this.damageStatus>20.0;//hwa hena 34an el la8bta ne5aly deh health 34an tbe2a aktar mn 20 wla eh (gemy knt bas2alo 3la 7aga tanya aslan)
      }


}

