package ideas.vaccineTracker.vaccine_tracker_data.repository;

import ideas.vaccineTracker.vaccine_tracker_data.dto.PatientProjection;
import ideas.vaccineTracker.vaccine_tracker_data.dto.patient.PatientDTO;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

    @Query("SELECT DISTINCT p FROM Patient p WHERE LOWER(p.patientName) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<PatientDTO> findDistinctPatientsByName(String name);

    @Query("SELECT DISTINCT p FROM Patient p WHERE p.phoneNumber = :phoneNumber")
    Optional<PatientDTO> findDistinctByPhoneNumber(String phoneNumber);

    @Query("SELECT DISTINCT p FROM Patient p WHERE p.dateOfBirth BETWEEN :startDate AND :endDate")
    List<PatientDTO> findDistinctByDateOfBirthBetween(String startDate, String endDate);

    List<PatientDTO> findBy();

    Optional<PatientDTO> findByPatientId(Integer id);
}
