package person.attornatus.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import person.attornatus.api.DTO.request.PersonRequest;
import person.attornatus.api.DTO.response.PersonResponse;
import person.attornatus.api.model.Person;
import person.attornatus.api.service.PersonService;

import javax.validation.Valid;


@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    private ModelMapper mapper;


    @Autowired
    public PersonController(PersonService personService, ModelMapper mapper){
        this.personService = personService;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponse createPerson(@RequestBody @Valid PersonRequest personRequest){
        Person createdPerson = personService.createPerson(mapper.map(personRequest, Person.class));
        return mapper.map(createdPerson, PersonResponse.class);
    }

    @GetMapping("/{uuid}/find-one")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponse findPerson(@PathVariable(value = "uuid") String uuid){
       return mapper.map(personService.findByExternalUUID(uuid), PersonResponse.class);
    }

//    @GetMapping("/find-all")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public List<Person> findAll(){
//        return personService.findAllPerson();
//    }






}
