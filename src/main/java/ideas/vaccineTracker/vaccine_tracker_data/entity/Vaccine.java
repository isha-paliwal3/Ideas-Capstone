package ideas.vaccineTracker.vaccine_tracker_data.entity;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vaccines")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer vaccineId;

    @Column(nullable = false)
    private String vaccineName;

    @Column(nullable = false)
    private String route;

    @Column(nullable = false)
    private Integer maxRequiredDoses;

    @Column(nullable = false)
    private double effectivenessPercentage;

    @OneToMany(mappedBy = "vaccine")
    private List<VaccinationLog> vaccinationLogs;

    public Vaccine(Integer vaccineId, String vaccineName, String route, Integer maxRequiredDoses, double effectivenessPercentage) {
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
        this.route = route;
        this.maxRequiredDoses = maxRequiredDoses;
        this.effectivenessPercentage = effectivenessPercentage;
    }
}
