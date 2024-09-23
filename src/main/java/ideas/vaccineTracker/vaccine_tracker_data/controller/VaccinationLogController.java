package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccinationLogProjection;
import ideas.vaccineTracker.vaccine_tracker_data.dto.vaccinationLog.VaccinationLogDTO;
import ideas.vaccineTracker.vaccine_tracker_data.entity.VaccinationLog;
import ideas.vaccineTracker.vaccine_tracker_data.service.VaccinationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/vaccination-logs")
public class VaccinationLogController {

    @Autowired
    private VaccinationLogService vaccinationLogService;

    @GetMapping
    public ResponseEntity<List<VaccinationLogProjection>> getAllVaccinationLogs() {
        List<VaccinationLogProjection> logs = vaccinationLogService.getAllVaccinationLogs();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<VaccinationLogProjection>> getLogsByPatientId(@PathVariable Integer patientId) {
        List<VaccinationLogProjection> logs = vaccinationLogService.getLogsByPatientId(patientId);
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VaccinationLog> createVaccinationLog(@RequestBody VaccinationLogDTO log) {
        VaccinationLog createdLog = vaccinationLogService.createVaccinationLog(log);
        return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccinationLogProjection> updateVaccinationLog(
            @PathVariable Integer id,
            @RequestBody VaccinationLog logDetails) {

        VaccinationLogProjection updatedLog = vaccinationLogService.updateVaccinationLog(id, logDetails);

        return new ResponseEntity<>(updatedLog, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccinationLog(@PathVariable Integer id) {
        vaccinationLogService.deleteVaccinationLog(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
