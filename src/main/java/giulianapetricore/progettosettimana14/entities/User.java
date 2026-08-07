package giulianapetricore.progettosettimana14.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "users")
@ToString
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    public User() {
    }

    public User(String username, String fullName, String email) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }
}
