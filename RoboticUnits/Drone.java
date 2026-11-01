package roboticunits;

import java.util.ArrayList;
import java.util.List;
import enviroment.Enviroment;

public class Drone extends RoboticUnits{
    //4elt flyRange me4 maktooba f 7eta
    private double flyRange;

    //zawedt capabilities brdo mission will use drones in rescue and anything that needs communication
    public Drone(String ID, String currentLocation, int battery, double damageStatus, List<String> capabilities, double flyRange) {
        super(ID, currentLocation, battery, damageStatus, capabilities);
        if (this.capabilities == null) {
            this.capabilities = new ArrayList<>();
        }
        if (!this.capabilities.contains("Communication")) {
            this.capabilities.add("Communication");
        }
        if (!this.capabilities.contains("Rescue")) {
            this.capabilities.add("Rescue");
        }if(flyRange<0.0)
            System.out.println("Fly range cannot be negative");
        else
            this.flyRange = flyRange;


    }

    @Override
    public void task() {
        System.out.println("Drone "+getID()+" performs aerial reconnaissance and operates in areas inaccessible to ground vehicles.");
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


    @Override
    public boolean canOperate(Enviroment Enviroment) {
        if(Enviroment.getCurrentCond()!= null && Enviroment.getCurrentCond().equals(enviroment.Enviroment.commDisruption)){

            System.out.println("Drone " + getID()+ " cannot be controlled due to communication disruption.");
            return false;
        }
        return true;
    }
}
