package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.entity.Admin;
import ideas.vaccineTracker.vaccine_tracker_data.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterAdminSuccess() {
        Admin admin = new Admin("admin@example.com", "password123");
        Admin savedAdmin = new Admin("admin@example.com", "encodedPassword123");

        when(adminService.registerAdmin(admin.getEmail(), admin.getPassword())).thenReturn(savedAdmin);

        ResponseEntity<Admin> response = adminController.registerAdmin(admin);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedAdmin, response.getBody());
    }

    @Test
    void testRegisterAdminEmailAlreadyRegistered() {
        Admin admin = new Admin("admin@example.com", "password123");

        when(adminService.registerAdmin(admin.getEmail(), admin.getPassword())).thenThrow(new RuntimeException("Email already registered"));

        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            adminController.registerAdmin(admin);
        });

        assertEquals("Email already registered", exception.getMessage());
    }
}
