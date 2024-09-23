package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.dto.ImmunizationScheduleProjection;
import ideas.vaccineTracker.vaccine_tracker_data.dto.PatientVaccinationDueProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.ImmunizationSchedule;
import ideas.vaccineTracker.vaccine_tracker_data.service.ImmunizationScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ImmunizationScheduleController {

    @Autowired
    private ImmunizationScheduleService immunizationScheduleService;

    @PostMapping("/adminAuth/immunization-schedules")
    public ResponseEntity<ImmunizationSchedule> createImmunizationSchedule(@RequestBody ImmunizationSchedule schedule) {
        ImmunizationSchedule createdSchedule = immunizationScheduleService.createImmunizationSchedule(schedule);
        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }

    @GetMapping("/immunization-schedules")
    public ResponseEntity<List<ImmunizationScheduleProjection>> getAllImmunizationSchedules() {
        List<ImmunizationScheduleProjection> schedules = immunizationScheduleService.getAllImmunizationSchedules();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/immunization-schedules/vaccine/{vaccineId}")
    public ResponseEntity<List<ImmunizationScheduleProjection>> getSchedulesByVaccineId(@PathVariable Integer vaccineId) {
        List<ImmunizationScheduleProjection> schedules = immunizationScheduleService.getSchedulesByVaccineId(vaccineId);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/immunization-schedules/upcoming")
    public ResponseEntity<List<PatientVaccinationDueProjection>> getVaccinationsDueWithin7Days() {
        List<PatientVaccinationDueProjection> dueVaccinations = immunizationScheduleService.getVaccinationsDueWithin7Days();
        return new ResponseEntity<>(dueVaccinations, HttpStatus.OK);
    }

    @GetMapping("/immunization-schedules/count-upcoming")
    public ResponseEntity<Long> countUpcomingVaccinations() {
        long count = immunizationScheduleService.countVaccinationsDueWithin7Days();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PutMapping("/adminAuth/immunization-schedules/{id}")
    public ResponseEntity<ImmunizationSchedule> updateImmunizationSchedule(@PathVariable Integer id, @RequestBody ImmunizationSchedule scheduleDetails) {
        ImmunizationSchedule updatedSchedule = immunizationScheduleService.updateImmunizationSchedule(id, scheduleDetails);
        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }

    @DeleteMapping("/adminAuth/immunization-schedules/{id}")
    public ResponseEntity<Void> deleteImmunizationSchedule(@PathVariable Integer id) {
        immunizationScheduleService.deleteImmunizationSchedule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
