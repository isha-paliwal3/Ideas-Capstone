package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import ideas.vaccineTracker.vaccine_tracker_data.exception.EmailAlreadyRegisteredException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.DoctorRepository;
import ideas.vaccineTracker.vaccine_tracker_data.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/doctors/register")
    public Doctor registerDoctor(@RequestBody Doctor doctor) {
        if (doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new EmailAlreadyRegisteredException("Email is already registered");
        }

        // Hash the password
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));

        // Assign the role based on the input (this could be enhanced based on your business logic)
        if (doctor.getRole() == null || doctor.getRole().isEmpty()) {
            doctor.setRole(Roles.ROLE_DOCTOR);
        }

        return doctorRepository.save(doctor);
    }

    @PostMapping("/authAdmin/register")
    public Doctor registerAdmin(@RequestBody Doctor admin) {
        if (doctorRepository.existsByEmail(admin.getEmail())) {
            throw new EmailAlreadyRegisteredException("Email is already registered");
        }

        // Hash the password
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        // Explicitly set the role as ADMIN
        admin.setRole(Roles.ROLE_ADMIN);

        return doctorRepository.save(admin);
    }
}
