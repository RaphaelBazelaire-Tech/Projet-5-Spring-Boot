package com.safetynet.alerts.service.alert;

import com.safetynet.alerts.controller.dto.child.ChildAlertDTO;
import com.safetynet.alerts.controller.dto.child.ChildDTO;
import com.safetynet.alerts.controller.dto.fire.FireAddressDTO;
import com.safetynet.alerts.controller.dto.fire.FirePersonDTO;
import com.safetynet.alerts.controller.dto.firestation.FirestationResponseDTO;
import com.safetynet.alerts.controller.dto.flood.FloodDTO;
import com.safetynet.alerts.controller.dto.flood.FloodPersonDTO;
import com.safetynet.alerts.controller.dto.person.PersonInfoDTO;
import com.safetynet.alerts.controller.dto.person.PersonSummaryDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implémentation du service {@link AlertService}.
 *
 * <p>Ce service contient la logique métier permettant de répondre
 * aux différentes alertes demandées par l'application SafetyNet Alerts.
 * Il utilise le {@link DataRepository} pour accéder aux données
 * des personnes, des stations de pompiers et des dossiers médicaux.</p>
 *
 * <p>Les fonctionnalités principales incluent :</p>
 * <ul>
 *     <li>la récupération des personnes couvertes par une station de pompiers</li>
 *     <li>l'identification des enfants vivant à une adresse spécifique</li>
 *     <li>la récupération des numéros de téléphone d'une station</li>
 *     <li>les informations des habitants lors d'un incendie</li>
 *     <li>les informations des foyers couverts par plusieurs stations (flood)</li>
 *     <li>la récupération des informations détaillées d'une personne</li>
 *     <li>la récupération des emails d'une communauté</li>
 * </ul>
 *
 * <p>Ce service est utilisé par les contrôleurs REST afin de founir
 * les données nécessaires aux endpoints de l'API.</p>
 */
@Service
public class AlertServiceImpl implements AlertService {

    private static final Logger logger = LogManager.getLogger(AlertServiceImpl.class);

    private final DataRepository repository;

    /**
     * Constructure du service d'alertes.
     *
     * @param repository dépôt de données permettant d'accéder aux personnes, stations et dossiers médicaux.
     */
    public AlertServiceImpl(DataRepository repository) {
        this.repository = repository;
    }

    /**
     * Récupère la liste des personnes couvertes par une station
     * de pompiers ainsi que le nombre d'adultes et d'enfants.
     *
     * @param stationNumber numéro de la station de pompiers.
     * @return un {@link FirestationResponseDTO} contenant les personnes couvertes,
     * le nombre d'adultes et le nombre d'enfants.
     */
    @Override
    public FirestationResponseDTO getPersonCoveredByStation(int stationNumber) {

        logger.info("Recherche de personnes couvert par la station {}", stationNumber);

        List<String> addresses = repository.getFirestations()
                .stream()
                .filter(f -> f.getStation() == stationNumber)
                .map(Firestation::getAddress)
                .collect(Collectors.toList());

        logger.debug("Adresses couvertes par la station {}: {}", stationNumber, addresses);

        List<PersonSummaryDTO> persons = new ArrayList<>();

        int adults = 0;
        int children = 0;

        for (Person person : repository.getPersons()) {

            if (addresses.contains(person.getAddress())) {

                persons.add(new PersonSummaryDTO(
                        person.getFirstName(),
                        person.getLastName(),
                        person.getAddress(),
                        person.getPhone()
                ));

                MedicalRecord record = getMedicalRecord(person.getFirstName(), person.getLastName());

                int age = record != null ? calculateAge(record.getBirthdate()) : 0;

                if (age <= 18) {
                    children++;
                } else {
                    adults++;
                }
            }
        }

        logger.info("Station {} couvre {} personnes. ({} adultes et {} enfants)", stationNumber, persons.size(), adults, children);

        return new FirestationResponseDTO(persons, adults, children);
    }

    /**
     * Recherche les enfants vivant à une adresse donnée.
     *
     * <p>La réponse inclut également les autres membres du foyer
     * habitant à la même adresse.</p>
     *
     * @param address adresse à analyser.
     * @return un {@link ChildAlertDTO} contenant la liste des enfants
     * et les membres de leur foyer.
     */
    @Override
    public ChildAlertDTO getChildrenByAddress(String address) {

        logger.info("Recherche des enfants à l'adresse {}", address);

        List<Person> persons = repository.getPersons()
                .stream()
                .filter(p -> p.getAddress().equals(address))
                .collect(Collectors.toList());

        List<ChildDTO> children = new ArrayList<>();

        for (Person person : persons) {

            MedicalRecord record = getMedicalRecord(person.getFirstName(), person.getLastName());
            int age = calculateAge(record.getBirthdate());

            if (age <= 18) {

                logger.debug("Enfant trouvé : {} {}", person.getFirstName(), person.getLastName());

                List<String> household = persons.stream()
                        .filter(other -> !other.getFirstName().equals(person.getFirstName()))
                        .map(other -> other.getFirstName() + " " + other.getLastName())
                        .collect(Collectors.toList());

                children.add(new ChildDTO(
                        person.getFirstName(),
                        person.getLastName(),
                        age,
                        household
                ));
            }
        }

        logger.info("{} enfants trouvés à l'adresse {}", children.size(), address);

        return new ChildAlertDTO(children);
    }

    /**
     * Récupère les numéros de téléphone des personnes
     * couvertes par une station de pompiers.
     *
     * @param stationNumber numéro de la station.
     * @return une liste de numéros de téléphone uniques.
     */
    @Override
    public List<String> getPhoneNumbersByStation(int stationNumber) {

        logger.info("Recherche des numéros de téléphones de la station {}", stationNumber);

        List<String> phones = repository.getPersons()
                .stream()
                .filter(p -> repository.getFirestations().stream()
                        .anyMatch(f -> f.getStation() == stationNumber && f.getAddress().equals(p.getAddress())))
                .map(Person::getPhone)
                .distinct()
                .toList();

        logger.info("{} numéros de téléphones trouvés pour la station {}", phones.size(), stationNumber);

        return phones;
    }

    /**
     * Récupère les informations des habitants d'une adresse
     * ainsi que de la station de pompiers correspondante.
     *
     * @param address adresse concernée.
     * @return un {@link FireAddressDTO} contenant le numéro de la station
     * et la liste des habitants avec leurs informations médicales.
     */
    @Override
    public FireAddressDTO getFireByAddress(String address) {

        logger.info("Recherche d'information pour l'adresse {}", address);

        int station = repository.getFirestations()
                .stream()
                .filter(f -> f.getAddress().equals(address))
                .map(Firestation::getStation)
                .findFirst()
                .orElse(0);

        logger.debug("La station {} couvre l'adresse {}", station, address);

        List<FirePersonDTO> residents = repository.getPersons()
                .stream()
                .filter(p -> p.getAddress().equals(address))
                .map(p -> {

                    MedicalRecord record = getMedicalRecord(p.getFirstName(), p.getLastName());
                    int age = calculateAge(record.getBirthdate());

                    logger.debug("Résident trouvé : {} {} âgé de {}", p.getFirstName(), p.getLastName(), age);

                    return new FirePersonDTO(
                            p.getFirstName() + " " + p.getLastName(),
                            p.getPhone(),
                            age,
                            record.getMedications(),
                            record.getAllergies());
                })
                .collect(Collectors.toList());

        logger.info("{} résidents trouvés à l'adresse {}", residents.size(), address);

        return new FireAddressDTO(station, residents);
    }

    /**
     * Récupère les informations des foyers couverts
     * par plusieurs stations de pompiers.
     *
     * <p>Les résultats sont regroupés par station.</p>
     *
     * @param stations la liste des numéros de stations.
     * @return une {@link Map} associant chaque station à la liste des foyers couverts.
     */
    @Override
    public Map<Integer, List<FloodDTO>> getFloodByStations(List<Integer> stations) {

        logger.info("Recherche de flood pour les stations {}", stations);

        Map<Integer, List<FloodDTO>> result = new HashMap<>();

        for (int station : stations) {

            logger.debug("Information sur la station {}", station);

            List<String> addresses = repository.getFirestations()
                    .stream()
                    .filter(f -> f.getStation() == station)
                    .map(Firestation::getAddress)
                    .collect(Collectors.toList());

            logger.debug("Adresses pour les stations {}: {}", station, addresses);

            List<FloodDTO> floods = new ArrayList<>();

            for (String address : addresses) {

                List<FloodPersonDTO> residents = repository.getPersons()
                        .stream()
                        .filter(p -> p.getAddress().equals(address))
                        .map(p -> {

                            MedicalRecord record = getMedicalRecord(p.getFirstName(), p.getLastName());
                            int age = calculateAge(record.getBirthdate());

                            return new FloodPersonDTO(
                                    p.getFirstName() + " " + p.getLastName(),
                                    p.getPhone(),
                                    age,
                                    record.getMedications(),
                                    record.getAllergies());
                        })
                        .collect(Collectors.toList());

                floods.add(new FloodDTO(address, residents));
            }

            logger.info("Station {} possède {} résidents", station, floods.size());

            result.put(station, floods);
        }

        logger.info("Demande pour les {} stations complétés.", stations.size());
        return result;
    }

    /**
     * Récupère les informations détaillées d'une personne
     * à partir de son prénom et de son nom.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     * @return une liste de {@link PersonInfoDTO} contenant les informations personnelles et médicales.
     */
    @Override
    public List<PersonInfoDTO> getPersonInfo(String firstName, String lastName) {

        logger.info("Recherche d'informations pour {} {}", firstName, lastName);

        List<PersonInfoDTO> result = repository.getPersons()
                .stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName))
                .map(p -> {

                    MedicalRecord record = getMedicalRecord(p.getFirstName(), p.getLastName());
                    int age = calculateAge(record.getBirthdate());

                    logger.debug("Personne trouvé : {} {} âgé de {}", p.getFirstName(), p.getLastName(), age);

                    return new PersonInfoDTO(
                            p.getFirstName(),
                            p.getLastName(),
                            p.getAddress(),
                            age,
                            p.getEmail(),
                            record.getMedications(),
                            record.getAllergies());
                })
                .collect(Collectors.toList());

        logger.info("{} résultat(s) trouvés pour la personne {} {}", result.size(), firstName, lastName);

        return result;
    }

    /**
     * Récupère les informations détaillées de toutes les
     * personnes correspondant à un nom de famille donné.
     *
     * @param lastName nom de famille recherché.
     * @return une liste de {@link PersonInfoDTO}
     */
    @Override
    public List<PersonInfoDTO> getPersonInfoByLastName(String lastName) {

        logger.info("Recherche d'informations pour {}", lastName);

        List<PersonInfoDTO> result = repository.getPersons()
                .stream()
                .filter(p -> p.getLastName().equalsIgnoreCase(lastName))
                .map(p -> {

                    MedicalRecord record = getMedicalRecord(p.getFirstName(), p.getLastName());
                    int age = calculateAge(record.getBirthdate());

                    logger.debug("Nom de famille trouvé : {} âgé de {}", p.getLastName(), age);

                    return new PersonInfoDTO(
                            p.getFirstName(),
                            p.getLastName(),
                            p.getAddress(),
                            age,
                            p.getEmail(),
                            record.getMedications(),
                            record.getAllergies());
                })
                .collect(Collectors.toList());

        logger.info("{} résultat(s) trouvés pour le nom de famille {}", result.size(), lastName);

        return result;
    }

    /**
     * Récupère tous les emails des habitants d'une ville.
     *
     * @param city nom de la ville souhaitée.
     * @return une liste d'emails uniques.
     */
    @Override
    public List<String> getCommunityEmails(String city) {

        logger.info("Recherche d'emails dans la ville {}", city);

        List<String> emails = repository.getPersons()
                .stream()
                .filter(p -> p.getCity().equalsIgnoreCase(city))
                .map(Person::getEmail)
                .distinct()
                .toList();

        logger.info("{} emails trouvés pour la ville {}", emails.size(), city);

        return emails;
    }

    /**
     * Calcule l'âge d'une personne à partir de sa date de naissance.
     *
     * @param birthdate date de naissance au format MM/dd/yyyy.
     * @return âge de la personne en années.
     */
    private int calculateAge(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate = LocalDate.parse(birthdate, formatter);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    /**
     * Recherche le dossier médical correspondant à une personne.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     * @return le {@link MedicalRecord} correspondant ou {@code null} si aucun dossier trouvé.
     */
    private MedicalRecord getMedicalRecord(String firstName, String lastName) {
        return repository.getMedicalRecords()
                .stream()
                .filter(m -> m.getFirstName().equals(firstName)
                        && m.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }
}
