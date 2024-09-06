package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.dto.ImmunizationScheduleProjection;
import ideas.vaccineTracker.vaccine_tracker_data.exception.ResourceNotFoundException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.ImmunizationScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor/immunization-schedules")
public class ImmunizationScheduleController {

    @Autowired
    private ImmunizationScheduleRepository immunizationScheduleRepository;

    // Get all immunization schedules
    @GetMapping
    public ResponseEntity<List<ImmunizationScheduleProjection>> getAllImmunizationSchedules() {
        List<ImmunizationScheduleProjection> schedules = immunizationScheduleRepository.findBy().stream()
                .map(schedule -> (ImmunizationScheduleProjection) schedule)
                .toList();

        if (schedules.isEmpty()) {
            throw new ResourceNotFoundException("No immunization schedules found");
        }

        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    // Get immunization schedules by vaccine ID
    @GetMapping("/vaccine/{vaccineId}")
    public ResponseEntity<List<ImmunizationScheduleProjection>> getSchedulesByVaccineId(@PathVariable Integer vaccineId) {
        List<ImmunizationScheduleProjection> schedules = immunizationScheduleRepository.findByVaccineVaccineId(vaccineId);

        if (schedules.isEmpty()) {
            throw new ResourceNotFoundException("No immunization schedules found for vaccine ID: " + vaccineId);
        }

        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    // Get immunization schedules by exact age in days
    @GetMapping("/age/{ageInDays}")
    public ResponseEntity<List<ImmunizationScheduleProjection>> getSchedulesByAgeInDays(@PathVariable Integer ageInDays) {
        List<ImmunizationScheduleProjection> schedules = immunizationScheduleRepository.findByAgeInDays(ageInDays);

        if (schedules.isEmpty())
            throw new ResourceNotFoundException("No immunization schedules found for age in days: " + ageInDays);

        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    // Get immunization schedules for patients older than or equal to a certain age
    @GetMapping("/age/older-than/{ageInDays}")
    public ResponseEntity<List<ImmunizationScheduleProjection>> getSchedulesOlderThanAge(@PathVariable Integer ageInDays) {
        List<ImmunizationScheduleProjection> schedules = immunizationScheduleRepository.findByAgeInDaysGreaterThanEqualOrderByAgeInDays(ageInDays);

        if (schedules.isEmpty()) {
            throw new ResourceNotFoundException("No immunization schedules found for patients older than or equal to: " + ageInDays + " days");
        }

        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    // Get immunization schedules for patients younger than or equal to a certain age
    @GetMapping("/age/younger-than/{ageInDays}")
    public ResponseEntity<List<ImmunizationScheduleProjection>> getSchedulesYoungerThanAge(@PathVariable Integer ageInDays) {
        List<ImmunizationScheduleProjection> schedules = immunizationScheduleRepository.findByAgeInDaysLessThanEqualOrderByAgeInDays(ageInDays);

        if (schedules.isEmpty()) {
            throw new ResourceNotFoundException("No immunization schedules found for patients younger than or equal to: " + ageInDays + " days");
        }

        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }
}
