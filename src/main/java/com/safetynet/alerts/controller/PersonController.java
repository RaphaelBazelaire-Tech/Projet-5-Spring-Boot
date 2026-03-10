package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.person.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Logger logger = LogManager.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        logger.info("POST /person - Ajout d'une personne {} {}", person.getFirstName(), person.getLastName());

        personService.addPerson(person);

        return ResponseEntity.ok("Personne ajouté avec succès.");
    }

    @PostMapping
    public ResponseEntity<String> updatePerson(@RequestBody Person person) {
        logger.info("PUT /person - Mise à jour d'une personne {} {}", person.getFirstName(), person.getLastName());

        personService.updatePerson(person);

        return ResponseEntity.ok("Mise à jour d'une personne avec succès.");
    }

    @PostMapping
    public ResponseEntity<String> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        logger.info("DELETE /person - Suppression d'une personne {} {}", firstName, lastName);

        personService.deletePerson(firstName, lastName);

        return ResponseEntity.ok("Personne supprimé avec succès.");
    }
}
