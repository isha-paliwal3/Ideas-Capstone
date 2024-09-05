package ideas.vaccineTracker.vaccine_tracker_data.dto;

public interface ImmunizationScheduleProjection {

    Integer getImmId();

    Integer getAgeInDays();

    VaccineProjection getVaccine();
}

