package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.dto.patient.PatientDTO;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Patient;
import ideas.vaccineTracker.vaccine_tracker_data.exception.ResourceNotFoundException;
import ideas.vaccineTracker.vaccine_tracker_data.exception.DuplicateResourceException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/patients")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        if (patientRepository.existsById(patient.getPatientId())) {
            throw new DuplicateResourceException("Patient with ID " + patient.getPatientId() + " already exists.");
        }
        Patient savedPatient = patientRepository.save(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Integer id) {
        PatientDTO patient = patientRepository.findByPatientId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));

        patient.setPatientName(patientDetails.getPatientName());
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setGender(patientDetails.getGender());
        patient.setAddress(patientDetails.getAddress());
        patient.setPhoneNumber(patientDetails.getPhoneNumber());
        patient.setVaccinationLogs(patientDetails.getVaccinationLogs());

        Patient updatedPatient = patientRepository.save(patient);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));

        patientRepository.delete(patient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientRepository.findBy();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/search/patients/name/{name}")
    public ResponseEntity<List<PatientDTO>> searchPatientsByName(@PathVariable String name) {
        List<PatientDTO> patients = patientRepository.findDistinctPatientsByName(name);
        if (patients.isEmpty()) {
            throw new ResourceNotFoundException("No patients found with name: " + name);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/search/patients/phoneNumber/{phoneNumber}")
    public ResponseEntity<PatientDTO> searchPatientByPhoneNumber(@PathVariable String phoneNumber) {
        PatientDTO patient = patientRepository.findDistinctByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("No patient found with phone number: " + phoneNumber));
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
}
