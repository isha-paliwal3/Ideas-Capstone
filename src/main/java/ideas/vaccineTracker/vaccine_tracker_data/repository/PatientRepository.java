package ideas.vaccineTracker.vaccine_tracker_data.repository;

import ideas.vaccineTracker.vaccine_tracker_data.dto.PatientProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
    List<PatientProjection> findBy();

    Optional<PatientProjection> findByPatientId(Integer id);
}
