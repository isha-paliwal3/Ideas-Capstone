package ideas.vaccineTracker.vaccine_tracker_data.service;

import ideas.vaccineTracker.vaccine_tracker_data.entity.Admin;
import ideas.vaccineTracker.vaccine_tracker_data.repository.AdminRepository;
import ideas.vaccineTracker.vaccine_tracker_data.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin registerAdmin(String email, String password) {
        if (adminRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }
        Admin admin = new Admin(email, passwordEncoder.encode(password));
        admin.setRole(Roles.ROLE_ADMIN);
        return adminRepository.save(admin);
    }

    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
