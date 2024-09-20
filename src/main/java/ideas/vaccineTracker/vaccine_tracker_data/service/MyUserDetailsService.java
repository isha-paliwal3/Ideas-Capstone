package ideas.vaccineTracker.vaccine_tracker_data.service;

import ideas.vaccineTracker.vaccine_tracker_data.entity.Admin;
import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import ideas.vaccineTracker.vaccine_tracker_data.repository.AdminRepository;
import ideas.vaccineTracker.vaccine_tracker_data.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (doctorRepository.existsByEmail(email)) {
            Doctor doctor = doctorRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Doctor not found with email: " + email));

            return org.springframework.security.core.userdetails.User.builder()
                    .username(doctor.getEmail())
                    .password(doctor.getPassword())
                    .roles(doctor.getRole())
                    .build();
        }

        if (adminRepository.existsByEmail(email)) {
            Admin admin = adminRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Admin not found with email: " + email));

            return org.springframework.security.core.userdetails.User.builder()
                    .username(admin.getEmail())
                    .password(admin.getPassword())
                    .roles(admin.getRole())
                    .build();
        }

        throw new UsernameNotFoundException("Wrong Credential");
    }
}
