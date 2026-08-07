package giulianapetricore.progettosettimana14.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import giulianapetricore.progettosettimana14.entities.User;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {
}