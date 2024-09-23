package ideas.vaccineTracker.vaccine_tracker_data.service;

import ideas.vaccineTracker.vaccine_tracker_data.dto.ImmunizationScheduleProjection;
import ideas.vaccineTracker.vaccine_tracker_data.dto.PatientVaccinationDueProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.ImmunizationSchedule;
import ideas.vaccineTracker.vaccine_tracker_data.exception.ResourceNotFoundException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.ImmunizationScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImmunizationScheduleServiceTest {

    @Mock
    private ImmunizationScheduleRepository immunizationScheduleRepository;

    @InjectMocks
    private ImmunizationScheduleService immunizationScheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllImmunizationSchedules() {
        ImmunizationScheduleProjection schedule1 = mock(ImmunizationScheduleProjection.class);
        ImmunizationScheduleProjection schedule2 = mock(ImmunizationScheduleProjection.class);

        when(immunizationScheduleRepository.findBy()).thenReturn(Arrays.asList(schedule1, schedule2));

        List<ImmunizationScheduleProjection> schedules = immunizationScheduleService.getAllImmunizationSchedules();

        assertNotNull(schedules);
        assertEquals(2, schedules.size());
        verify(immunizationScheduleRepository, times(1)).findBy();
    }

    @Test
    void testGetSchedulesByVaccineIdSuccess() {
        ImmunizationScheduleProjection schedule = mock(ImmunizationScheduleProjection.class);
        when(immunizationScheduleRepository.findByVaccineVaccineId(1)).thenReturn(Arrays.asList(schedule));

        List<ImmunizationScheduleProjection> schedules = immunizationScheduleService.getSchedulesByVaccineId(1);

        assertNotNull(schedules);
        assertEquals(1, schedules.size());
        verify(immunizationScheduleRepository, times(1)).findByVaccineVaccineId(1);
    }

    @Test
    void testGetSchedulesByVaccineIdNotFound() {
        when(immunizationScheduleRepository.findByVaccineVaccineId(1)).thenReturn(Arrays.asList());

        assertThrows(ResourceNotFoundException.class, () -> immunizationScheduleService.getSchedulesByVaccineId(1));
    }

    @Test
    void testCreateImmunizationSchedule() {
        ImmunizationSchedule schedule = new ImmunizationSchedule();
        schedule.setAgeInDays(30);

        when(immunizationScheduleRepository.save(any(ImmunizationSchedule.class))).thenReturn(schedule);

        ImmunizationSchedule createdSchedule = immunizationScheduleService.createImmunizationSchedule(schedule);

        assertNotNull(createdSchedule);
        assertEquals(30, createdSchedule.getAgeInDays());
    }

    @Test
    void testUpdateImmunizationScheduleSuccess() {
        ImmunizationSchedule schedule = new ImmunizationSchedule();
        schedule.setAgeInDays(30);

        ImmunizationSchedule updatedDetails = new ImmunizationSchedule();
        updatedDetails.setAgeInDays(60);

        when(immunizationScheduleRepository.findById(1)).thenReturn(Optional.of(schedule));
        when(immunizationScheduleRepository.save(any(ImmunizationSchedule.class))).thenReturn(schedule);

        ImmunizationSchedule updatedSchedule = immunizationScheduleService.updateImmunizationSchedule(1, updatedDetails);

        assertNotNull(updatedSchedule);
        assertEquals(60, updatedSchedule.getAgeInDays());
    }

    @Test
    void testUpdateImmunizationScheduleNotFound() {
        ImmunizationSchedule scheduleDetails = new ImmunizationSchedule();

        when(immunizationScheduleRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> immunizationScheduleService.updateImmunizationSchedule(1, scheduleDetails));
    }

    @Test
    void testDeleteImmunizationScheduleSuccess() {
        ImmunizationSchedule schedule = new ImmunizationSchedule();
        schedule.setAgeInDays(30);

        when(immunizationScheduleRepository.findById(1)).thenReturn(Optional.of(schedule));

        immunizationScheduleService.deleteImmunizationSchedule(1);

        verify(immunizationScheduleRepository, times(1)).delete(schedule);
    }

    @Test
    void testDeleteImmunizationScheduleNotFound() {
        when(immunizationScheduleRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> immunizationScheduleService.deleteImmunizationSchedule(1));
    }

    @Test
    void testGetVaccinationsDueWithin7Days() {
        PatientVaccinationDueProjection dueProjection = mock(PatientVaccinationDueProjection.class);
        when(immunizationScheduleRepository.findVaccinationsDueWithin7Days()).thenReturn(Collections.singletonList(dueProjection));

        List<PatientVaccinationDueProjection> dueVaccinations = immunizationScheduleService.getVaccinationsDueWithin7Days();

        assertNotNull(dueVaccinations);
        assertEquals(1, dueVaccinations.size());
        verify(immunizationScheduleRepository, times(1)).findVaccinationsDueWithin7Days();
    }

    @Test
    void testCountVaccinationsDueWithin7Days() {
        when(immunizationScheduleRepository.countVaccinationsDueWithin10Days()).thenReturn(5L);

        long count = immunizationScheduleService.countVaccinationsDueWithin7Days();

        assertEquals(5, count);
        verify(immunizationScheduleRepository, times(1)).countVaccinationsDueWithin10Days();
    }
}
