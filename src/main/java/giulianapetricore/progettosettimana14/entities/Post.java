package giulianapetricore.progettosettimana14.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "posts")
@ToString
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    private String text;

    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    public Post() {
    }

    public Post(String text, User author) {
        this.text = text;
        this.author = author;
        this.publishDate = LocalDate.now();
    }
}
