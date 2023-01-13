package person.attornatus.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.attornatus.api.model.Address;
import person.attornatus.api.model.Person;
import person.attornatus.api.repository.PersonRepository;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void createPerson(Person person) {
    }

    public Person findPerson(Long id) {
        return null;
    }

    public void findAllPerson() {
    }

    public void EditPerson(Long id) {
    }

    public void createAdrresForPerson(Person person, Address address) {
    }

    public void listAllAddressForPerson(Person person) {
    }


    public void setTheBestAddress(Person person, Long idAddress) {
    }



}
