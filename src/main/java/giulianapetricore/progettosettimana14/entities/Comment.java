package giulianapetricore.progettosettimana14.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "comments")
@ToString
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment() {
    }

    public Comment(String text, User author, Post post) {
        this.text = text;
        this.author = author;
        this.post = post;
        this.date = LocalDate.now();
    }
}