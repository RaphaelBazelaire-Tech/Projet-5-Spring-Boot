package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.person.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST permettant de gérer les opérations CRUD
 * liées aux personnes dans l'application SafetyNet Alerts.
 *
 * <p>Ce contrôleur expose des endpoints HTTP permettant :</p>
 * <ul>
 *     <li>d'ajouter une personne</li>
 *     <li>de mettre à jour une personne existante</li>
 *     <li>de supprimer une personne.</li>
 * </ul>
 *
 * <p>Les requêtes sont déléguées au service {@link PersonService}
 * qui contient la logique métier associée.</p>
 *
 * Endpoint de base : <b>/person</b>
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Logger logger = LogManager.getLogger(PersonController.class);

    private final PersonService personService;

    /**
     * Constructeur du contrôleur de personnes.
     *
     * @param personService le service responsable de la gestion des opérations sur les personnes.
     */
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Ajout d'une nouvelle personne dans le système.
     *
     * <p>Endpoint : <b>POST /person</b></p>
     *
     * @param person objet {@link Person} contenant des informations de la personne à ajouter.
     * @return une {@link ResponseEntity} contenant un message indiquant que la personne a été ajoutée avec succès.
     */
    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        logger.info("POST /person - Ajout d'une personne {} {}", person.getFirstName(), person.getLastName());

        personService.addPerson(person);

        return ResponseEntity.ok("Personne ajouté avec succès.");
    }

    /**
     * Met à jour les informations d'une personne existante.
     *
     * <p>Endpoint : <b>PUT /person</b></p>
     *
     * @param person objet {@link Person} contenant les nouvelles informations de la personne.
     * @return une {@link ResponseEntity} contenant un message indiquant que la mise à jour a été effectuée.
     */
    @PutMapping
    public ResponseEntity<String> updatePerson(@RequestBody Person person) {
        logger.info("PUT /person - Mise à jour d'une personne {} {}", person.getFirstName(), person.getLastName());

        personService.updatePerson(person);

        return ResponseEntity.ok("Mise à jour d'une personne avec succès.");
    }

    /**
     * Supprime une personne du système.
     *
     * <p>Endpoint : <b>DELETE /person</b></p>
     *
     * @param firstName prénom de la personne à supprimer.
     * @param lastName nom de famille de la personne à supprimer.
     * @return une {@link ResponseEntity} contenant un message confirmant la suppression de la personne.
     */
    @DeleteMapping
    public ResponseEntity<String> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        logger.info("DELETE /person - Suppression d'une personne {} {}", firstName, lastName);

        personService.deletePerson(firstName, lastName);

        return ResponseEntity.ok("Personne supprimé avec succès.");
    }
}
