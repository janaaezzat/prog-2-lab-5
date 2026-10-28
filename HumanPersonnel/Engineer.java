package HumanPersonnel;
 import java.util.List;

public class Engineer extends HumanPersonnel{
    public Engineer(String name, String identificationNum, String currentLocation, List<String> skills, double healthStatus){
        super(name, identificationNum, currentLocation, skills,healthStatus);
    }

    @Override
    public void duty() {
        System.out.println("Engineer "+getName());
    }

    @Override
    public boolean canPerform(String type) {
        return super.canPerform(type)&&("Repair".equals(type));
    }
}

