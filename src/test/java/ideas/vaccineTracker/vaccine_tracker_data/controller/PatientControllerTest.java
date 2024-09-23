package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.dto.PatientProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Patient;
import ideas.vaccineTracker.vaccine_tracker_data.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePatientSuccess() {
        Patient patient = new Patient();
        when(patientService.createPatient(patient)).thenReturn(patient);

        ResponseEntity<Patient> response = patientController.createPatient(patient);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).createPatient(patient);
    }

    @Test
    void testGetPatientByIdSuccess() {
        Integer patientId = 1;
        PatientProjection patientProjection = mock(PatientProjection.class);
        when(patientService.getPatientById(patientId)).thenReturn(patientProjection);

        ResponseEntity<PatientProjection> response = patientController.getPatientById(patientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(patientProjection, response.getBody());
        verify(patientService, times(1)).getPatientById(patientId);
    }

    @Test
    void testGetAllPatientsSuccess() {
        List<PatientProjection> patients = Collections.emptyList();
        when(patientService.getAllPatients()).thenReturn(patients);

        ResponseEntity<List<PatientProjection>> response = patientController.getAllPatients();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(patients, response.getBody());
        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    void testUpdatePatientSuccess() {
        Integer patientId = 1;
        Patient patientDetails = new Patient();
        Patient updatedPatient = new Patient();
        when(patientService.updatePatient(patientId, patientDetails)).thenReturn(updatedPatient);

        ResponseEntity<Patient> response = patientController.updatePatient(patientId, patientDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedPatient, response.getBody());
        verify(patientService, times(1)).updatePatient(patientId, patientDetails);
    }

    @Test
    void testDeletePatientSuccess() {
        Integer patientId = 1;
        doNothing().when(patientService).deletePatient(patientId);

        ResponseEntity<Void> response = patientController.deletePatient(patientId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(patientService, times(1)).deletePatient(patientId);
    }
}
