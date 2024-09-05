package ideas.vaccineTracker.vaccine_tracker_data.service;

import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import ideas.vaccineTracker.vaccine_tracker_data.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Doctor not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                doctor.getEmail(),
                doctor.getPassword(),
                new ArrayList<>()
        );
    }
}
