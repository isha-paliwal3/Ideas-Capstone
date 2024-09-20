package ideas.vaccineTracker.vaccine_tracker_data.entity;

import ideas.vaccineTracker.vaccine_tracker_data.roles.Roles;
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
    private String role = Roles.ROLE_ADMIN;
    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Roles.ROLE_ADMIN;
    }
}
