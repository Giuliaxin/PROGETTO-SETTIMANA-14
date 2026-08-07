package giulianapetricore.progettosettimana14.services;

import org.springframework.stereotype.Service;
import giulianapetricore.progettosettimana14.entities.Like;
import giulianapetricore.progettosettimana14.entities.Post;
import giulianapetricore.progettosettimana14.entities.User;
import giulianapetricore.progettosettimana14.exceptions.BadRequestException;
import giulianapetricore.progettosettimana14.exceptions.NotFoundException;
import giulianapetricore.progettosettimana14.repositories.LikesRepository;

import java.util.List;
import java.util.UUID;

@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final UsersService usersService;
    private final PostsService postsService;

    public LikesService(LikesRepository likesRepository, UsersService usersService, PostsService postsService) {
        this.likesRepository = likesRepository;
        this.usersService = usersService;
        this.postsService = postsService;
    }

    public List<Like> findAll() {
        return this.likesRepository.findAll();
    }

    public Like save(String userId, String postId) {
        User user = this.usersService.findById(userId);
        Post post = this.postsService.findById(postId);

        boolean alreadyLiked = this.likesRepository.findAll().stream()
                .anyMatch(like -> like.getUser().getUserId().equals(user.getUserId()) &&
                        like.getPost().getId().equals(post.getId()));

        if (alreadyLiked) {
            throw new BadRequestException("L'utente ha già messo un like a questo post!");
        }

        Like newLike = new Like(user, post);
        return this.likesRepository.save(newLike);
    }

    public void delete(String likeId) {
        UUID uuid = UUID.fromString(likeId);
        Like like = this.likesRepository.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
        this.likesRepository.delete(like);
    }
}