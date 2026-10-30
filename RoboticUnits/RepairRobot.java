package roboticunits;

import java.util.ArrayList;
import java.util.List;

public class RepairRobot extends RoboticUnits{
    public RepairRobot(String ID, String currentLocation, int battery, double damageStatus,List<String> initialCapabilities){
        super(ID,currentLocation,battery,damageStatus,initialCapabilities);
        
        if (this.capabilities == null) {
            this.capabilities = new ArrayList<>();
        }
        if (!this.capabilities.contains("Repair")) {
            this.capabilities.add("Repair");
        }
    }

    @Override
    public void task() {
        System.out.println("Repair Robot "+getID()+" repairs damaged equipment and operates inside facilities");

    }

   //4elt canPerform 5ales 34an 5alas main class does everything
}
