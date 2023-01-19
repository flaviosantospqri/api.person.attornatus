package person.attornatus.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import person.attornatus.api.dto.request.AddressRequestDTO;
import person.attornatus.api.dto.request.PersonRequestDTO;
import person.attornatus.api.exceptions.PersonNotFoundException;
import person.attornatus.api.model.Address;
import person.attornatus.api.model.Person;
import person.attornatus.api.repository.PersonRepository;

import java.util.*;

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

    public Person findByExternalUUID(String uuid) throws PersonNotFoundException {
        return getPerson(uuid);
    }

    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    public Person updatePerson(PersonRequestDTO personRequest, String uuid) throws PersonNotFoundException {
        Person personFounded = getPerson(uuid);

        personFounded.setName(Objects.requireNonNullElseGet(personRequest.getName(), personFounded::getName));
        personFounded.setBirthDate(Objects.requireNonNullElseGet(personRequest.getBirthDate(), personFounded::getBirthDate));

        return personRepository.saveAndFlush(personFounded);
    }

    public Person createAddressForPerson(String personUUID, AddressRequestDTO addressRequestDTO) throws PersonNotFoundException {
        Person personFounded = getPerson(personUUID);
        Address address = new Address();

        address.setCity(addressRequestDTO.getCity());
        address.setNumber(addressRequestDTO.getNumber());
        address.setZipCode(addressRequestDTO.getZipCode());
        address.setPublicPlace(addressRequestDTO.getPublicPlace());
        address.setExternalUUID(UUID.randomUUID().toString());
        address.setMain(false);

        List<Address> oldListAddress = new ArrayList<>(personFounded.getAddresses());

        oldListAddress.add(address);

        personFounded.setAddresses(oldListAddress);

        return personRepository.save(personFounded);
    }

    public List<Address> listAllAddressForPerson(String personUUID) throws PersonNotFoundException {
       return getPerson(personUUID).getAddresses();
    }


    public Person setTheBestAddress(String personUUID, String addressUUID) throws PersonNotFoundException {
        Person personFounded = getPerson(personUUID);
        List<Address> addresses = personFounded.getAddresses();

        Address newMainAddress = addresses.stream()
                .filter(address -> address.getExternalUUID().equals(addressUUID))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Address not found, verify this uuid" + addressUUID));

        addresses.forEach(address -> address.setMain(false));

        newMainAddress.setMain(true);
        return personRepository.saveAndFlush(personFounded);

    }

    private Person getPerson(String uuid) throws PersonNotFoundException {
        if(personRepository.findPersonByExternalUUID(uuid).isEmpty()){
            throw new PersonNotFoundException(uuid);
        }else{
           Optional<Person> person = personRepository.findPersonByExternalUUID(uuid);
           return person.get();
        }
    }
}
