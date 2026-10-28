package RoboticUnits;

public class RepairRobot extends RoboticUnits{
    public RepairRobot(String ID, String currentLocation, int battery, double damageStatus){
        super(ID,currentLocation,battery,damageStatus);
    }

    @Override
    public void task() {
        System.out.println("Repair Robot "+getID()+" repairs damaged equipment and operates inside facilities");

    }

    @Override
    public boolean canPerform(String type) {
        return super.canPerform(type)&&("Repair".equals(type));
    }
}
