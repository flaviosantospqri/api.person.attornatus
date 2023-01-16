package person.attornatus.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import person.attornatus.api.dto.request.PersonRequest;
import person.attornatus.api.model.Address;
import person.attornatus.api.model.Person;
import person.attornatus.api.repository.PersonRepository;

import java.util.List;
import java.util.Objects;
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
        return personRepository.saveAndFlush(person);
    }

    public Person findByExternalUUID(String uuid) {
        return getPerson(uuid);
    }

    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    public Person updatePerson(PersonRequest personRequest, String uuid) {
        Person personFounded = getPerson(uuid);

        personFounded.setName(Objects.requireNonNullElseGet(personRequest.getName(), personFounded::getName));
        personFounded.setBirthDate(Objects.requireNonNullElseGet(personRequest.getBirthDate(), personFounded::getBirthDate));

        return personRepository.saveAndFlush(personFounded);
    }

    public Person createAddressForPerson(String personUUID, Address address) {
        Person personFounded = getPerson(personUUID);

        personFounded.getAddresses().add(address);

        return personRepository.saveAndFlush(personFounded);
    }

    public List<Address> listAllAddressForPerson(String personUUID) {
        return getPerson(personUUID).getAddresses();
    }


    public Person setTheBestAddress(String personUUID, String addressUUID) {
        Person personFounded = getPerson(personUUID);
        List<Address> addresses = personFounded.getAddresses();

        Address newMainAddress = addresses.stream()
                .filter(address -> address.getExternalUUID().equals(addressUUID))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Resource not found!"));

        addresses.forEach(address -> address.setMain(false));

        newMainAddress.setMain(true);

        return personRepository.saveAndFlush(personFounded);
    }

    private Person getPerson(String uuid) {
        return personRepository.findPersonByExternalUUID(uuid).orElseThrow(() -> new NotFoundException("Person not founded"));
    }
}
