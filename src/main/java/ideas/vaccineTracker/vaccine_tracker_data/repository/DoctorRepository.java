package ideas.vaccineTracker.vaccine_tracker_data.repository;

import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {

    Boolean existsByEmail(String email);

    Optional<Doctor> findByEmail(String email);
}

