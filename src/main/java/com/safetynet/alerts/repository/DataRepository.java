package com.safetynet.alerts.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataRepository {

    private static final Logger logger = LogManager.getLogger(DataRepository.class);

    private List<Person> persons = new ArrayList<>();
    private List<Firestation> firestations = new ArrayList<>();
    private List<MedicalRecord> medicalrecords = new ArrayList<>();

    @PostConstruct
    public void loadData() {
        logger.info("Chargement de data.json...");

        try {
            ClassPathResource resource = new ClassPathResource("data.json");
            Reader reader = new InputStreamReader(resource.getInputStream());

            Gson gson = new GsonBuilder().create();
            DataWrapper dataWrapper = gson.fromJson(reader, DataWrapper.class);

            if (dataWrapper != null) {
                this.persons = dataWrapper.getPersons() != null ? dataWrapper.getPersons() : new ArrayList<>();
                this.firestations = dataWrapper.getFirestations() != null ? dataWrapper.getFirestations() : new ArrayList<>();
                this.medicalrecords = dataWrapper.getMedicalrecords() != null ? dataWrapper.getMedicalrecords() : new ArrayList<>();
            }

            logger.info("Données chargés avec succès.");
            logger.info("Nombre de persons : {}", this.persons.size());
            logger.info("Nombre de firestations : {}", this.firestations.size());
            logger.info("Nombre de medical records : {}", this.medicalrecords.size());

        } catch (Exception e) {
            logger.error("Impossible de charger le fichier data.json...", e);
            throw new RuntimeException("Impossible de charger le fichier data.json...", e);
        }
    }

    public List<MedicalRecord> getMedicalrecords() {
        return medicalrecords;
    }

    public List<Firestation> getFirestations() {
        return firestations;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }

}
