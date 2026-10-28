package HumanPersonnel;

import java.util.List;

public class MedicalOfficer extends HumanPersonnel{
    public MedicalOfficer(String name, String identificationNum, String currentLocation, List<String> skills, double healthStatus){
        super(name, identificationNum, currentLocation, skills,healthStatus);
    }

    @Override
    public void duty() {
        System.out.println("Medical Officer "+getName());

    }

    @Override
    public boolean canPerform(String type) {
        return super.canPerform(type)&&("Medical".equals(type));
    }
}
