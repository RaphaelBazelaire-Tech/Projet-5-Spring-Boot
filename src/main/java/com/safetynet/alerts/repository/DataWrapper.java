package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

import java.util.List;

/**
 * Wrapper utilisé pour la désérialisation du fichier `data.json`.
 *
 * <p>Cette classe sert de conteneur temporaire pour regrouper les listes
 * de {@link Person}, {@link Firestation} et {@link MedicalRecord} lors
 * du chargement des données JSON via Gson.</p>
 *
 * <p>Elle n’est pas persistée et n’a pas de logique métier, elle est
 * uniquement utilisée pour la conversion JSON → objets Java.</p>
 */
public class DataWrapper {

    /**
     * Constructeur par défaut.
     */
    public DataWrapper() {
    }

    /**
     * Liste des personnes contenues dans le JSON.
     */
    private List<Person> persons;

    /**
     * Liste des stations de pompiers contenues dans le JSON.
     */
    private List<Firestation> firestations;

    /**
     * Liste des dossiers médicaux contenues dans le JSON.
     */
    private List<MedicalRecord> medicalrecords;

    /**
     * Récupère la liste des personnes contenues dans le JSON.
     *
     * @return liste des {@link Person} contenues dans le JSON.
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * Récupère la liste des stations de pompiers contenues dans le JSON.
     *
     * @return liste des {@link Firestation} contenues dans le JSON.
     */
    public List<Firestation> getFirestations() {
        return firestations;
    }

    /**
     * Récupère la liste des dossiers médicaux contenues dans le JSON.
     *
     * @return liste des {@link MedicalRecord} contenues dans le JSON.
     */
    public List<MedicalRecord> getMedicalRecords() {
        return medicalrecords;
    }
}
