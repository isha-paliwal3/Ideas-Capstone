package ideas.vaccineTracker.vaccine_tracker_data.service;

import ideas.vaccineTracker.vaccine_tracker_data.dto.VaccineProjection;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Vaccine;
import ideas.vaccineTracker.vaccine_tracker_data.exception.ResourceNotFoundException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.VaccineRepository;
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
public class VaccineServiceTest {

    @Mock
    private VaccineRepository vaccineRepository;

    @InjectMocks
    private VaccineService vaccineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVaccine() {
        Vaccine vaccine = new Vaccine(1, "BCG", "Intra-dermal", 1, 99.5);

        when(vaccineRepository.save(any(Vaccine.class))).thenReturn(vaccine);

        Vaccine createdVaccine = vaccineService.createVaccine(vaccine);

        assertNotNull(createdVaccine);
        assertEquals("BCG", createdVaccine.getVaccineName());
    }

    @Test
    void testGetVaccineById_Success() {
        VaccineProjection mockVaccine = mock(VaccineProjection.class);
        when(mockVaccine.getVaccineId()).thenReturn(1);
        when(mockVaccine.getVaccineName()).thenReturn("BCG");

        when(vaccineRepository.findByVaccineId(1)).thenReturn(Optional.of(mockVaccine));

        VaccineProjection vaccine = vaccineService.getVaccineById(1);

        assertNotNull(vaccine);
        assertEquals(1, vaccine.getVaccineId());
        assertEquals("BCG", vaccine.getVaccineName());
    }

    @Test
    void testGetVaccineById_NotFound() {
        when(vaccineRepository.findByVaccineId(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> vaccineService.getVaccineById(1));
    }

    @Test
    void testGetAllVaccines() {
        VaccineProjection vaccine1 = mock(VaccineProjection.class);
        VaccineProjection vaccine2 = mock(VaccineProjection.class);

        when(vaccineRepository.findBy()).thenReturn(Arrays.asList(vaccine1, vaccine2));

        List<VaccineProjection> allVaccines = vaccineService.getAllVaccines();

        assertEquals(2, allVaccines.size());
        verify(vaccineRepository, times(1)).findBy();
    }

    @Test
    void testUpdateVaccine_Success() {
        Vaccine vaccine = new Vaccine(1, "BCG", "Intra-dermal", 1, 99.5);

        Vaccine updatedDetails = new Vaccine();
        updatedDetails.setVaccineName("OPV");
        updatedDetails.setRoute("Oral");
        updatedDetails.setMaxRequiredDoses(3);
        updatedDetails.setEffectivenessPercentage(95.0);

        when(vaccineRepository.findById(1)).thenReturn(Optional.of(vaccine));
        when(vaccineRepository.save(any(Vaccine.class))).thenReturn(vaccine);

        Vaccine updatedVaccine = vaccineService.updateVaccine(1, updatedDetails);

        assertNotNull(updatedVaccine);
        assertEquals("OPV", updatedVaccine.getVaccineName());
        assertEquals("Oral", updatedVaccine.getRoute());
        assertEquals(3, updatedVaccine.getMaxRequiredDoses());
        assertEquals(95.0, updatedVaccine.getEffectivenessPercentage());
    }

    @Test
    void testUpdateVaccine_NotFound() {
        when(vaccineRepository.findById(1)).thenReturn(Optional.empty());

        Vaccine vaccineDetails = new Vaccine();
        assertThrows(ResourceNotFoundException.class, () -> vaccineService.updateVaccine(1, vaccineDetails));
    }

    @Test
    void testDeleteVaccine_Success() {
        Vaccine vaccine = new Vaccine(1, "BCG", "Intra-dermal", 1, 99.5);

        when(vaccineRepository.findById(1)).thenReturn(Optional.of(vaccine));

        vaccineService.deleteVaccine(1);

        verify(vaccineRepository, times(1)).delete(vaccine);
    }

    @Test
    void testDeleteVaccine_NotFound() {
        when(vaccineRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> vaccineService.deleteVaccine(1));
    }
}
