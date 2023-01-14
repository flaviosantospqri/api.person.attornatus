package person.attornatus.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import person.attornatus.api.dto.request.PersonRequest;
import person.attornatus.api.model.Address;
import person.attornatus.api.model.Person;
import person.attornatus.api.repository.PersonRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person) {
        person.setExternalUUID(UUID.randomUUID().toString());
        return personRepository.save(person);
    }

    public Person findByExternalUUID(String uuid) {
        return personRepository.findPersonByExternalUUID(uuid).orElseThrow(() -> new NotFoundException("Person not founded"));
    }

    public List<Person> findAllPerson() {
        return personRepository.findAll();


    }

    public Person updatePerson(PersonRequest personRequest, String uuid) {
        Person personFounded = personRepository.findPersonByExternalUUID(uuid).orElseThrow(() -> new NotFoundException("Person not founded"));

            if(!personRequest.getName().isEmpty()) {
                personFounded.setName(personRequest.getName());
                personFounded.setName(personRequest.getName());
            }

            personRepository.saveAndFlush(personFounded);

            return personFounded;



    }

    public void createAdrresForPerson(Person person, Address address) {
    }

    public void listAllAddressForPerson(Person person) {
    }


    public void setTheBestAddress(Person person, Long idAddress) {
    }



}
