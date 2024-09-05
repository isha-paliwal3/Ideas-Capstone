package ideas.vaccineTracker.vaccine_tracker_data.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vaccination_logs")
public class VaccinationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer logId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(nullable = false)
    private Integer doseNumber;

    @Column(nullable = false)
    private String vaccinationDate;

    private String nextDueDate;

    private String vaccinationStatus;

}
