package giulianapetricore.progettosettimana14.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import giulianapetricore.progettosettimana14.entities.Comment;
import giulianapetricore.progettosettimana14.entities.Like;
import giulianapetricore.progettosettimana14.entities.Post;
import giulianapetricore.progettosettimana14.entities.User;
import giulianapetricore.progettosettimana14.exceptions.BadRequestException;
import giulianapetricore.progettosettimana14.exceptions.NotFoundException;
import giulianapetricore.progettosettimana14.services.CommentsService;
import giulianapetricore.progettosettimana14.services.LikesService;
import giulianapetricore.progettosettimana14.services.PostsService;
import giulianapetricore.progettosettimana14.services.UsersService;

@Component
public class SocialNetworkRunner implements CommandLineRunner {

    private final UsersService usersService;
    private final PostsService postsService;
    private final CommentsService commentsService;
    private final LikesService likesService;

    public SocialNetworkRunner(UsersService usersService, PostsService postsService, CommentsService commentsService, LikesService likesService) {
        this.usersService = usersService;
        this.postsService = postsService;
        this.commentsService = commentsService;
        this.likesService = likesService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- AVVIO TEST SOCIAL NETWORK ---");

        try {
            System.out.println(">>> FASE 1: CREAZIONE E SALVATAGGIO UTENTI <<<");
            User user1 = usersService.save("giuly_99", "Giuliana Petricore", "giuly@example.com");
            User user2 = usersService.save("mario_rossi", "Mario Rossi", "mario@example.com");
            System.out.println(">>> ESITO: Utenti salvati con successo. <<<");

            System.out.println(">>> FASE 2: CREAZIONE E SALVATAGGIO POST <<<");
            Post post1 = postsService.save("Questo e' il mio primo post su questo social!", user1.getUserId().toString());
            System.out.println(">>> ESITO: Post salvato con successo. <<<");

            System.out.println(">>> FASE 3: CREAZIONE E SALVATAGGIO COMMENTO <<<");
            Comment comment1 = commentsService.save("Benvenuta! Bellissimo post.", user2.getUserId().toString(), post1.getId().toString());
            System.out.println(">>> ESITO: Commento salvato con successo. <<<");

            System.out.println(">>> FASE 4: CREAZIONE E SALVATAGGIO PRIMO LIKE <<<");
            Like like1 = likesService.save(user2.getUserId().toString(), post1.getId().toString());
            System.out.println(">>> ESITO: Primo Like salvato con successo. <<<");

            System.out.println(">>> FASE 5: TEST DEL VINCOLO DI UNICITA SUI LIKE <<<");
            likesService.save(user2.getUserId().toString(), post1.getId().toString());

            System.out.println(">>> ERRORE: Il like duplicato e' stato inserito (non doveva succedere). <<<");

        } catch (BadRequestException e) {
            System.out.println(">>> TEST PASSATO: Il service ha bloccato il like duplicato dicendo: " + e.getMessage() + " <<<");
        } catch (Exception e) {
            System.out.println(">>> ERRORE GENERICO: " + e.getMessage() + " <<<");
        }
    }
}