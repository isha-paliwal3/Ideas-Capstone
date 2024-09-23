package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import ideas.vaccineTracker.vaccine_tracker_data.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterDoctorSuccess() {
        Doctor doctor = new Doctor();
        when(doctorService.registerDoctor(doctor)).thenReturn(doctor);

        ResponseEntity<Doctor> response = doctorController.registerDoctor(doctor);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(doctor, response.getBody());
        verify(doctorService, times(1)).registerDoctor(doctor);
    }

    @Test
    void testUpdateDoctorSuccess() {
        Integer doctorId = 1;
        Doctor doctorDetails = new Doctor();
        Doctor updatedDoctor = new Doctor();
        when(doctorService.updateDoctor(doctorId, doctorDetails)).thenReturn(updatedDoctor);

        ResponseEntity<Doctor> response = doctorController.updateDoctor(doctorId, doctorDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedDoctor, response.getBody());
        verify(doctorService, times(1)).updateDoctor(doctorId, doctorDetails);
    }

    @Test
    void testDeleteDoctorSuccess() {
        Integer doctorId = 1;
        doNothing().when(doctorService).deleteDoctor(doctorId);

        ResponseEntity<Void> response = doctorController.deleteDoctor(doctorId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(doctorService, times(1)).deleteDoctor(doctorId);
    }
}
