package com.safetynet.alerts.service.person;

import com.safetynet.alerts.model.Person;

import java.util.List;

public interface PersonService {

    void addPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(String firstName, String lastName);

    List<Person> getPersons();

    Person getPersonByName(String firstName, String lastName);
}
