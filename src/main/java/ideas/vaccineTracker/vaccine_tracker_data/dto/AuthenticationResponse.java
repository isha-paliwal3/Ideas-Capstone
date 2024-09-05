package ideas.vaccineTracker.vaccine_tracker_data.dto;

import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

}
