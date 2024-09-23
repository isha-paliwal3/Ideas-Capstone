package ideas.vaccineTracker.vaccine_tracker_data.service;
import ideas.vaccineTracker.vaccine_tracker_data.dto.PatientProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Patient;
import ideas.vaccineTracker.vaccine_tracker_data.exception.ResourceNotFoundException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePatient() {
        Patient patient = new Patient(1, "John Doe", "1990-05-15", "Male", "123 Main St", "1234567890");

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient createdPatient = patientService.createPatient(patient);

        assertNotNull(createdPatient);
        assertEquals("John Doe", createdPatient.getPatientName());
    }

    @Test
    void testGetPatientByIdSuccess() {
        PatientProjection mockPatient = mock(PatientProjection.class);
        when(mockPatient.getPatientId()).thenReturn(1);
        when(mockPatient.getPatientName()).thenReturn("John Doe");

        when(patientRepository.findByPatientId(1)).thenReturn(Optional.of(mockPatient));

        PatientProjection patient = patientService.getPatientById(1);

        assertNotNull(patient);
        assertEquals(1, patient.getPatientId());
        assertEquals("John Doe", patient.getPatientName());
    }

    @Test
    void testGetPatientByIdNotFound() {
        when(patientRepository.findByPatientId(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> patientService.getPatientById(1));
    }

    @Test
    void testGetAllPatients() {
        PatientProjection patient1 = mock(PatientProjection.class);
        PatientProjection patient2 = mock(PatientProjection.class);

        when(patientRepository.findBy()).thenReturn(Arrays.asList(patient1, patient2));

        List<PatientProjection> allPatients = patientService.getAllPatients();

        assertEquals(2, allPatients.size());
        verify(patientRepository, times(1)).findBy();
    }

    @Test
    void testUpdatePatientSuccess() {
        Patient patient = new Patient(1, "John Doe", "1990-05-15", "Male", "123 Main St", "1234567890");

        Patient updatedDetails = new Patient();
        updatedDetails.setPatientName("Jane Doe");
        updatedDetails.setDateOfBirth("1995-07-20");
        updatedDetails.setGender("Female");
        updatedDetails.setAddress("456 Oak St");
        updatedDetails.setPhoneNumber("9876543210");

        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient updatedPatient = patientService.updatePatient(1, updatedDetails);

        assertNotNull(updatedPatient);
        assertEquals("Jane Doe", updatedPatient.getPatientName());
        assertEquals("Female", updatedPatient.getGender());
        assertEquals("456 Oak St", updatedPatient.getAddress());
    }

    @Test
    void testUpdatePatientNotFound() {
        when(patientRepository.findById(1)).thenReturn(Optional.empty());

        Patient patientDetails = new Patient();
        assertThrows(ResourceNotFoundException.class, () -> patientService.updatePatient(1, patientDetails));
    }

    @Test
    void testDeletePatientSuccess() {
        Patient patient = new Patient(1, "John Doe", "1990-05-15", "Male", "123 Main St", "1234567890");

        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));

        patientService.deletePatient(1);

        verify(patientRepository, times(1)).delete(patient);
    }

    @Test
    void testDeletePatientNotFound() {
        when(patientRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> patientService.deletePatient(1));
    }
}
