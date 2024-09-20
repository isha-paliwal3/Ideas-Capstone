package ideas.vaccineTracker.vaccine_tracker_data.dto;

public interface PatientVaccinationDueProjection {

     Integer getPatientId();
     String getPatientName();
     String getDateOfBirth();
     String getAddress();
     String getPhoneNumber();
     Integer getVaccineId();
     String getVaccineName();
     Integer getIdealAgeInDays();
     Integer getCurrentAgeInDays();
     String getNextDueDate();
}
