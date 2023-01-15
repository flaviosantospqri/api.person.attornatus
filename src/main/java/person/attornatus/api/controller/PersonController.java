package person.attornatus.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import person.attornatus.api.dto.request.PersonRequest;
import person.attornatus.api.dto.response.PersonResponse;
import person.attornatus.api.model.Address;
import person.attornatus.api.model.Person;
import person.attornatus.api.service.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    private final ModelMapper mapper;


    @Autowired
    public PersonController(PersonService personService, ModelMapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponse createPerson(@RequestBody @Valid PersonRequest personRequest) {
        Person createdPerson = personService.createPerson(mapper.map(personRequest, Person.class));
        return mapper.map(createdPerson, PersonResponse.class);
    }

    @GetMapping("/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAll(){
        return personService.findAllPerson();
    }

    @PatchMapping("/{uuid}/update")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponse updatePerson(@PathVariable(value = "uuid") String uuid, @RequestBody @Valid PersonRequest personRequest) {
        return mapper.map(personService.updatePerson(personRequest, uuid), PersonResponse.class);
    }


    @GetMapping("/{uuid}/find-one")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponse findPerson(@PathVariable(value = "uuid") String uuid) {
        return mapper.map(personService.findByExternalUUID(uuid), PersonResponse.class);
    }

    @PostMapping("/{uuid}/add-address")
    @ResponseStatus(HttpStatus.OK)
    public Person setAddressInPerson(@PathVariable(value = "uuid") String uuid, @RequestBody Address address) {
        address.setExternalUUID(UUID.randomUUID().toString());
        return personService.createAddressForPerson(uuid, address);

    }

    @GetMapping("/{uuid}/find-all-address")
    @ResponseStatus(HttpStatus.OK)
    public List<Address> findAllAddressPerson(@PathVariable(value = "uuid") String uuid) {
        return personService.listAllAddressForPerson(uuid);
    }

    @PutMapping("/{uuid-person}/{uuid-address}/main-address")
    @ResponseStatus(HttpStatus.OK)
    public Person setTheBestAddress(@PathVariable(value = "uuid-person") String uuidPerson, @PathVariable(value = "uuid-address") String uuidAddress) {
        return personService.setTheBestAddress(uuidPerson, uuidAddress);
    }


}
