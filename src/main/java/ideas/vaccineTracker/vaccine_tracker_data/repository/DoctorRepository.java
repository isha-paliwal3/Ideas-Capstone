package ideas.vaccineTracker.vaccine_tracker_data.repository;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
//Todo:: Hash password
    Optional<Doctor> findByEmailAndPassword(String email, String password);

    Boolean existsByEmail(String email);

    Optional<Doctor> findByEmail(String email);
}

