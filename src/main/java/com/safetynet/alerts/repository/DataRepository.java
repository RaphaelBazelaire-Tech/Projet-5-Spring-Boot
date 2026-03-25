package com.safetynet.alerts.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository responsable de la gestion des données de l'application SafetyNet.
 *
 * <p>Cette classe charge les données depuis le fichier `data.json` à l'initialisation
 * de l'application, et fournit un accès en mémoire aux listes de {@link Person},
 * {@link Firestation} et {@link MedicalRecord}.</p>
 *
 * <p>Elle sert de couche de stockage centralisée pour les services,
 * évitant tout accès direct au fichier JSON après le chargement.</p>
 */
@Repository
public class DataRepository {

    private static final Logger logger = LogManager.getLogger(DataRepository.class);

    /**
     * Constructeur par défaut.
     */
    public DataRepository() {
    }

    /**
     * ObjectMapper pour l'écriture des données dans le data.json.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Liste des personnes chargées depuis le fichier JSON.
     */
    private List<Person> persons = new ArrayList<>();

    /**
     * Liste des stations de pompiers chargées depuis le fichier JSON.
     */
    private List<Firestation> firestations = new ArrayList<>();

    /**
     * Liste des dossiers médicaux chargées depuis le fichier JSON.
     */
    private List<MedicalRecord> medicalrecords = new ArrayList<>();

    /**
     * Chemin pour récupérer le data.json pour sauvegarder les données.
     */
    private final String filePath = "src/main/resources/data.json";

    /**
     * Méthode exécutée après la construction du bean pour
     * charger les données depuis le fichier `data.json`.
     *
     * <p>Les données sont converties en objets Java via Gson.
     * Si le fichier est introuvable ou invalide, une exception est levée
     * et l'application ne démarre pas correctement.</p>
     */
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
                this.medicalrecords = dataWrapper.getMedicalRecords() != null ? dataWrapper.getMedicalRecords() : new ArrayList<>();
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

    public void saveData() {
        try {

            Map<String, Object> data = new HashMap<>();
            data.put("persons", this.persons);
            data.put("firestations", this.firestations);
            data.put("medicalrecords", this.medicalrecords);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);

        } catch (Exception e) {
            logger.error("Impossible de sauvegarder le fichier data.json...", e);
            throw new RuntimeException("Impossible de sauvegarder le fichier data.json...", e);
        }
    }

    /**
     * Récupère la liste de tous les dossiers médicaux.
     *
     * @return liste de tous les dossiers médicaux
     */
    public List<MedicalRecord> getMedicalRecords() {
        return medicalrecords;
    }

    /**
     * Récupère la liste de toutes les stations de pompiers.
     *
     * @return liste de toutes les stations de pompiers.
     */
    public List<Firestation> getFirestations() {
        return firestations;
    }

    /**
     * Récupère la liste de toutes les personnes.
     *
     * @return liste de toutes les personnes.
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * Définit la liste des dossiers médicaux.
     *
     * @param medicalrecords nouvelle liste de {@link MedicalRecord}
     */
    public void setMedicalRecords(List<MedicalRecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }

    /**
     * Définit la liste des stations de pompiers.
     *
     * @param firestations nouvelle liste de {@link Firestation}
     */
    public void setFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }

    /**
     * Définit la liste des personnes.
     *
     * @param persons nouvelle liste de {@link Person}
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

}
