package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccineProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Vaccine;
import ideas.vaccineTracker.vaccine_tracker_data.exception.ResourceNotFoundException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VaccineController {

    @Autowired
    private VaccineRepository vaccineRepository;

    @PostMapping
    public ResponseEntity<Vaccine> createVaccine(@RequestBody Vaccine vaccine) {
        Vaccine savedVaccine = vaccineRepository.save(vaccine);
        return new ResponseEntity<>(savedVaccine, HttpStatus.CREATED);
    }

    @GetMapping("/vaccines/{id}")
    public ResponseEntity<VaccineProjection> getVaccineById(@PathVariable Integer id) {
        VaccineProjection vaccine = vaccineRepository.findByVaccineId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found with id: " + id));
        return new ResponseEntity<>(vaccine, HttpStatus.OK);
    }
    @PutMapping("/vaccines/{id}")
    public ResponseEntity<Vaccine> updateVaccine(@PathVariable Integer id, @RequestBody Vaccine vaccineDetails) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found with id: " + id));

        vaccine.setVaccineName(vaccineDetails.getVaccineName());
        vaccine.setRoute(vaccineDetails.getRoute());
        vaccine.setMaxRequiredDoses(vaccineDetails.getMaxRequiredDoses());
        vaccine.setEffectivenessPercentage(vaccineDetails.getEffectivenessPercentage());

        Vaccine updatedVaccine = vaccineRepository.save(vaccine);
        return new ResponseEntity<>(updatedVaccine, HttpStatus.OK);
    }

    @DeleteMapping("/vaccines/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Integer id) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found with id: " + id));

        vaccineRepository.delete(vaccine);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/vaccines")
    public ResponseEntity<List<VaccineProjection>> getAllVaccines() {
        List<VaccineProjection> vaccines = vaccineRepository.findBy();
        return new ResponseEntity<>(vaccines, HttpStatus.OK);
    }

    @GetMapping("/search/vaccine/name/{name}")
    public ResponseEntity<List<VaccineProjection>> searchPatientsByName(@PathVariable String name) {
        List<VaccineProjection> patients = vaccineRepository.findByVaccineNameContainingIgnoreCase(name);
        if (patients.isEmpty()) {
            throw new ResourceNotFoundException("No vaccines found with name: " + name);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

}
