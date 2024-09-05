package ideas.vaccineTracker.vaccine_tracker_data.dto;

public interface VaccineProjection {

    int getVaccineId();

    String getVaccineName();

    String getRoute();

    int getMaxRequiredDoses();

    double getEffectivenessPercentage();

}
