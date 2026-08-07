package giulianapetricore.progettosettimana14.services;

import org.springframework.stereotype.Service;
import giulianapetricore.progettosettimana14.entities.Post;
import giulianapetricore.progettosettimana14.entities.User;
import giulianapetricore.progettosettimana14.exceptions.NotFoundException;
import giulianapetricore.progettosettimana14.repositories.PostsRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final UsersService usersService;

    public PostsService(PostsRepository postsRepository, UsersService usersService) {
        this.postsRepository = postsRepository;
        this.usersService = usersService;
    }

    public List<Post> findAll() {
        return this.postsRepository.findAll();
    }

    public Post save(String text, String authorId) {
        User author = this.usersService.findById(authorId);
        Post newPost = new Post(text, author);
        return this.postsRepository.save(newPost);
    }

    public Post findById(String postId) {
        UUID uuid = UUID.fromString(postId);
        return this.postsRepository.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public void delete(String postId) {
        Post post = this.findById(postId);
        this.postsRepository.delete(post);
    }
}