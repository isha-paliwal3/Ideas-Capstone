package ideas.vaccineTracker.vaccine_tracker_data.repository;
import ideas.vaccineTracker.vaccine_tracker_data.dto.ImmunizationScheduleProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.ImmunizationSchedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ImmunizationScheduleRepository extends CrudRepository<ImmunizationSchedule, Integer> {

    @NonNull
    List<ImmunizationSchedule> findAll();

    List<ImmunizationScheduleProjection> findByVaccineVaccineId(Integer vaccineId);

    List<ImmunizationScheduleProjection> findByAgeInDays(Integer ageInDays);

    List<ImmunizationScheduleProjection> findByAgeInDaysGreaterThanEqualOrderByAgeInDays(Integer age);

    List<ImmunizationScheduleProjection> findByAgeInDaysLessThanEqualOrderByAgeInDays(Integer age);

}
