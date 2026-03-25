package com.safetynet.alerts.service.firestation;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service {@link FirestationService}.
 *
 * <p>Ce service gère les opérations liées aux stations de pompiers
 * dans l'application SafetyNet Alerts.</p>
 *
 * <p>Il permet notamment :</p>
 * <ul>
 *     <li>d'ajouter une nouvelle association entre une adresse et une station</li>
 *     <li>de mettre à jour une station existante</li>
 *     <li>de supprimer une association adresse / station</li>
 *     <li>de récupérer la liste des stations de pompiers</li>
 *     <li>de rechercher une station à partir d'une adresse</li>
 * </ul>
 *
 * <p>Les données sont récupérées depuis le {@link DataRepository}
 * qui contient les informations chargées par l'application.</p>
 */
@Service
public class FirestationServiceImpl implements FirestationService {

    private static final Logger logger = LogManager.getLogger(FirestationServiceImpl.class);

    private final DataRepository dataRepository;

    /**
     * Constructeur du service des stations de pompiers.
     *
     * @param dataRepository dépôt de données contenant les informations des stations.
     */
    public FirestationServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    /**
     * Ajoute une nouvelle association entre
     * une adresse et une station de pompiers.
     *
     * @param firestation objet {@link Firestation} contenant l'adresse et le numéro de station.
     */
    @Override
    public void addFirestation(Firestation firestation) {
        logger.info("Ajout d'une station de pompiers : {}", firestation.getAddress());
        dataRepository.getFirestations().add(firestation);

        dataRepository.saveData();
    }

    /**
     * Met à jour la station de pompiers associée à une adresse existante.
     *
     * @param firestation objet {@link Firestation} contenant les nouvelles informations de station.
     */
    @Override
    public void updateFirestation(Firestation firestation) {
        logger.info("Mise à jour d'une station de pompiers : {}", firestation.getAddress());

        Optional<Firestation> existing = dataRepository.getFirestations()
                .stream()
                .filter(f -> f.getAddress().equalsIgnoreCase(firestation.getAddress()))
                .findFirst();

        existing.ifPresent(f -> f.setStation(firestation.getStation()));

        dataRepository.saveData();
    }

    /**
     * Supprime l'association entre une adresse et une station de pompiers.
     *
     * @param address adresse à supprimer.
     */
    @Override
    public void deleteFirestation(String address) {
        logger.info("Suppression d'une station de pompiers : {}", address);

        dataRepository.getFirestations()
                .removeIf(f -> f.getAddress().equalsIgnoreCase(address));

        dataRepository.saveData();
    }

    /**
     * Récupère la liste de toutes les stations de pompiers.
     *
     * @return une liste de {@link Firestation}.
     */
    @Override
    public List<Firestation> getFirestations() {
        return dataRepository.getFirestations();
    }

    /**
     * Recherche une station de pompiers à partir d'une adresse.
     *
     * @param address adresse recherchée.
     * @return la {@link Firestation} correspondante ou {@code null} si aucune station n'est trouvée.
     */
    @Override
    public Firestation getFirestationByAddress(String address) {
        return dataRepository.getFirestations()
                .stream()
                .filter(f -> f.getAddress().equalsIgnoreCase(address))
                .findFirst()
                .orElse(null);
    }
}
