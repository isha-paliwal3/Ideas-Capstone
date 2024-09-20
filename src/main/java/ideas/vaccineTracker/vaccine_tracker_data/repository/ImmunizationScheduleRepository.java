package ideas.vaccineTracker.vaccine_tracker_data.repository;

import ideas.vaccineTracker.vaccine_tracker_data.dto.ImmunizationScheduleProjection;
import ideas.vaccineTracker.vaccine_tracker_data.dto.PatientVaccinationDueProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.ImmunizationSchedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ImmunizationScheduleRepository extends CrudRepository<ImmunizationSchedule, Integer> {

    @NonNull
    List<ImmunizationScheduleProjection> findBy();

    List<ImmunizationScheduleProjection> findByVaccineVaccineId(Integer vaccineId);

    @Query(value = "SELECT\n" +
            "    p.patient_id,\n" +
            "    p.patient_name,\n" +
            "    p.date_of_birth,\n" +
            "    p.address,\n" +
            "    p.phone_number,\n" +
            "    v.vaccine_id,\n" +
            "    v.vaccine_name,\n" +
            "    s.age_in_days AS ideal_age_in_days,\n" +
            "    DATEDIFF('DAY', p.date_of_birth, CURRENT_DATE) AS current_age_in_days,\n" +
            "    DATEADD('DAY', (s.age_in_days - DATEDIFF('DAY', p.date_of_birth, CURRENT_DATE)), CURRENT_DATE) AS next_due_date\n" +
            "FROM\n" +
            "    Patients p\n" +
            "JOIN\n" +
            "    Immunization_Schedule s ON TRUE\n" +
            "JOIN\n" +
            "    Vaccines v ON s.vaccine_id = v.vaccine_id\n" +
            "WHERE\n" +
            "    (s.age_in_days - DATEDIFF('DAY', p.date_of_birth, CURRENT_DATE)) <= 7\n" +
            "AND\n" +
            "    (s.age_in_days - DATEDIFF('DAY', p.date_of_birth, CURRENT_DATE)) >= 0;\n",
            nativeQuery = true)
    List<PatientVaccinationDueProjection> findVaccinationsDueWithin7Days();

    @Query(value = "SELECT \n" +
            "    COUNT(*)" +
            "FROM\n" +
            "    Patients p\n" +
            "JOIN\n" +
            "    Immunization_Schedule s ON TRUE\n" +
            "JOIN\n" +
            "    Vaccines v ON s.vaccine_id = v.vaccine_id\n" +
            "WHERE\n" +
            "    (s.age_in_days - DATEDIFF('DAY', p.date_of_birth, CURRENT_DATE)) <= 7\n" +
            "AND\n" +
            "    (s.age_in_days - DATEDIFF('DAY', p.date_of_birth, CURRENT_DATE)) >= 0;\n",
            nativeQuery = true)
    long countVaccinationsDueWithin10Days();

}
