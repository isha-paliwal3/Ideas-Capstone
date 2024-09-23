package ideas.vaccineTracker.vaccine_tracker_data.service;

import ideas.vaccineTracker.vaccine_tracker_data.entity.Admin;
import ideas.vaccineTracker.vaccine_tracker_data.repository.AdminRepository;
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
public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterAdminSuccess() {
        String email = "admin@example.com";
        String password = "password123";
        String encodedPassword = "encodedPassword123";

        when(adminRepository.existsByEmail(email)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(adminRepository.save(any(Admin.class))).thenReturn(buildAdmin(email, encodedPassword));

        Admin registeredAdmin = adminService.registerAdmin(email, password);

        assertNotNull(registeredAdmin);
        assertEquals(email, registeredAdmin.getEmail());
        assertEquals(encodedPassword, registeredAdmin.getPassword());
        assertEquals(Roles.ROLE_ADMIN, registeredAdmin.getRole());

        verify(adminRepository, times(1)).existsByEmail(email);
        verify(adminRepository, times(1)).save(any(Admin.class));
    }

    @Test
    void testRegisterAdminEmailAlreadyRegistered() {
        String email = "admin@example.com";
        String password = "password123";

        when(adminRepository.existsByEmail(email)).thenReturn(true);

        assertThrows(RuntimeException.class, () -> adminService.registerAdmin(email, password));

        verify(adminRepository, times(1)).existsByEmail(email);
        verify(adminRepository, never()).save(any(Admin.class));
    }

    @Test
    void testFindByEmailSuccess() {
        String email = "admin@example.com";
        Admin admin = buildAdmin(email, "encodedPassword123");

        when(adminRepository.findByEmail(email)).thenReturn(Optional.of(admin));

        Optional<Admin> foundAdmin = adminService.findByEmail(email);

        assertTrue(foundAdmin.isPresent());
        assertEquals(admin.getEmail(), foundAdmin.get().getEmail());
        verify(adminRepository, times(1)).findByEmail(email);
    }

    @Test
    void testFindByEmailNotFound() {
        String email = "nonexistent@example.com";

        when(adminRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<Admin> foundAdmin = adminService.findByEmail(email);

        assertFalse(foundAdmin.isPresent());
        verify(adminRepository, times(1)).findByEmail(email);
    }

    private Admin buildAdmin(String email, String encodedPassword) {
        Admin admin = new Admin(email, encodedPassword);
        admin.setRole(Roles.ROLE_ADMIN);
        return admin;
    }
}
