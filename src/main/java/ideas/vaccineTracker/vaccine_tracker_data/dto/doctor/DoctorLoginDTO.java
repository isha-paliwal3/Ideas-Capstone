package ideas.vaccineTracker.vaccine_tracker_data.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorLoginDTO{

    private String email;
    private String password;
}
