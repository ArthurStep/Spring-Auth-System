package main.artfix.passtimenote.repos;

import main.artfix.passtimenote.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends CrudRepository<Users, Long> {
    Users findByMail(String mail);
}
