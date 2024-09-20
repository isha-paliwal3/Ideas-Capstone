package ideas.vaccineTracker.vaccine_tracker_data.service;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccinationLogProjection;
import ideas.vaccineTracker.vaccine_tracker_data.dto.vaccinationLog.VaccinationLogDTO;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Patient;
import ideas.vaccineTracker.vaccine_tracker_data.entity.VaccinationLog;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Vaccine;
import ideas.vaccineTracker.vaccine_tracker_data.exception.ResourceNotFoundException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.DoctorRepository;
import ideas.vaccineTracker.vaccine_tracker_data.repository.PatientRepository;
import ideas.vaccineTracker.vaccine_tracker_data.repository.VaccinationLogRepository;
import ideas.vaccineTracker.vaccine_tracker_data.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationLogService {

    @Autowired
    private VaccinationLogRepository vaccinationLogRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<VaccinationLogProjection> getAllVaccinationLogs() {
        return vaccinationLogRepository.findBy();
    }

    public List<VaccinationLogProjection> getLogsByPatientId(Integer patientId) {
        List<VaccinationLogProjection> logs = vaccinationLogRepository.findByPatientPatientId(patientId);
        if (logs.isEmpty()) {
            throw new ResourceNotFoundException("No vaccination logs found for patient ID: " + patientId);
        }
        return logs;
    }


    public VaccinationLog createVaccinationLog(VaccinationLogDTO log) {
        Patient patient = patientRepository.findById(log.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + log.getPatientId()));
        Vaccine vaccine = vaccineRepository.findById(log.getVaccineId())
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found with ID: " + log.getVaccineId()));
        Doctor doctor = doctorRepository.findById(log.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + log.getDoctorId()));

        VaccinationLog vaccinationLog = new VaccinationLog();
        vaccinationLog.setPatient(patient);
        vaccinationLog.setVaccine(vaccine);
        vaccinationLog.setDoctor(doctor);

        vaccinationLog.setVaccinationDate(log.getVaccinationDate());
        vaccinationLog.setVaccinationStatus(log.getVaccinationStatus());
        vaccinationLog.setDoseNumber(log.getDoseNumber());
        vaccinationLog.setNextDueDate(log.getNextDueDate());

        return vaccinationLogRepository.save(vaccinationLog);
    }


    public VaccinationLogProjection updateVaccinationLog(Integer id, VaccinationLog logDetails) {
        VaccinationLog log = vaccinationLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccination log not found with id: " + id));

        Vaccine updatedVaccine = vaccineRepository.findById(logDetails.getVaccine().getVaccineId())
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine not found with id: " + logDetails.getVaccine().getVaccineId()));

        log.setVaccine(updatedVaccine);

        log.setDoseNumber(logDetails.getDoseNumber());
        log.setVaccinationDate(logDetails.getVaccinationDate());
        log.setNextDueDate(logDetails.getNextDueDate());
        log.setVaccinationStatus(logDetails.getVaccinationStatus());

        vaccinationLogRepository.save(log);

        return vaccinationLogRepository.findByLogId(id);
    }

    public void deleteVaccinationLog(Integer id) {
        VaccinationLog log = vaccinationLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccination log not found with id: " + id));
        vaccinationLogRepository.delete(log);
    }

}
