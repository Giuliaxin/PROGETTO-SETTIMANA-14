package giulianapetricore.progettosettimana14.services;

import org.springframework.stereotype.Service;
import giulianapetricore.progettosettimana14.entities.Comment;
import giulianapetricore.progettosettimana14.entities.Post;
import giulianapetricore.progettosettimana14.entities.User;
import giulianapetricore.progettosettimana14.exceptions.NotFoundException;
import giulianapetricore.progettosettimana14.repositories.CommentsRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final UsersService usersService;
    private final PostsService postsService;

    public CommentsService(CommentsRepository commentsRepository, UsersService usersService, PostsService postsService) {
        this.commentsRepository = commentsRepository;
        this.usersService = usersService;
        this.postsService = postsService;
    }

    public List<Comment> findAll() {
        return this.commentsRepository.findAll();
    }

    public Comment save(String text, String authorId, String postId) {
        User author = this.usersService.findById(authorId);
        Post post = this.postsService.findById(postId);

        Comment newComment = new Comment(text, author, post);
        return this.commentsRepository.save(newComment);
    }

    public Comment findById(String commentId) {
        UUID uuid = UUID.fromString(commentId);
        return this.commentsRepository.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }
}