package ideas.vaccineTracker.vaccine_tracker_data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String adminName;

    @Column(nullable = false)
    private String role = "ROLE_ADMIN";

    public Admin(String email, String password, String adminName) {
        this.email = email;
        this.password = password;
        this.adminName = adminName;
    }
}
