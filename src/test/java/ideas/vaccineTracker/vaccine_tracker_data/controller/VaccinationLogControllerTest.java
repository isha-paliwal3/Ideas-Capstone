package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccinationLogProjection;
import ideas.vaccineTracker.vaccine_tracker_data.dto.vaccinationLog.VaccinationLogDTO;
import ideas.vaccineTracker.vaccine_tracker_data.entity.VaccinationLog;
import ideas.vaccineTracker.vaccine_tracker_data.service.VaccinationLogService;
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
class VaccinationLogControllerTest {

    @Mock
    private VaccinationLogService vaccinationLogService;

    @InjectMocks
    private VaccinationLogController vaccinationLogController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllVaccinationLogsSuccess() {
        List<VaccinationLogProjection> logs = Collections.emptyList();
        when(vaccinationLogService.getAllVaccinationLogs()).thenReturn(logs);

        ResponseEntity<List<VaccinationLogProjection>> response = vaccinationLogController.getAllVaccinationLogs();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(logs, response.getBody());
        verify(vaccinationLogService, times(1)).getAllVaccinationLogs();
    }

    @Test
    void testGetLogsByPatientIdSuccess() {
        Integer patientId = 1;
        List<VaccinationLogProjection> logs = Collections.emptyList();
        when(vaccinationLogService.getLogsByPatientId(patientId)).thenReturn(logs);

        ResponseEntity<List<VaccinationLogProjection>> response = vaccinationLogController.getLogsByPatientId(patientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(logs, response.getBody());
        verify(vaccinationLogService, times(1)).getLogsByPatientId(patientId);
    }

    @Test
    void testCreateVaccinationLogSuccess() {
        VaccinationLogDTO dto = new VaccinationLogDTO();
        VaccinationLog createdLog = new VaccinationLog();

        when(vaccinationLogService.createVaccinationLog(dto)).thenReturn(createdLog);

        ResponseEntity<VaccinationLog> response = vaccinationLogController.createVaccinationLog(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdLog, response.getBody());
        verify(vaccinationLogService, times(1)).createVaccinationLog(dto);
    }

    @Test
    void testUpdateVaccinationLogSuccess() {
        Integer logId = 1;
        VaccinationLog logDetails = new VaccinationLog();
        VaccinationLogProjection updatedLog = mock(VaccinationLogProjection.class);

        when(vaccinationLogService.updateVaccinationLog(logId, logDetails)).thenReturn(updatedLog);

        ResponseEntity<VaccinationLogProjection> response = vaccinationLogController.updateVaccinationLog(logId, logDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedLog, response.getBody());
        verify(vaccinationLogService, times(1)).updateVaccinationLog(logId, logDetails);
    }

    @Test
    void testDeleteVaccinationLogSuccess() {
        Integer logId = 1;

        doNothing().when(vaccinationLogService).deleteVaccinationLog(logId);

        ResponseEntity<Void> response = vaccinationLogController.deleteVaccinationLog(logId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(vaccinationLogService, times(1)).deleteVaccinationLog(logId);
    }
}
