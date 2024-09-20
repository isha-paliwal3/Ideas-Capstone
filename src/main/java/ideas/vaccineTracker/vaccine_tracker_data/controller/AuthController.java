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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody DoctorLoginDTO loginRequest) throws AuthenticationException {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<Admin> admin = adminService.findByEmail(email);
        if (admin.isPresent()) {
            if (passwordEncoder.matches(password, admin.get().getPassword())) {
                final String jwt = jwtUtil.generateToken(admin.get().getEmail(), Roles.ROLE_ADMIN);
                return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
            } else {
                return ResponseEntity.status(401).body("Invalid credentials for Admin");
            }
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt, doctor.get()));
    }
}
