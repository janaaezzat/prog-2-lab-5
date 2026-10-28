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
        this.medicalSupplies=battery;
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
    public void setOxygen(int oxygen){
        if(oxygen<0){
            System.out.println("Number of units can't be negative");
            return;
        }
        //updating both the faciliy's oxygen and the total oxygen
        int difference=this.oxygen-oxygen;
        oxygenTotal+=difference;  //law el difference bl negative el total 7ay2el law bl positive el total 7ayzeed
        this.oxygen = oxygen;
    }
    public void setWater(int water){
        if(water<0){
            System.out.println("Number of units can't be negative");
            return;
        }
        int difference=this.water-water;
        waterTotal+=difference;
        this.water = water;
    }
    public void setFood(int food){
        if(food<0){
            System.out.println("Number of units can't be negative");
            return;
        }
        int difference=this.food-food;
        foodTotal+=difference;
        this.food = food;
    }
    public void setFuel(int fuel){
        if(fuel<0){
            System.out.println("Number of units can't be negative");
            return;
        }
        int difference=this.fuel-fuel;
        fuelTotal+=difference;
        this.fuel = fuel;
    }
    public void setBattery(int battery){
        if(battery<0){
            System.out.println("Number of units can't be negative");
            return;
        }
        int difference=this.battery-battery;
        batteryTotal+=difference;
        this.battery = battery;
    }
    public void setMedicalSupplies(int medicalSupplies){
        if(medicalSupplies<0){
            System.out.println("Number of units can't be negative");
            return;
        }
        int difference=this.medicalSupplies-medicalSupplies;
        medicalSuppliesTotal+=difference;
        this.medicalSupplies = medicalSupplies;
    }
    public void setSpareParts(int spareParts){
        if(spareParts<0){
            System.out.println("Number of units can't be negative");
            return;
        }
        int difference=this.spareParts-spareParts;
        sparePartsTotal+=difference;
        this.spareParts = spareParts;
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


