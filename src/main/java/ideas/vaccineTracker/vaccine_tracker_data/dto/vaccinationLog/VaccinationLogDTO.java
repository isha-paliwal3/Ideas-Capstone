package ideas.vaccineTracker.vaccine_tracker_data.dto.vaccinationLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationLogDTO {
    private Integer patientId;
    private Integer vaccineId;
    private Integer doctorId;
    private Integer doseNumber;
    private String vaccinationDate;
    private String nextDueDate;
    private String vaccinationStatus;

}
