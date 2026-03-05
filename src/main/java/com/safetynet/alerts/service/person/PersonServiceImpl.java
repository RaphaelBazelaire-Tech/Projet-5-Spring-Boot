package com.safetynet.alerts.service.person;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LogManager.getLogger(PersonServiceImpl.class);

    private final DataRepository dataRepository;

    public PersonServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void addPerson(Person person) {
        logger.info("Ajout d'une personne : {} {}", person.getFirstName(), person.getLastName());
        dataRepository.getPersons().add(person);
    }

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

    @Override
    public void deletePerson(String firstName, String lastName) {
        logger.info("Suppression d'une personne : {} {}", firstName, lastName);

        dataRepository.getPersons().removeIf(
                p -> p.getFirstName().equalsIgnoreCase(firstName)
                        && p.getLastName().equalsIgnoreCase(lastName)
        );
    }

    @Override
    public List<Person> getPersons() {
        return dataRepository.getPersons();
    }

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
