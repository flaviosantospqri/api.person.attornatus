package person.attornatus.api.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import person.attornatus.api.dto.request.AddressRequestDTO;
import person.attornatus.api.dto.request.PersonRequestDTO;
import person.attornatus.api.exceptions.PersonNotFoundException;
import person.attornatus.api.model.Address;
import person.attornatus.api.model.Person;
import person.attornatus.api.repository.PersonRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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

        assertEquals(personComplete.getName(), person.getName());

    }

    @Test
    public void findByExternalUUID_shouldReturnPerson_withValidUUID() throws PersonNotFoundException {
        Person personComplete = getMockPersonComplete();

        when(personRepository.findPersonByExternalUUID(personComplete.getExternalUUID())).thenReturn(Optional.of(personComplete));

        Person foundedPerson = personService().findByExternalUUID(personComplete.getExternalUUID());

        assertEquals(personComplete.getName(), foundedPerson.getName());
    }

    @Test
    public void findAllPerson_shouldReturnAllPerson(){
        Person personComplete = getMockPersonComplete();

        when(personRepository.findAll()).thenReturn(List.of(personComplete));

        List<Person> persons = personService().findAllPerson();

        assertNotNull(persons);
        assertFalse(persons.isEmpty());

    }

    @Test
    public void updatePerson_shouldNewValue() throws PersonNotFoundException {
        PersonRequestDTO personRequestDTO = getMockPersonDTO();
        Person personComplete = getMockPersonComplete();
        Person personUpdated = getPersonWithUpdateData();

        when(personRepository.findPersonByExternalUUID(personComplete.getExternalUUID())).thenReturn(Optional.of(personComplete));
        when(personRepository.saveAndFlush(personComplete)).thenReturn(personUpdated);

        Person serviceResponse = personService().updatePerson(personRequestDTO, personComplete.getExternalUUID());

        assertEquals(personUpdated.getName(), serviceResponse.getName());
    }

    @Test
    public void createAddressForPerson_shouldReturnPersonWithNewAddress_withValidParams() throws PersonNotFoundException {
        AddressRequestDTO addressRequestDTO = getMockAddressDTO();
        Person personComplete = getMockPersonComplete();
        Person personWithAddress = getPersonWithAddressData();

        when(personRepository.findPersonByExternalUUID(personComplete.getExternalUUID())).thenReturn(Optional.of(personComplete));
        when(personRepository.saveAndFlush(personComplete)).thenReturn(personWithAddress);

        Person serviceResponse = personService().createAddressForPerson(personComplete.getExternalUUID(), addressRequestDTO);

        assertNotNull(serviceResponse);
        assertEquals(personComplete.getAddresses().size(), serviceResponse.getAddresses().size());
    }

    @Test
    public void listAllAddressForPerson_shouldReturnAllAddress() throws PersonNotFoundException {
        Person personWithAddress = getPersonWithAddressData();

        when(personRepository.findPersonByExternalUUID(personWithAddress.getExternalUUID())).thenReturn(Optional.of(personWithAddress));
        when(personRepository.saveAndFlush(personWithAddress)).thenReturn(personWithAddress);

        List<Address> serviceResponse = personService().listAllAddressForPerson(personWithAddress.getExternalUUID());

        assertNotNull(serviceResponse);
        assertFalse(serviceResponse.isEmpty());

    }

    @Test
    public void setTheBestAddress_shouldReturnAddress_withMainParamTrue() throws PersonNotFoundException {
        Person personWithAddress = getPersonWithAddressData();

        when(personRepository.findPersonByExternalUUID(personWithAddress.getExternalUUID())).thenReturn(Optional.of(personWithAddress));
        when(personRepository.saveAndFlush(personWithAddress)).thenReturn(personWithAddress);

        Person serviceResponse = personService().setTheBestAddress(personWithAddress.getExternalUUID(), personWithAddress.getAddresses().get(0).getExternalUUID());

        assertNotNull(serviceResponse);
        assertTrue(serviceResponse.getAddresses().get(0).getMain());
    }

    private Person getPersonWithAddressData(){
        Person person = getMockPersonComplete();

        Address address = new Address();

        address.setExternalUUID(UUID.randomUUID().toString());
        address.setNumber(243);
        address.setZipCode("36610000");
        address.setCity("Java City");
        address.setMain(true);
        address.setPublicPlace("Rua dos Devs");

        person.setAddresses(List.of(address));

        return person;
    }

    private AddressRequestDTO getMockAddressDTO(){
        AddressRequestDTO address = new AddressRequestDTO();

        address.setNumber(243);
        address.setZipCode("36610000");
        address.setCity("Java City");
        address.setMain(true);
        address.setPublicPlace("Rua dos Devs");

        return address;
    }

    private PersonRequestDTO getMockPersonDTO() {
        PersonRequestDTO personDTO = new PersonRequestDTO();

        personDTO.setName("Billy");
        personDTO.setBirthDate(LocalDate.now().minusDays(10));

        return personDTO;
    }

    private Person getMockPersonRequest(){
        Person personTest = new Person();

        personTest.setName("Jonie Doyle");
        personTest.setBirthDate(LocalDate.now().minusDays(10));

        return personTest;

    }

    private Person getMockPersonComplete(){
        Person personTest = new Person();

        personTest.setName("Jonie Doyle");
        personTest.setBirthDate(LocalDate.now().minusDays(10));
        personTest.setExternalUUID(UUID.randomUUID().toString());

        return personTest;

    }

    private Person getPersonWithUpdateData(){
        Person personTest = new Person();

        personTest.setName("Jonie Doyle");
        personTest.setBirthDate(LocalDate.now().minusDays(10));
        personTest.setExternalUUID(UUID.randomUUID().toString());

        return personTest;

    }
}
