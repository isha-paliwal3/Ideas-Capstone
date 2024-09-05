package ideas.vaccineTracker.vaccine_tracker_data.controller;

import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import ideas.vaccineTracker.vaccine_tracker_data.exception.EmailAlreadyRegisteredException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Doctor registerDoctor(@RequestBody Doctor doctor) {
        if (doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new EmailAlreadyRegisteredException("Email is already registered");
        }

        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));

        return doctorRepository.save(doctor);
    }
}
