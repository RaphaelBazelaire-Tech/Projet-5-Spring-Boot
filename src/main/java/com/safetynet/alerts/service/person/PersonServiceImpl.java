package com.safetynet.alerts.service.person;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service {@link PersonService}.
 *
 * <p>Ce service gère les opérations liées aux personnes
 * dans l'application SafetyNet Alerts.</p>
 *
 * <p>Les fonctionnalités principales incluent :</p>
 * <ul>
 *     <li>l'ajout d'une nouvelle personne</li>
 *     <li>la mise à jour des informations d'une personne existante</li>
 *     <li>la suppression d'une personne</li>
 *     <li>la récupération de la liste des personnes</li>
 *     <li>la recherche d'une personne à partir de son prénom et de son nom</li>
 * </ul>
 *
 * <p>Les données sont récupérées et manipulées via le {@link DataRepository}
 * qui contient les informations chargées par l'application.</p>
 */
@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LogManager.getLogger(PersonServiceImpl.class);

    private final DataRepository dataRepository;

    /**
     * Constructeur du service des personnes.
     *
     * @param dataRepository dépôt de données contenant les informations des personnes.
     */
    public PersonServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    /**
     * Ajoute une nouvelle personne dans le système.
     *
     * @param person objet {@link Person} contenant les informations de la personne à ajouter.
     */
    @Override
    public void addPerson(Person person) {
        logger.info("Ajout d'une personne : {} {}", person.getFirstName(), person.getLastName());
        dataRepository.getPersons().add(person);
    }

    /**
     * Met à jour les informations d'une personne existante.
     *
     * <p>La recherche de la personne se base sur son prénom et son nom.</p>
     *
     * @param person objet {@link Person} contenant les nouvelles informations de la personne.
     */
    @Override
    public void updatePerson(Person person) {
        logger.info("Mise à jour d'une personne : {} {}", person.getFirstName(), person.getLastName());

        Optional<Person> existingPerson = dataRepository.getPersons()
                .stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(person.getFirstName())
                        && p.getLastName().equalsIgnoreCase(person.getLastName()))
                .findFirst();

        existingPerson.ifPresent(p -> {
            p.setAddress(person.getAddress());
            p.setCity(person.getCity());
            p.setZip(person.getZip());
            p.setPhone(person.getPhone());
            p.setEmail(person.getEmail());
        });
    }

    /**
     * Supprime une personne à partir de son prénom et de son nom.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     */
    @Override
    public void deletePerson(String firstName, String lastName) {
        logger.info("Suppression d'une personne : {} {}", firstName, lastName);

        dataRepository.getPersons().removeIf(
                p -> p.getFirstName().equalsIgnoreCase(firstName)
                        && p.getLastName().equalsIgnoreCase(lastName)
        );
    }

    /**
     * Récupère la liste de toutes les personnes enregistrées.
     *
     * @return une liste de {@link Person}.
     */
    @Override
    public List<Person> getPersons() {
        return dataRepository.getPersons();
    }

    /**
     * Recherche une personne à partir de son prénom et de son nom.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     * @return la {@link Person} correspondante ou {@code null} si aucune personne n'est trouvée.
     */
    @Override
    public Person getPersonByName(String firstName, String lastName) {
        return dataRepository.getPersons()
                .stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(firstName)
                && p.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }
}
