package ideas.vaccineTracker.vaccine_tracker_data.dto;

public interface PatientProjection {

    Integer getPatientId();

    String getPatientName();

    String getDateOfBirth();

    String getGender();

    String getAddress();

    String getPhoneNumber();

    VaccinationLogProjection getVaccinationLogs();

    public interface VaccinationLogProjection {

        Integer getLogId();

        ideas.vaccineTracker.vaccine_tracker_data.dto.VaccinationLogProjection.VaccineProjection getVaccine();

        ideas.vaccineTracker.vaccine_tracker_data.dto.VaccinationLogProjection.DoctorProjection getDoctor();

        Integer getDoseNumber();

        String getVaccinationDate();

        String getNextDueDate();

        String getVaccinationStatus();
    }

}
