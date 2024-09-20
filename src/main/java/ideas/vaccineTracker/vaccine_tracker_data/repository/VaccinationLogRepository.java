package ideas.vaccineTracker.vaccine_tracker_data.repository;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccinationLogProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.VaccinationLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface VaccinationLogRepository extends CrudRepository<VaccinationLog, Integer> {

    @NonNull
    List<VaccinationLogProjection> findBy();

    List<VaccinationLogProjection> findByPatientPatientId(Integer patientId);

    VaccinationLogProjection findByLogId(Integer logId);
}

