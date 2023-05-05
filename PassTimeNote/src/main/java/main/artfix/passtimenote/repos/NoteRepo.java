package main.artfix.passtimenote.repos;

import main.artfix.passtimenote.models.Notes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends CrudRepository<Notes, Long> {
    Notes findByMail(String mail);
}
