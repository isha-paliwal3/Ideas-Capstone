package ideas.vaccineTracker.vaccine_tracker_data.service;

import ideas.vaccineTracker.vaccine_tracker_data.MockUtils;
import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccinationLogProjection;
import ideas.vaccineTracker.vaccine_tracker_data.dto.vaccinationLog.VaccinationLogDTO;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Patient;
import ideas.vaccineTracker.vaccine_tracker_data.entity.VaccinationLog;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Vaccine;
import ideas.vaccineTracker.vaccine_tracker_data.repository.DoctorRepository;
import ideas.vaccineTracker.vaccine_tracker_data.repository.PatientRepository;
import ideas.vaccineTracker.vaccine_tracker_data.repository.VaccinationLogRepository;
import ideas.vaccineTracker.vaccine_tracker_data.repository.VaccineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VaccinationLogServiceTest {
    @Mock
    private VaccinationLogRepository vaccinationLogRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private VaccineRepository vaccineRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @InjectMocks
    private VaccinationLogService vaccinationLogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllVaccinationLogs() {
        List<VaccinationLogProjection> mockedLogs = MockUtils.getVaccinationLogProjectionList();

        when(vaccinationLogRepository.findBy()).thenReturn(mockedLogs);

        List<VaccinationLogProjection> actualVaccinationProjections = vaccinationLogService.getAllVaccinationLogs();
        assertNotNull(actualVaccinationProjections);
        assertEquals(actualVaccinationProjections, mockedLogs);
    }

    @Test
    void testCreateVaccinationLog() {
        VaccinationLogDTO mockedLog = buildVaccinationLogDTO();
        Patient mockedPatient = buildPatient();
        Doctor mockedDoctor = buildDoctor();
        Vaccine mockedVaccine = buildVaccine();
        VaccinationLog mockedVaccinationLog = buildVaccinationLog();

        Mockito.lenient().when(patientRepository.findById(mockedLog.getPatientId())).thenReturn(Optional.ofNullable(mockedPatient));
        Mockito.lenient().when(vaccineRepository.findById(mockedLog.getVaccineId())).thenReturn(Optional.ofNullable(mockedVaccine));
        Mockito.lenient().when(doctorRepository.findById(mockedLog.getDoctorId())).thenReturn(Optional.of(mockedDoctor));

        Mockito.lenient().when(vaccinationLogRepository.save(any(VaccinationLog.class))).thenReturn(mockedVaccinationLog);

        VaccinationLog actualVaccinationLog = vaccinationLogService.createVaccinationLog(mockedLog);
        assertNotNull(actualVaccinationLog);
        assertEquals(actualVaccinationLog, mockedVaccinationLog);
    }

    public VaccinationLogDTO buildVaccinationLogDTO() {
        VaccinationLogDTO dto = new VaccinationLogDTO();

        dto.setPatientId(1);
        dto.setVaccineId(101);
        dto.setDoctorId(1001);
        dto.setDoseNumber(1);
        dto.setVaccinationDate("2024-01-15");
        dto.setNextDueDate("2024-02-15");
        dto.setVaccinationStatus("Completed");

        return dto;
    }

    public Patient buildPatient() {
        Patient patient = new Patient();

        patient.setPatientId(1);
        patient.setPatientName("John Doe");
        patient.setDateOfBirth("1990-05-15");
        patient.setGender("Male");
        patient.setAddress("123 Main St, Springfield");
        patient.setPhoneNumber("9876543210");

        patient.setVaccinationLogs(null);

        return patient;
    }

    public Vaccine buildVaccine() {
        Vaccine vaccine = new Vaccine();

        // Set dummy data
        vaccine.setVaccineId(1);
        vaccine.setVaccineName("Bacillus Calmette-Guerin (BCG)");
        vaccine.setRoute("Intra-dermal");
        vaccine.setMaxRequiredDoses(1);
        vaccine.setEffectivenessPercentage(99.5);

        vaccine.setVaccinationLogs(null);

        return vaccine;
    }

    public Doctor buildDoctor() {
        Doctor doctor = new Doctor();

        // Set dummy data
        doctor.setDoctorId(1);
        doctor.setEmail("doctor@example.com");
        doctor.setPassword(UUID.randomUUID().toString()); // Simulating a generated password
        doctor.setDoctorName("Dr. John Doe");
        doctor.setSpecialization("Pediatrics");
        doctor.setPhoneNumber("9876543210");
        doctor.setRole("Doctor");

        return doctor;
    }

    public VaccinationLog buildVaccinationLog() {
        // Create a Patient object with dummy data
        Patient patient = buildPatient();

        // Create a Vaccine object with dummy data
        Vaccine vaccine = buildVaccine();

        // Create a Doctor object with dummy data
        Doctor doctor = buildDoctor();

        // Set dummy data for the vaccination log
        VaccinationLog vaccinationLog = new VaccinationLog();
        vaccinationLog.setLogId(1);
        vaccinationLog.setPatient(patient);
        vaccinationLog.setVaccine(vaccine);
        vaccinationLog.setDoctor(doctor);
        vaccinationLog.setDoseNumber(1);
        vaccinationLog.setVaccinationDate(LocalDate.now().toString());
        vaccinationLog.setNextDueDate(LocalDate.now().plusMonths(1).toString());
        vaccinationLog.setVaccinationStatus("Completed");

        return vaccinationLog;
    }

    @Test
    void testUpdateVaccinationLog() {
        Integer logId = 1;
        VaccinationLog existingLog = buildVaccinationLog();
        VaccinationLogDTO updatedLogDetails = buildVaccinationLogDTO();
        updatedLogDetails.setDoseNumber(2);

        Vaccine updatedVaccine = buildVaccine();
        updatedVaccine.setVaccineId(101);
        updatedVaccine.setVaccineName("Updated Vaccine Name");

        VaccinationLog updatedLog = buildVaccinationLog();
        updatedLog.setDoseNumber(2);
        updatedLog.setVaccine(updatedVaccine);

        Mockito.when(vaccinationLogRepository.findById(logId)).thenReturn(Optional.of(existingLog));
        Mockito.doReturn(Optional.of(updatedVaccine)).when(vaccineRepository).findById(updatedLogDetails.getVaccineId());
        Mockito.when(vaccinationLogRepository.save(any(VaccinationLog.class))).thenReturn(updatedLog);

        VaccinationLogProjection mockUpdatedLog = MockUtils.getMockVaccinationLogProjection(
                logId, 2, "2024-01-20", "2024-02-20", "Completed",
                1, "Dr. John Doe", "doctor@example.com", "Pediatrics", "9876543210",
                101, "Updated Vaccine Name", "Intra-dermal", 1, 99.5,
                1, "John Doe", "1990-05-15", "Male", "123 Main St, Springfield");

        Mockito.when(vaccinationLogRepository.findByLogId(logId)).thenReturn(mockUpdatedLog);

        VaccinationLogProjection updatedLogProjection = vaccinationLogService.updateVaccinationLog(logId, updatedLog);

        assertNotNull(updatedLogProjection);
        assertEquals("Updated Vaccine Name", updatedLogProjection.getVaccine().getVaccineName());
        assertEquals(2, updatedLogProjection.getDoseNumber());
    }

    @Test
    void testDeleteVaccinationLog() {
        Integer logId = 1;
        VaccinationLog existingLog = buildVaccinationLog();

        Mockito.when(vaccinationLogRepository.findById(logId)).thenReturn(Optional.of(existingLog));
        Mockito.doNothing().when(vaccinationLogRepository).delete(existingLog);

        vaccinationLogService.deleteVaccinationLog(logId);

        Mockito.verify(vaccinationLogRepository, Mockito.times(1)).delete(existingLog);
    }

}
