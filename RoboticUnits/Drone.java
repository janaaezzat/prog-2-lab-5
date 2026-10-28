package RoboticUnits;

public class Drone extends RoboticUnits{
    private double flyRange;//maktoba fein deh?
    public Drone(String ID, String currentLocation, int battery, double damageStatus, double flyRange){
        super(ID, currentLocation, battery, damageStatus);
        if(flyRange<0.0)
            System.out.println("Fly range cannot be negative");
        else
            this.flyRange = flyRange;
    }

    @Override
    public void task() {
        System.out.println("Drone "+getID()+" performs aerial reconnaissance and operates in areas inaccessible to ground vehicles.");
    }

    @Override
    public boolean canPerform(String type) {
        return super.canPerform(type)&&("Reconnaissance".equals(type));
    }

    public double getFlyRange() {
        return flyRange;
    }

    public void setFlyRange(double flyRange) {
        if(flyRange<0.0)
            System.out.println("Fly range cannot be negative");
        else
            this.flyRange = flyRange;
    }
}
