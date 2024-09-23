package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccineProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Vaccine;
import ideas.vaccineTracker.vaccine_tracker_data.service.VaccineService;
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
class VaccineControllerTest {

    @Mock
    private VaccineService vaccineService;

    @InjectMocks
    private VaccineController vaccineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVaccineSuccess() {
        Vaccine vaccine = new Vaccine();
        when(vaccineService.createVaccine(vaccine)).thenReturn(vaccine);

        ResponseEntity<Vaccine> response = vaccineController.createVaccine(vaccine);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(vaccine, response.getBody());
        verify(vaccineService, times(1)).createVaccine(vaccine);
    }

    @Test
    void testGetVaccineByIdSuccess() {
        Integer vaccineId = 1;
        VaccineProjection vaccineProjection = mock(VaccineProjection.class);
        when(vaccineService.getVaccineById(vaccineId)).thenReturn(vaccineProjection);

        ResponseEntity<VaccineProjection> response = vaccineController.getVaccineById(vaccineId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(vaccineProjection, response.getBody());
        verify(vaccineService, times(1)).getVaccineById(vaccineId);
    }

    @Test
    void testGetAllVaccinesSuccess() {
        List<VaccineProjection> vaccines = Collections.emptyList();
        when(vaccineService.getAllVaccines()).thenReturn(vaccines);

        ResponseEntity<List<VaccineProjection>> response = vaccineController.getAllVaccines();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(vaccines, response.getBody());
        verify(vaccineService, times(1)).getAllVaccines();
    }

    @Test
    void testUpdateVaccineSuccess() {
        Integer vaccineId = 1;
        Vaccine vaccineDetails = new Vaccine();
        Vaccine updatedVaccine = new Vaccine();
        when(vaccineService.updateVaccine(vaccineId, vaccineDetails)).thenReturn(updatedVaccine);

        ResponseEntity<Vaccine> response = vaccineController.updateVaccine(vaccineId, vaccineDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedVaccine, response.getBody());
        verify(vaccineService, times(1)).updateVaccine(vaccineId, vaccineDetails);
    }

    @Test
    void testDeleteVaccineSuccess() {
        Integer vaccineId = 1;
        doNothing().when(vaccineService).deleteVaccine(vaccineId);

        ResponseEntity<Void> response = vaccineController.deleteVaccine(vaccineId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(vaccineService, times(1)).deleteVaccine(vaccineId);
    }
}
