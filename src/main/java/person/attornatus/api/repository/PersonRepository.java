package person.attornatus.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import person.attornatus.api.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
