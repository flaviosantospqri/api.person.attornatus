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
    private final PersonRepository personRepository;

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
        personFounded.setName(personRequest.getName());
        personFounded.setBirthDate(personRequest.getBirthDate());
        personRepository.saveAndFlush(personFounded);
        return personFounded;
    }

    public Person createAddressForPerson(String personUUID, Address address) {
        Person personFounded = findByExternalUUID(personUUID);
        List<Address> addresses = personFounded.getAddresses();
        addresses.add(address);
        personRepository.saveAndFlush(personFounded);
        return personFounded;

    }

    public List<Address> listAllAddressForPerson(String personUUID) {
        Person personFounded = findByExternalUUID(personUUID);
        return personFounded.getAddresses();


    }


    public Person setTheBestAddress(String personUUID, String addressUUID) {
        Person personFounded = findByExternalUUID(personUUID);
        List<Address> addresses = personFounded.getAddresses();

        for (Address a : addresses) {
            a.setMain(a.getExternalUUID().equals(addressUUID));
        }
        personRepository.saveAndFlush(personFounded);
        return personFounded;


    }
}
