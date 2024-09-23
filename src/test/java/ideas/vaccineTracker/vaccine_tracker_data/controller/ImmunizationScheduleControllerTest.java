package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.dto.ImmunizationScheduleProjection;
import ideas.vaccineTracker.vaccine_tracker_data.dto.PatientVaccinationDueProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.ImmunizationSchedule;
import ideas.vaccineTracker.vaccine_tracker_data.service.ImmunizationScheduleService;
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
class ImmunizationScheduleControllerTest {

    @Mock
    private ImmunizationScheduleService immunizationScheduleService;

    @InjectMocks
    private ImmunizationScheduleController immunizationScheduleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllImmunizationSchedulesSuccess() {
        List<ImmunizationScheduleProjection> schedules = Collections.emptyList();
        when(immunizationScheduleService.getAllImmunizationSchedules()).thenReturn(schedules);

        ResponseEntity<List<ImmunizationScheduleProjection>> response = immunizationScheduleController.getAllImmunizationSchedules();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(schedules, response.getBody());
        verify(immunizationScheduleService, times(1)).getAllImmunizationSchedules();
    }

    @Test
    void testGetSchedulesByVaccineIdSuccess() {
        Integer vaccineId = 1;
        List<ImmunizationScheduleProjection> schedules = Collections.emptyList();
        when(immunizationScheduleService.getSchedulesByVaccineId(vaccineId)).thenReturn(schedules);

        ResponseEntity<List<ImmunizationScheduleProjection>> response = immunizationScheduleController.getSchedulesByVaccineId(vaccineId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(schedules, response.getBody());
        verify(immunizationScheduleService, times(1)).getSchedulesByVaccineId(vaccineId);
    }

    @Test
    void testCreateImmunizationScheduleSuccess() {
        ImmunizationSchedule schedule = new ImmunizationSchedule();
        when(immunizationScheduleService.createImmunizationSchedule(schedule)).thenReturn(schedule);

        ResponseEntity<ImmunizationSchedule> response = immunizationScheduleController.createImmunizationSchedule(schedule);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(schedule, response.getBody());
        verify(immunizationScheduleService, times(1)).createImmunizationSchedule(schedule);
    }

    @Test
    void testUpdateImmunizationScheduleSuccess() {
        Integer scheduleId = 1;
        ImmunizationSchedule scheduleDetails = new ImmunizationSchedule();
        ImmunizationSchedule updatedSchedule = new ImmunizationSchedule();
        when(immunizationScheduleService.updateImmunizationSchedule(scheduleId, scheduleDetails)).thenReturn(updatedSchedule);

        ResponseEntity<ImmunizationSchedule> response = immunizationScheduleController.updateImmunizationSchedule(scheduleId, scheduleDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedSchedule, response.getBody());
        verify(immunizationScheduleService, times(1)).updateImmunizationSchedule(scheduleId, scheduleDetails);
    }

    @Test
    void testDeleteImmunizationScheduleSuccess() {
        Integer scheduleId = 1;
        doNothing().when(immunizationScheduleService).deleteImmunizationSchedule(scheduleId);

        ResponseEntity<Void> response = immunizationScheduleController.deleteImmunizationSchedule(scheduleId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(immunizationScheduleService, times(1)).deleteImmunizationSchedule(scheduleId);
    }

    @Test
    void testGetVaccinationsDueWithin7DaysSuccess() {
        List<PatientVaccinationDueProjection> dueVaccinations = Collections.emptyList();
        when(immunizationScheduleService.getVaccinationsDueWithin7Days()).thenReturn(dueVaccinations);

        ResponseEntity<List<PatientVaccinationDueProjection>> response = immunizationScheduleController.getVaccinationsDueWithin7Days();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(dueVaccinations, response.getBody());
        verify(immunizationScheduleService, times(1)).getVaccinationsDueWithin7Days();
    }

    @Test
    void testCountUpcomingVaccinationsSuccess() {
        long count = 5L;
        when(immunizationScheduleService.countVaccinationsDueWithin7Days()).thenReturn(count);

        ResponseEntity<Long> response = immunizationScheduleController.countUpcomingVaccinations();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(count, response.getBody());
        verify(immunizationScheduleService, times(1)).countVaccinationsDueWithin7Days();
    }
}
