package Facilities;

public class Facilities {
    protected String location;
    protected int capacity; //number of units of el 7aga el stored
    protected boolean operationalStatus; //true:sha8ala, false:msh sha8ala
    protected int oxygen; //all resources measured as a number of units
    protected int water;
    protected int food;
    protected int fuel;
    protected int battery;
    protected int medicalSupplies;
    protected int spareParts;
    protected static int oxygenTotal=0,waterTotal=0,foodTotal=0,fuelTotal=0,batteryTotal=0,medicalSuppliesTotal=0,sparePartsTotal=0;
    
    public Facilities(String location,int capacity,boolean operationalStatus,int oxygen,int water,int food,int fuel,int battery,int medicalSupplies,int spareParts){
        if(location==null||location.equals("")){
            System.out.println("Invalid location");
            return;
        }
        if(capacity<0){
            System.out.println("Capacity can't be negative");
            return;
        }
        if(oxygen<0||water<0||food<0||fuel<0||battery<0||medicalSupplies<0||spareParts<0){
            System.out.println("Number of units can't be negative");
            return;
        }
        if(capacity<(oxygen+water+food+fuel+battery+medicalSupplies+spareParts)){
            System.out.println("Capacity is not enough to store resources");
            return;
        }
        this.location=location;
        this.capacity=capacity; 
        this.operationalStatus=operationalStatus; 
        this.oxygen=oxygen; 
        this.water=water;
        this.food=food;
        this.fuel=fuel;
        this.battery=battery;
        this.medicalSupplies=medicalSupplies;
        this.spareParts=spareParts;
        if(operationalStatus==true){  //resources in an unavailable facility do not count
        oxygenTotal+=oxygen;
        waterTotal+=water;
        foodTotal+=food;
        fuelTotal+=fuel;
        batteryTotal+=battery;
        medicalSuppliesTotal+=medicalSupplies;
        sparePartsTotal+=spareParts;
        }
        System.out.println("Facility was created successfully!");
    }
    
    //getters
    public String getLocation() {
        return location;
    }
    public int getCapacity() {
        return capacity;
    }
    public boolean getOperationalStatus() {
        return operationalStatus;
    }
    public int getOxygen() {
        return oxygen;
    }
    public int getWater() {
        return water;
    }
    public int getFood() {
        return food;
    }
    public int getFuel() {
        return fuel;
    }
    public int getBattery() {
        return battery;
    }
    public int getMedicalSupplies() {
        return medicalSupplies;
    }
    public int getSpareParts() {
        return spareParts;
    }
    public static int getOxygenTotal() {
        return oxygenTotal;
    }
    public static int getWaterTotal() {
        return waterTotal;
    }
    public static int getFoodTotal() {
        return foodTotal;
    }
    public static int getFuelTotal() {
        return fuelTotal;
    }
    public static int getBatteryTotal() {
        return batteryTotal;
    }
    public static int getMedicalSuppliesTotal() {
        return medicalSuppliesTotal;
    }
    public static int getSparePartsTotal() {
        return sparePartsTotal;
    }
    
    
    //setters
    public void setLocation(String location){
        if(location==null||location.equals("")){
            System.out.println("Invalid location");
            return;
        }
        this.location = location;
    }
    public void setCapacity(int capacity){
        if(capacity<0){
            System.out.println("Capacity can't be negative");
            return;
        }
        if(capacity<(oxygen+water+food+fuel+battery+medicalSupplies+spareParts)){
            System.out.println("Capacity is not enough to store resources");
            return;
        }
        this.capacity = capacity;
    }
    public void setOperationalStatus(boolean operationalStatus) {
        this.operationalStatus = operationalStatus;
    }
    //han3ml b2a l kol resource add w consume 34an manla8bat4 el colony w heya 3izah tezawed badal ma t set an entire number mn el awal zay ma kanet bt3ml
    public void addOxygen(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to add must be positive.");
            return;
        }
        this.oxygen += amount;
        oxygenTotal += amount;
    }

    public boolean consumeOxygen(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to consume must be positive.");
            return false;
        }
        if (this.oxygen < amount) {
            System.out.println("Error: Insufficient oxygen in this facility.");
            return false;
        }
        this.oxygen -= amount;
        oxygenTotal -= amount;
        return true;
    }

    public void addWater(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to add must be positive.");
            return;
        }
        this.water += amount;
        waterTotal += amount;
    }

    public boolean consumeWater(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to consume must be positive.");
            return false;
        }
        if (this.water < amount) {
            System.out.println("Error: Insufficient water in this facility.");
            return false;
        }
        this.water -= amount;
        waterTotal -= amount;
        return true;
    }

    public void addFood(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to add must be positive.");
            return;
        }
        this.food += amount;
        foodTotal += amount;
    }

    public boolean consumeFood(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to consume must be positive.");
            return false;
        }
        if (this.food < amount) {
            System.out.println("Error: Insufficient food in this facility.");   
            return false;
        }
        this.food -= amount;
        foodTotal -= amount;
        return true;
    }

    public void addFuel(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to add must be positive.");
            return;
        }
        this.fuel += amount;
        fuelTotal += amount;
    }

    public boolean consumeFuel(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to consume must be positive.");
            return false;
        }
        if (this.fuel < amount) {
            System.out.println("Error: Insufficient fuel in this facility.");   
            return false;
        }
        this.fuel -= amount;
        fuelTotal -= amount;
        return true;
    }

    public void addBattery(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to add must be positive.");
            return;
        }
        this.battery += amount;
        batteryTotal += amount;
    }

    public boolean consumeBattery(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to consume must be positive.");
            return false;
        }
        if (this.battery < amount) {
            System.out.println("Error: Insufficient battery power in this facility.");  
            return false;
        }
        this.battery -= amount;
        batteryTotal -= amount;
        return true;
    }

    public void addMedicalSupplies(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to add must be positive.");
            return;
        }
        this.medicalSupplies += amount;
        medicalSuppliesTotal += amount;
    }

    public boolean consumeMedicalSupplies(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to consume must be positive.");
            return false;
        }
        if (this.medicalSupplies < amount) {
            System.out.println("Error: Insufficient medical supplies in this facility.");   
            return false;
        }
        this.medicalSupplies -= amount;
        medicalSuppliesTotal -= amount;
        return true;
    }

    public void addSpareParts(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to add must be positive.");
            return;
        }
        this.spareParts += amount;
        sparePartsTotal += amount;
    }

    public boolean consumeSpareParts(int amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount to consume must be positive.");
            return false;
        }
        if (this.spareParts < amount) {
            System.out.println("Error: Insufficient spare parts in this facility.");    
            return false;
        }
        this.spareParts -= amount;
        sparePartsTotal -= amount;
        return true;
    }
    public void colonyStatusReport(){ //should it be more detailed to specify how much each facility subclass has?
        System.out.println("Colony wide oxygen resource total: "+oxygenTotal+" units");
        System.out.println("Colony wide water resource total: "+waterTotal+" units");
        System.out.println("Colony wide food resource total: "+foodTotal+" units");
        System.out.println("Colony wide fuel resource total: "+fuelTotal+" units");
        System.out.println("Colony wide battery power total: "+batteryTotal+" units");
        System.out.println("Colony wide medical supplies total: "+medicalSuppliesTotal+" units");
        System.out.println("Colony wide spare parts total: "+sparePartsTotal+" units");
        
        }
}


