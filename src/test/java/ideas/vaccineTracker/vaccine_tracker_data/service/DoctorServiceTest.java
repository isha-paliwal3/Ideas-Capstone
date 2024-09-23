package ideas.vaccineTracker.vaccine_tracker_data.service;

import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import ideas.vaccineTracker.vaccine_tracker_data.exception.ResourceNotFoundException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.DoctorRepository;
import ideas.vaccineTracker.vaccine_tracker_data.roles.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterDoctor_Success() {
        Doctor doctor = buildDoctor();
        when(doctorRepository.existsByEmail(doctor.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(doctor.getPassword())).thenReturn("encodedPassword");
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        Doctor savedDoctor = doctorService.registerDoctor(doctor);

        assertNotNull(savedDoctor);
        assertEquals(Roles.ROLE_DOCTOR, savedDoctor.getRole());
        assertEquals("encodedPassword", savedDoctor.getPassword());
        verify(doctorRepository, times(1)).existsByEmail(doctor.getEmail());
        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }

    @Test
    void testRegisterDoctor_EmailAlreadyRegistered() {
        Doctor doctor = buildDoctor();
        when(doctorRepository.existsByEmail(doctor.getEmail())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> doctorService.registerDoctor(doctor));
        verify(doctorRepository, times(1)).existsByEmail(doctor.getEmail());
        verify(doctorRepository, never()).save(any(Doctor.class));
    }

    @Test
    void testFindByEmailSuccess() {
        Doctor doctor = buildDoctor();
        when(doctorRepository.findByEmail(doctor.getEmail())).thenReturn(Optional.of(doctor));

        Optional<Doctor> foundDoctor = doctorService.findByEmail(doctor.getEmail());

        assertTrue(foundDoctor.isPresent());
        assertEquals(doctor.getEmail(), foundDoctor.get().getEmail());
        verify(doctorRepository, times(1)).findByEmail(doctor.getEmail());
    }

    @Test
    void testFindByEmailNotFound() {
        when(doctorRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> doctorService.findByEmail("nonexistent@example.com"));
        verify(doctorRepository, times(1)).findByEmail("nonexistent@example.com");
    }

    @Test
    void testUpdateDoctorSuccess() {
        Doctor existingDoctor = buildDoctor();
        Doctor updatedDoctorDetails = buildUpdatedDoctor();

        when(doctorRepository.findById(1)).thenReturn(Optional.of(existingDoctor));
        when(passwordEncoder.encode(updatedDoctorDetails.getPassword())).thenReturn("updatedEncodedPassword");
        when(doctorRepository.save(any(Doctor.class))).thenReturn(existingDoctor);

        Doctor updatedDoctor = doctorService.updateDoctor(1, updatedDoctorDetails);

        assertNotNull(updatedDoctor);
        assertEquals(updatedDoctorDetails.getDoctorName(), updatedDoctor.getDoctorName());
        assertEquals("updatedEncodedPassword", updatedDoctor.getPassword());
        verify(doctorRepository, times(1)).findById(1);
        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }

    @Test
    void testUpdateDoctorNotFound() {
        Doctor doctorDetails = buildUpdatedDoctor();

        when(doctorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> doctorService.updateDoctor(1, doctorDetails));
        verify(doctorRepository, times(1)).findById(1);
        verify(doctorRepository, never()).save(any(Doctor.class));
    }

    @Test
    void testDeleteDoctorSuccess() {
        Doctor doctor = buildDoctor();
        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));

        doctorService.deleteDoctor(1);

        verify(doctorRepository, times(1)).delete(doctor);
    }

    @Test
    void testDeleteDoctorNotFound() {
        when(doctorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> doctorService.deleteDoctor(1));
        verify(doctorRepository, times(1)).findById(1);
        verify(doctorRepository, never()).delete(any(Doctor.class));
    }

    private Doctor buildDoctor() {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(1);
        doctor.setEmail("doctor@example.com");
        doctor.setPassword("password123");
        doctor.setDoctorName("Dr. John Doe");
        doctor.setSpecialization("Pediatrics");
        doctor.setPhoneNumber("9876543210");
        doctor.setRole(Roles.ROLE_DOCTOR);
        return doctor;
    }

    private Doctor buildUpdatedDoctor() {
        Doctor doctor = new Doctor();
        doctor.setEmail("updateddoctor@example.com");
        doctor.setPassword("newpassword123");
        doctor.setDoctorName("Dr. Jane Doe");
        doctor.setSpecialization("Cardiology");
        doctor.setPhoneNumber("1234567890");
        return doctor;
    }
}
