package ideas.vaccineTracker.vaccine_tracker_data.vacinationlogservice;

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
import ideas.vaccineTracker.vaccine_tracker_data.service.VaccinationLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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
    void getAllVaccinationLogs() {
        List<VaccinationLogProjection> mockedLogs = MockUtils.getVaccinationLogProjectionList();

        when(vaccinationLogRepository.findBy()).thenReturn(mockedLogs);

        List<VaccinationLogProjection> actualVaccinationProjections = vaccinationLogService.getAllVaccinationLogs();
        assertNotNull(actualVaccinationProjections);
        assertEquals(actualVaccinationProjections, mockedLogs);
    }

    @Test
    void createVaccinationLog() {
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

        // Set dummy data
        patient.setPatientId(1);
        patient.setPatientName("John Doe");
        patient.setDateOfBirth("1990-05-15");
        patient.setGender("Male");
        patient.setAddress("123 Main St, Springfield");
        patient.setPhoneNumber("9876543210");

        // Ensure vaccinationLogs is null
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

        // Ensure vaccinationLogs is null
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
        Patient patient = buildPatient(); // Assuming buildPatient is already defined

        // Create a Vaccine object with dummy data
        Vaccine vaccine = buildVaccine(); // Assuming buildVaccine is already defined

        // Create a Doctor object with dummy data
        Doctor doctor = buildDoctor(); // Assuming buildDoctor is already defined

        // Set dummy data for the vaccination log
        VaccinationLog vaccinationLog = new VaccinationLog();
        vaccinationLog.setLogId(1);
        vaccinationLog.setPatient(patient);
        vaccinationLog.setVaccine(vaccine);
        vaccinationLog.setDoctor(doctor);
        vaccinationLog.setDoseNumber(1);
        vaccinationLog.setVaccinationDate(LocalDate.now().toString()); // Current date as vaccination date
        vaccinationLog.setNextDueDate(LocalDate.now().plusMonths(1).toString()); // Next due date is 1 month after vaccination
        vaccinationLog.setVaccinationStatus("Completed");

        return vaccinationLog;
    }
}
