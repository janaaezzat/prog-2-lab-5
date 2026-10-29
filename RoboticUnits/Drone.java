package RoboticUnits;

import java.util.ArrayList;
import java.util.List;

public class Drone extends RoboticUnits{
    //4elt flyRange me4 maktooba f 7eta 

    //zawedt capabilities brdo mission will use drones in rescue and anything that needs communication
    public Drone(String ID, String currentLocation, int battery, double damageStatus, List<String> capabilities) {
        super(ID, currentLocation, battery, damageStatus, capabilities);
        if (this.capabilities == null) {
            this.capabilities = new ArrayList<>();
        }
        if (!this.capabilities.contains("Communication")) {
            this.capabilities.add("Communication");
        }
        if (!this.capabilities.contains("Rescue")) {
            this.capabilities.add("Rescue");
        }
    }

    @Override
    public void task() {
        System.out.println("Drone "+getID()+" performs aerial reconnaissance and operates in areas inaccessible to ground vehicles.");
    }
    //4elt canPerform 5ales 34an 5alas main class does everything

}
