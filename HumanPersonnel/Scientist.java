package HumanPersonnel;

import java.util.ArrayList;
import java.util.List;

public class Scientist extends HumanPersonnel{
    public Scientist(String name, String identificationNum, String currentLocation, List<String> skills, double healthStatus){
     super(name, identificationNum, currentLocation, skills,healthStatus);

    if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        if (!this.skills.contains("Research")) {
            this.skills.add("Research");
        }

    }

    @Override
    public void duty() {
        System.out.println("Scientist "+getName());
    }

    @Override
    public boolean canPerform(String type) {
        return super.canPerform(type)&& ("Research".equals(type));
    }
    //htrg3 true lw available ,health aktar men 50,fatigue a2al men 50,type howa "Research"
}

