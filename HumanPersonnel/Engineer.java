package humanpersonnel;
 import java.util.ArrayList;
 import java.util.List;

public class Engineer extends HumanPersonnel{
    public Engineer(String name, String identificationNum, String currentLocation, List<String> skills, double healthStatus){
        super(name, identificationNum, currentLocation, skills,healthStatus);
    
        //zawedtaha in all persons 34an skill list
    if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        if (!this.skills.contains("Repair")) {
            this.skills.add("Repair");
        }

    }

    @Override
    public void duty() {
        System.out.println("Engineer "+getName());
    }
    //4elt canPerform 5ales 34an 5alas main class does everything 
   
    @Override
     public boolean canHandleOxygenLeak(){
    return canPerform("Repair"); 
     }
    
  
   


}

