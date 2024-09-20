package ideas.vaccineTracker.vaccine_tracker_data.dto.Authentication;

import ideas.vaccineTracker.vaccine_tracker_data.entity.Doctor;
import lombok.Getter;

@Getter
public class AuthenticationResponseDTO {
    private final String jwt;
    private Doctor doctor;

    public AuthenticationResponseDTO(String jwt, Doctor doctor) {
        this.jwt = jwt;
        this.doctor = doctor;
    }
    public AuthenticationResponseDTO(String jwt) {
        this.jwt = jwt;
    }

}