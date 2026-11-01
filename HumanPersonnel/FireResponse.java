package humanpersonnel;
import java.util.ArrayList;
import java.util.List;

public class FireResponse extends HumanPersonnel{
    public FireResponse(String name, String identificationNum, String currentLocation, List <String> skills, double healthStatus){
        super(name, identificationNum, currentLocation, skills);
        if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        if (!this.skills.contains("Fire Response")) {
            this.skills.add("Fire Response");
        }
    }
    @Override
    public boolean canHandleFireEmergency(){
    return canPerform("Fire Response"); 
   }

    @Override
    public void duty() {
        System.out.println("Fire Responder "+getName());
    }
  

}