package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccineProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Vaccine;
import ideas.vaccineTracker.vaccine_tracker_data.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/vaccines")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @PostMapping
    public ResponseEntity<Vaccine> createVaccine(@RequestBody Vaccine vaccine) {
        Vaccine createdVaccine = vaccineService.createVaccine(vaccine);
        return new ResponseEntity<>(createdVaccine, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccineProjection> getVaccineById(@PathVariable Integer id) {
        VaccineProjection vaccine = vaccineService.getVaccineById(id);
        return new ResponseEntity<>(vaccine, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VaccineProjection>> getAllVaccines() {
        List<VaccineProjection> vaccines = vaccineService.getAllVaccines();
        return new ResponseEntity<>(vaccines, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaccine> updateVaccine(@PathVariable Integer id, @RequestBody Vaccine vaccineDetails) {
        Vaccine updatedVaccine = vaccineService.updateVaccine(id, vaccineDetails);
        return new ResponseEntity<>(updatedVaccine, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Integer id) {
        vaccineService.deleteVaccine(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
