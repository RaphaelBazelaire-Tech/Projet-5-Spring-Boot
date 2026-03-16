package com.safetynet.alerts.units.service.person;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.DataRepository;
import com.safetynet.alerts.service.person.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private PersonServiceImpl personServiceImpl;

    @Test
    public void addPersonShouldAddPerson() {

        List<Person> persons = new ArrayList<>();
        when(dataRepository.getPersons()).thenReturn(persons);

        Person person = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "john@email.com"
        );

        personServiceImpl.addPerson(person);

        assertEquals(1, persons.size());
        assertEquals("John", persons.get(0).getFirstName());
    }

    @Test
    public void updatePersonShouldUpdatePersonInformation() {

        Person existingPerson = new Person(
                "John",
                "Boyd",
                "Old Address",
                "Old City",
                "00000",
                "0000000000",
                "old@email.com"
        );

        List<Person> persons = new ArrayList<>();
        persons.add(existingPerson);

        when(dataRepository.getPersons()).thenReturn(persons);

        Person updatedPerson = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "new@email.com"
        );

        personServiceImpl.updatePerson(updatedPerson);

        assertEquals("1509 Culver St", existingPerson.getAddress());
        assertEquals("Culver", existingPerson.getCity());
        assertEquals("97451", existingPerson.getZip());
        assertEquals("841-874-6512", existingPerson.getPhone());
        assertEquals("new@email.com", existingPerson.getEmail());
    }

    @Test
    public void deletePersonShouldRemovePerson() {

        Person person = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "john@email.com"
        );

        List<Person> persons = new ArrayList<>();
        persons.add(person);

        when(dataRepository.getPersons()).thenReturn(persons);

        personServiceImpl.deletePerson("John", "Boyd");

        assertTrue(persons.isEmpty());
    }

    @Test
    public void getPersonsShouldReturnList() {

        List<Person> persons = List.of(
                new Person("John", "Boyd", "Address1", "City1", "00001", "1111111111", "john@email.com"),
                new Person("Jacob", "Boyd", "Address2", "City2", "00002", "2222222222", "jacob@email.com")
        );

        when(dataRepository.getPersons()).thenReturn(persons);

        List<Person> result = personServiceImpl.getPersons();

        assertEquals(2, result.size());
    }

    @Test
    public void getPersonByNameShouldReturnPerson() {

        Person person = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "john@email.com"
        );

        when(dataRepository.getPersons()).thenReturn(List.of(person));

        Person result = personServiceImpl.getPersonByName("John", "Boyd");

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Boyd", result.getLastName());
    }

    @Test
    public void getPersonByNameShouldReturnNullIfNotFound() {

        when(dataRepository.getPersons()).thenReturn(new ArrayList<>());

        Person result = personServiceImpl.getPersonByName("Unknown", "Person");

        assertNull(result);
    }
}
