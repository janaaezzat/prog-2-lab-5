package HumanPersonnel;

import java.util.ArrayList;
import java.util.List;

public class Scientist extends HumanPersonnel{
    public Scientist(String name, String identificationNum, String currentLocation, List<String> skills, double healthStatus){
     super(name, identificationNum, currentLocation, skills,healthStatus);


     //zawedtaha in all persons 34an skill list
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
//4elt canPerform 5ales 34an 5alas main class does everything 
}

