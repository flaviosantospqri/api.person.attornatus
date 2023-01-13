package person.attornatus.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import person.attornatus.api.model.Person;
import person.attornatus.api.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPerson(@RequestBody Person person){
        personService.createPerson(person);
        return "Person created";
    }

    @GetMapping
    public String teste(){
        return "teste";
    }



}
