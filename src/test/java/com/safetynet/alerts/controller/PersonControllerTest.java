package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.person.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Test
    public void addPersonShouldReturnSuccessMessage() {

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        ResponseEntity<String> result = personController.addPerson(person);

        assertEquals(200, result.getStatusCode().value());
        assertEquals("Personne ajouté avec succès.", result.getBody());

        verify(personService, times(1)).addPerson(person);
    }

    @Test
    public void updatePersonShouldReturnSuccessMessage() {

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        ResponseEntity<String> result = personController.updatePerson(person);

        assertEquals(200, result.getStatusCode().value());
        assertEquals("Mise à jour d'une personne avec succès.", result.getBody());

        verify(personService, times(1)).updatePerson(person);
    }

    @Test
    public void deletePersonShouldReturnSuccessMessage() {

        String firstName = "John";
        String lastName = "Boyd";

        ResponseEntity<String> result = personController.deletePerson(firstName, lastName);

        assertEquals(200, result.getStatusCode().value());
        assertEquals("Personne supprimé avec succès.", result.getBody());

        verify(personService, times(1)).deletePerson(firstName, lastName);
    }
}
