package ideas.vaccineTracker.vaccine_tracker_data.repository;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccineProjection;
import  ideas.vaccineTracker.vaccine_tracker_data.entity.Vaccine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface VaccineRepository extends CrudRepository<Vaccine, Integer> {

    @NonNull

    List<VaccineProjection> findByVaccineNameContainingIgnoreCase(String vaccineName);

    Optional<VaccineProjection> findByVaccineId(Integer id);

    List<VaccineProjection> findBy();

}
