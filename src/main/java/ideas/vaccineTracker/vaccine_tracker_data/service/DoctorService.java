package ideas.vaccineTracker.vaccine_tracker_data.service;

import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import ideas.vaccineTracker.vaccine_tracker_data.exception.ResourceNotFoundException;
import ideas.vaccineTracker.vaccine_tracker_data.repository.DoctorRepository;
import ideas.vaccineTracker.vaccine_tracker_data.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Doctor registerDoctor(Doctor doctor) {
        if (doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        if (doctor.getRole() == null || doctor.getRole().isEmpty()) {
            doctor.setRole(Roles.ROLE_DOCTOR);
        }
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> findByEmail(String email) {
        return Optional.ofNullable(doctorRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with email: " + email)));
    }

    public Doctor updateDoctor(Integer id, Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));

        doctor.setEmail(doctorDetails.getEmail());
        doctor.setPassword(passwordEncoder.encode(doctorDetails.getPassword()));
        doctor.setDoctorName(doctorDetails.getDoctorName());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setPhoneNumber(doctorDetails.getPhoneNumber());

        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Integer id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctorRepository.delete(doctor);
    }
}
