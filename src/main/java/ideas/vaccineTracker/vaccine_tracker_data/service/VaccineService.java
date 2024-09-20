package ideas.vaccineTracker.vaccine_tracker_data.service;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccineProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Vaccine;
import ideas.vaccineTracker.vaccine_tracker_data.exception.ResourceNotFoundException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    public Vaccine createVaccine(Vaccine vaccine) {
        return vaccineRepository.save(vaccine);
    }

    public VaccineProjection getVaccineById(Integer id) {
        return vaccineRepository.findByVaccineId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found with id: " + id));
    }

    public List<VaccineProjection> getAllVaccines() {
        return vaccineRepository.findBy();
    }
        public Vaccine updateVaccine(Integer id, Vaccine vaccineDetails) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found with id: " + id));

        vaccine.setVaccineName(vaccineDetails.getVaccineName());
        vaccine.setRoute(vaccineDetails.getRoute());
        vaccine.setMaxRequiredDoses(vaccineDetails.getMaxRequiredDoses());
        vaccine.setEffectivenessPercentage(vaccineDetails.getEffectivenessPercentage());

        return vaccineRepository.save(vaccine);
    }

    public void deleteVaccine(Integer id) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found with id: " + id));
        vaccineRepository.delete(vaccine);
    }
}
