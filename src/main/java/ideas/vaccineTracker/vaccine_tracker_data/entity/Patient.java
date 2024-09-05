package ideas.vaccineTracker.vaccine_tracker_data.entity;

import jakarta.persistence.*;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer patientId;

    @Column(nullable = false)
    private String patientName;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private String gender;

    private String address;
    private String phoneNumber;

    @Getter
    @Setter
    @OneToMany(mappedBy = "patient")
    private List<VaccinationLog> vaccinationLogs;

    public Patient(Integer patientId, String patientName, String dateOfBirth, String gender, String address, String phoneNumber) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}
