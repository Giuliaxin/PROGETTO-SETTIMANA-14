package giulianapetricore.progettosettimana14.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "likes")
@ToString
@Getter
@Setter
public class Like {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private LocalDate date;

    public Like() {
    }

    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
        this.date = LocalDate.now();
    }
}