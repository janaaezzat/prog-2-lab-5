package roboticunits;

import java.util.ArrayList;
import java.util.List;

public class ExplorationRover extends RoboticUnits{

    //makan4 feh capacity el mafrood netab3ha ll Exploration rover (34an mankatr4 el mawdoo3 3la nafsena)
    public ExplorationRover(String ID, String currentLocation, int battery, double damageStatus,List<String> capabilities) {
       super(ID, currentLocation,battery,damageStatus,capabilities);
        if (this.capabilities == null) {
            this.capabilities = new ArrayList<>();
        }
        if (!this.capabilities.contains("Exploration")) {
            this.capabilities.add("Exploration");
        }
    }

    @Override
    public void task() {
        System.out.println("Exploration Rover "+getID()+" travels across the Marine surface and carries equipment ");

    }

   //4elt canPerform 5ales 34an 5alas main class does everything
}
