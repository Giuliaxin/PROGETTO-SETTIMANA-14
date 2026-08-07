package giulianapetricore.progettosettimana14.services;

import org.springframework.stereotype.Service;
import giulianapetricore.progettosettimana14.entities.User;
import giulianapetricore.progettosettimana14.exceptions.BadRequestException;
import giulianapetricore.progettosettimana14.exceptions.NotFoundException;
import giulianapetricore.progettosettimana14.repositories.UsersRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll() {
        return this.usersRepository.findAll();
    }

    public User save(String username, String fullName, String email) {
        boolean usernameInUse = this.usersRepository.findAll().stream()
                .anyMatch(user -> user.getUsername().equals(username));
        if (usernameInUse) throw new BadRequestException("L'username " + username + " è già in uso");

        boolean emailInUse = this.usersRepository.findAll().stream()
                .anyMatch(user -> user.getEmail().equals(email));
        if (emailInUse) throw new BadRequestException("L'email " + email + " è già in uso");

        User newUser = new User(username, fullName, email);
        return this.usersRepository.save(newUser);
    }

    public User findById(String userId) {
        UUID uuid = UUID.fromString(userId);
        return this.usersRepository.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public void delete(String userId) {
        User user = this.findById(userId);
        this.usersRepository.delete(user);
    }
}