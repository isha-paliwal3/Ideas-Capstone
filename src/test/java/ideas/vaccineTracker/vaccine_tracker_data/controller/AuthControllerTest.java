package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.dto.Authentication.AuthenticationResponseDTO;
import ideas.vaccineTracker.vaccine_tracker_data.dto.doctor.DoctorLoginDTO;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Admin;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import ideas.vaccineTracker.vaccine_tracker_data.repository.DoctorRepository;
import ideas.vaccineTracker.vaccine_tracker_data.roles.Roles;
import ideas.vaccineTracker.vaccine_tracker_data.service.AdminService;
import ideas.vaccineTracker.vaccine_tracker_data.service.MyUserDetailsService;
import ideas.vaccineTracker.vaccine_tracker_data.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private MyUserDetailsService userDetailsService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private AdminService adminService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdminLoginSuccess() throws Exception {
        DoctorLoginDTO loginRequest = new DoctorLoginDTO("admin@example.com", "password123");
        Admin admin = new Admin("admin@example.com", passwordEncoder.encode("password123"));

        when(adminService.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(admin));
        when(passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(admin.getEmail(), Roles.ROLE_ADMIN)).thenReturn("mocked-jwt-token");

        ResponseEntity<?> response = authController.createAuthenticationToken(loginRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        AuthenticationResponseDTO authResponse = (AuthenticationResponseDTO) response.getBody();
        assertEquals("mocked-jwt-token", authResponse.getJwt());
    }

    @Test
    void testAdminLoginInvalidCredentials() throws Exception {
        DoctorLoginDTO loginRequest = new DoctorLoginDTO("admin@example.com", "wrongpassword");
        Admin admin = new Admin("admin@example.com", passwordEncoder.encode("password123"));

        when(adminService.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(admin));
        when(passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())).thenReturn(false);

        ResponseEntity<?> response = authController.createAuthenticationToken(loginRequest);

        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid credentials for Admin", response.getBody());
    }

    @Test
    void testDoctorLoginSuccess() throws Exception {
        DoctorLoginDTO loginRequest = new DoctorLoginDTO("doctor@example.com", "password123");
        Doctor doctor = new Doctor();
        doctor.setEmail("doctor@example.com");
        doctor.setPassword(passwordEncoder.encode("password123"));

        when(adminService.findByEmail(loginRequest.getEmail())).thenReturn(Optional.empty());
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userDetailsService.loadUserByUsername(loginRequest.getEmail())).thenReturn(mock(UserDetails.class));
        when(jwtUtil.generateToken(any(UserDetails.class))).thenReturn("mocked-jwt-token");
        when(doctorRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(doctor));

        ResponseEntity<?> response = authController.createAuthenticationToken(loginRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        AuthenticationResponseDTO authResponse = (AuthenticationResponseDTO) response.getBody();
        assertEquals("mocked-jwt-token", authResponse.getJwt());
        assertEquals(doctor, authResponse.getDoctor());
    }

    @Test
    void testDoctorLoginInvalidCredentials() throws Exception {
        DoctorLoginDTO loginRequest = new DoctorLoginDTO("doctor@example.com", "wrongpassword");

        when(adminService.findByEmail(loginRequest.getEmail())).thenReturn(Optional.empty());
        doThrow(new RuntimeException("Invalid credentials")).when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        assertThrows(RuntimeException.class, () -> authController.createAuthenticationToken(loginRequest));
    }
}
