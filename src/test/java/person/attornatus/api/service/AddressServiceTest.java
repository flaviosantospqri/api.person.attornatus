package person.attornatus.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import person.attornatus.api.model.Address;
import person.attornatus.api.model.Person;

import java.time.LocalDate;
import java.util.List;

class AddressServiceTest {

    @Test
    void createAddressToPerson() {
        Person personTest = new Person();

        personTest.setName("Jhon Doyle");
        personTest.setBirthDate(LocalDate.parse("15/08/2020"));

        Address addressTest = new Address();
        addressTest.setPublicPlace("Rua dos Deves");
        addressTest.setZipCode("36610-000");
        addressTest.setNumber(242);
        addressTest.setCity("Java City");

        List<Address> addresses = personTest.getAddresses();

        addresses.add(addressTest);

        Assertions.assertEquals(1, personTest.getAddresses().size());
    }
}
