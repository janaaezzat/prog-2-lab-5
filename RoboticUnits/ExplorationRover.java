package RoboticUnits;

public class ExplorationRover extends RoboticUnits{
    private double capacity;//عشان اليتاع ده بيشيل حاجات فعايزين نشوف هو يقدر يشيل لحد كام كيلو
    public ExplorationRover(String ID, String currentLocation, int battery, double damageStatus,double capacity){
       super(ID, currentLocation,battery,damageStatus);
       if(capacity<0.0)
            System.out.println("Capacity cannot be negative");
        else
       this.capacity=capacity;

    }

    @Override
    public void task() {
        System.out.println("Exploration Rover "+getID()+" travels across the Marine surface and carries equipment ");

    }

    @Override
    public boolean canPerform(String type) {
        return super.canPerform(type)&&("Exploration".equals(type));//super bet4oof el awal el general checks el fl parent
    }//Even if the rover's battery and health are great, it can only perform missions where the type string matches "Exploration"

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        if(capacity<0.0)
            System.out.println("Capacity cannot be negative");
        else
            this.capacity = capacity;
    }
}
