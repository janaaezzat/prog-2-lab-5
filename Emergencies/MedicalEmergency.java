package Emergencies;
import Facilities.Facilities;
import HumanPersonnel.HumanPersonnel;
import HumanPersonnel.MedicalOfficer;
import RoboticUnits.RoboticUnits;
import java.util.List;
import java.util.ArrayList;


public class MedicalEmergency extends Emergency{
    HumanPersonnel patient;//el mosab
    Facilities facility; //el facility ely 7asal feha el emergency 2tsab feha
    MedicalOfficer medicalAssistance;

    public MedicalEmergency(String location, HumanPersonnel patient,Facilities facility, int emergencyHandleTime) {
       super(location, emergencyHandleTime);

      if (patient == null || facility == null) {
        System.out.println("Invalid patient or facility.");
        return;
        }

    this.patient = patient;
    this.facility = facility;
 }

@Override
public boolean checkResponders(List<HumanPersonnel> humans,
                               List<RoboticUnits> robots) {
    medicalAssistance = null;
  // Search for a medical officer in the list of human personnel
    for (HumanPersonnel h : humans) {
        if (h.canProvideMedicalAssistance()) {
            medicalAssistance = (MedicalOfficer) h;
            break;
        }
    }
   if (medicalAssistance == null) {
    return false;
   }
  // Check if the facility is operational and has enough medical supplies
  if (!facility.getOperationalStatus()) {
    return false;
  }

  if (facility.getMedicalSupplies() < 5) {
    return false;
   }
medicalAssistance.setAvailable(false);
medicalAssistance.setCurrentLocation(location);


    return true;
  }


@Override
public void cantHandleEmergency() {
    status = false; //emergency unresolved
    failureReason = "No available medical officer, insufficient medical supplies, or facility is not operational.";
}

@Override
public void handledSuccessfully() {

    

    facility.consumeMedicalSupplies(5);

    patient.increaseHealth(3);

    medicalAssistance.setAvailable(true);

    status = true;
}



}