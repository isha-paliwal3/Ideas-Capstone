package ideas.vaccineTracker.vaccine_tracker_data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "immunization_schedule")
public class ImmunizationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer immId;

    @Column(nullable = false)
    private Integer ageInDays;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    public ImmunizationSchedule(Integer immId, Integer ageInDays, Vaccine vaccine) {
        this.immId = immId;
        this.ageInDays = ageInDays;
        this.vaccine = vaccine;
    }
}
