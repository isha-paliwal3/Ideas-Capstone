package ideas.vaccineTracker.vaccine_tracker_data.dto;

public interface VaccinationLogProjection {

    Integer getLogId();

    PatientProjection getPatient();

    VaccineProjection getVaccine();

    DoctorProjection getDoctor();

    Integer getDoseNumber();

    String getVaccinationDate();

    String getNextDueDate();

    String getVaccinationStatus();

    public interface DoctorProjection {

        Integer getDoctorId();

        String getDoctorName();

        String getEmail();

        String getSpecialization();

        String getPhoneNumber();

    }

    public interface VaccineProjection {

        Integer getVaccineId();

        String getVaccineName();

        String getRoute();

        Integer getMaxRequiredDoses();

        double getEffectivenessPercentage();
    }

    public interface PatientProjection {

        Integer getPatientId();

        String getPatientName();

        String getDateOfBirth();

        String getGender();

        String getAddress();

        String getPhoneNumber();

    }

}
