package com.safetynet.alerts.service.person;

import com.safetynet.alerts.model.Person;

import java.util.List;

/**
 * Interface définissant les opérations CRUD pour la gestion des personnes.
 *
 * <p>Fournit les méthodes pour ajouter, mettre à jour, supprimer
 * et récupérer des entités {@link Person}.</p>
 */
public interface PersonService {

    /**
     * Ajoute une nouvelle personne.
     *
     * @param person la personne à ajouter.
     */
    void addPerson(Person person);

    /**
     * Met à jour les informations d'une personne existante.
     *
     * @param person la personne avec les nouvelles informations.
     */
    void updatePerson(Person person);

    /**
     * Supprime une personne identifiée par son prénom et son nom de famille.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     */
    void deletePerson(String firstName, String lastName);

    /**
     * Récupère toutes les personnes présentes dans le repository.
     *
     * @return liste des {@link Person}.
     */
    List<Person> getPersons();

    /**
     * Récupère une personne par son prénom et son nom de famille.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     * @return {@link Person} correspondant, ou null si non trouvé.
     */
    Person getPersonByName(String firstName, String lastName);
}
