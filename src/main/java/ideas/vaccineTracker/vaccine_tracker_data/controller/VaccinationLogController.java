package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccinationLogProjection;
import ideas.vaccineTracker.vaccine_tracker_data.exception.ResourceNotFoundException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.VaccinationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor/vaccination-logs")
public class VaccinationLogController {

    @Autowired
    private VaccinationLogRepository vaccinationLogRepository;

    // Get all vaccination logs
    @GetMapping
    public ResponseEntity<List<VaccinationLogProjection>> getAllVaccinationLogs() {
        List<VaccinationLogProjection> logs = vaccinationLogRepository.findBy().stream()
                .map(log -> (VaccinationLogProjection) log)
                .toList();

        if (logs.isEmpty()) {
            throw new ResourceNotFoundException("No vaccination logs found");
        }

        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    // Get vaccination logs by patient ID
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<VaccinationLogProjection>> getLogsByPatientId(@PathVariable Integer patientId) {
        List<VaccinationLogProjection> logs = vaccinationLogRepository.findByPatientPatientId(patientId);

        if (logs.isEmpty()) {
            throw new ResourceNotFoundException("No vaccination logs found for patient ID: " + patientId);
        }

        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    // Get vaccination logs by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<VaccinationLogProjection>> getLogsByStatus(@PathVariable String status) {
        List<VaccinationLogProjection> logs = vaccinationLogRepository.findByVaccinationStatus(status);

        if (logs.isEmpty()) {
            throw new ResourceNotFoundException("No vaccination logs found with status: " + status);
        }

        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    // Get upcoming vaccinations for a patient
    @GetMapping("/patient/{patientId}/upcoming")
    public ResponseEntity<List<VaccinationLogProjection>> getUpcomingVaccinations(@PathVariable Integer patientId) {
        List<VaccinationLogProjection> logs = vaccinationLogRepository.findByPatientPatientIdAndVaccinationStatusOrderByNextDueDateAsc(patientId, "Upcoming");

        if (logs.isEmpty()) {
            throw new ResourceNotFoundException("No upcoming vaccinations found for patient ID: " + patientId);
        }

        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    // Check if a specific vaccine is due for a patient
    @GetMapping("/patient/{patientId}/vaccine/{vaccineId}/due")
    public ResponseEntity<Boolean> isVaccineDue(@PathVariable Integer patientId, @PathVariable Integer vaccineId) {
        Integer count = vaccinationLogRepository.countByPatientPatientIdAndVaccineVaccineIdAndVaccinationStatus(patientId, vaccineId, "Upcoming");

        if (count == null || count == 0) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    // Get overdue vaccinations
    @GetMapping("/overdue")
    public ResponseEntity<List<VaccinationLogProjection>> getOverdueVaccinations(@RequestParam String currentDate) {
        List<VaccinationLogProjection> logs = vaccinationLogRepository.findByNextDueDateBeforeAndVaccinationStatus(currentDate, "Upcoming");

        if (logs.isEmpty()) {
            throw new ResourceNotFoundException("No overdue vaccinations found");
        }

        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    // Count total vaccinations administered
    @GetMapping("/count/completed")
    public ResponseEntity<Integer> countCompletedVaccinations() {
        Integer count = vaccinationLogRepository.countByVaccinationStatus("Completed");

        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<VaccinationLogProjection>> getVaccinationLogsByPatientId(@PathVariable Integer patientId) {
        List<VaccinationLogProjection> logs = vaccinationLogRepository.findAllByPatientPatientId(patientId);

        if (logs.isEmpty()) {
            throw new ResourceNotFoundException("No vaccination logs found for patient ID: " + patientId);
        }

        return new ResponseEntity<>(logs, HttpStatus.OK);
    }
}
