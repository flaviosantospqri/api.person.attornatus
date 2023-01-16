package person.attornatus.api.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import person.attornatus.api.model.Person;
import person.attornatus.api.repository.PersonRepository;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PersonServiceTest {

    @MockBean
    public PersonRepository personRepository;

    public PersonService personService() {
        return new PersonService(personRepository);
    }

    @Test
    public void createPerson_shouldCreate_withValidParams() {
        Person personRequest = getMockPersonRequest();
        Person personComplete = getMockPersonComplete();

        when(personRepository.saveAndFlush(personRequest)).thenReturn(personComplete);

        Person person = personService().createPerson(personRequest);

        Assertions.assertEquals(personComplete.getName(), person.getName());

    }

    private Person getMockPersonRequest(){
        Person personTest = new Person();

        personTest.setName("Jhon Doyle");
        personTest.setBirthDate(LocalDate.now().minusDays(10));

        return personTest;

    }
    private Person getMockPersonComplete(){
        Person personTest = new Person();

        personTest.setName("Jhon Doyle");
        personTest.setBirthDate(LocalDate.now().minusDays(10));
        personTest.setExternalUUID(UUID.randomUUID().toString());

        return personTest;

    }

}
