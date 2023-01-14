package person.attornatus.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import person.attornatus.api.model.Person;

import java.util.Optional;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findPersonByExternalUUID(String externalUUID);
}
