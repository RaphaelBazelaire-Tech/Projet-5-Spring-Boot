package com.safetynet.alerts.controller;

import com.safetynet.alerts.controller.dto.child.ChildAlertDTO;
import com.safetynet.alerts.controller.dto.fire.FireAddressDTO;
import com.safetynet.alerts.controller.dto.firestation.FirestationResponseDTO;
import com.safetynet.alerts.controller.dto.flood.FloodDTO;
import com.safetynet.alerts.controller.dto.person.PersonInfoDTO;
import com.safetynet.alerts.service.alert.AlertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Contrôleur REST exposant les endpoints d'alerte de l'application SafetyNet Alerts.
 *
 * <p>Ce contrôleur permet d'accéder à différentes informations liées aux alertes
 * de sécurité publique, comme :</p>
 * <ul>
 *     <li>les personnes couvertes par une station de pompiers</li>
 *     <li>les enfants vivant à une adresse spécifique</li>
 *     <li>les numéros de téléphone associés à une station</li>
 *     <li>les informations liées à un incendie à une adresse</li>
 *     <li>les foyers couverts par plusieurs stations</li>
 *     <li>les informations détaillées d'une personne</li>
 *     <li>les emails des habitants d'une ville</li>
 * </ul>
 *
 * <p>Les traitements sont délégués au service {@link AlertService}
 * qui contient la logique métier associée.</p>
 */
@RestController
public class AlertController {

    private static final Logger logger = LogManager.getLogger(AlertController.class);

    private final AlertService alertService;

    /**
     * Constructeur du contrôleur d'alertes.
     *
     * @param alertService le service responsable de la gestion
     * des opérations liées aux alertes.
     */
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    /**
     * Récupère la liste des personnes couvertes par une station de pompiers
     * ainsi que le nombre d'adultes et d'enfants.
     *
     * <p>Endpoint : <b>GET /firestation</b></p>
     *
     * @param stationNumber numéro de la station de pompiers.
     * @return une {@link ResponseEntity} contenant un {@link FirestationResponseDTO}
     * avec la liste des personnes couvertes par la station.
     */
    @GetMapping("/firestation")
    public ResponseEntity<FirestationResponseDTO> getPersonCoveredByStation(@RequestParam int stationNumber) {
        logger.info("GET /firestation - Récupération des personnes couvertes par la station {}", stationNumber);

        return ResponseEntity.ok(alertService.getPersonCoveredByStation(stationNumber));
    }

    /**
     * Récupère la liste des enfants vivant à une adresse donnée
     * ainsi que les autres membres du foyer.
     *
     * <p>Endpoint : <b>GET /childAlert</b></p>
     *
     * @param address adresse à analyser.
     * @return une {@link ResponseEntity} contenant un {@link ChildAlertDTO}
     * avec les informations des enfants et des autres habitants.
     */
    @GetMapping("/childAlert")
    public ResponseEntity<ChildAlertDTO> getChildAddress(@RequestParam String address) {
        logger.info("GET /childAlert - Récupération des enfants à l'adresse {}", address);

        return ResponseEntity.ok(alertService.getChildrenByAddress(address));
    }

    /**
     * Récupère les numéros de téléphone des habitants couverts
     * par une station de pompiers spécifique.
     *
     * <p>Endpoint : <b>GET /phoneAlert</b></p>
     *
     * @param firestation numéro de la station de pompiers.
     * @return une {@link ResponseEntity} contenant une liste de numéros de téléphone.
     */
    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> getPhoneAddressCoveredByStation(@RequestParam int firestation) {
        logger.info("GET /phoneAlert - Récupération des numéros de téléphones affilié à la station {}", firestation);

        return ResponseEntity.ok(alertService.getPhoneNumbersByStation(firestation));
    }

    /**
     * Récupère les informations des habitants d'une adresse donnée
     * ainsi que la station de pompiers correspondante.
     *
     * <p>Endpoint : <b>GET /fire</b></p>
     *
     * @param address adresse concernée par l'incident.
     * @return une {@link ResponseEntity} contenant un {@link FireAddressDTO}
     * avec les informations des habitants et la station associée.
     */
    @GetMapping("/fire")
    public ResponseEntity<FireAddressDTO> getFireByAddress(@RequestParam String address) {
        logger.info("GET /fire - Récupération des informations liées à l'adresse {}", address);

        return ResponseEntity.ok(alertService.getFireByAddress(address));
    }

    /**
     * Récupère la liste des foyers couverts par plusieurs stations
     * de pompiers avec les informations des habitants.
     *
     * <p>Endpoint : <b>GET /flood/stations</b></p>
     *
     * @param stations liste des numéros de stations.
     * @return une {@link ResponseEntity} contenant une {@link Map}
     * associant chaque station à la liste des foyers couverts.
     */
    @GetMapping("/flood/stations")
    public ResponseEntity<Map<Integer, List<FloodDTO>>> getFloodByStation(@RequestParam List<Integer> stations) {
        logger.info("GET /flood/stations - Récupération des adresses pour les stations {}", stations);

        return ResponseEntity.ok(alertService.getFloodByStations(stations));
    }

    /**
     * Récupère les informations détaillées des personnes
     * correspondant à un nom de famille donné.
     *
     * <p>Endpoint : <b>GET /personInfo</b></p>
     *
     * @param lastName nom de famille de la personne recherchée.
     * @return une {@link ResponseEntity} contenant une liste de {@link PersonInfoDTO}
     */
    @GetMapping("/personInfo")
    public ResponseEntity<List<PersonInfoDTO>> getPersonInfoByLastName(@RequestParam String lastName) {
        logger.info("GET /personInfo - Récupération des informations de la personne {}", lastName);

        return ResponseEntity.ok(alertService.getPersonInfoByLastName(lastName));
    }

    /**
     * Récupère tous les emails des habitants d'une ville donnée.
     *
     * <p>Endpoint : <b>GET /communityEmail</b></p>
     *
     * @param city nom de la ville souhaitée.
     * @return une {@link ResponseEntity} contenant la liste des adresses email.
     */
    @GetMapping("/communityEmail")
    public ResponseEntity<List<String>> getCommunityEmail(@RequestParam String city) {
        logger.info("GET /communityEmail - Récupérations des mails dans la ville {}", city);

        return ResponseEntity.ok(alertService.getCommunityEmails(city));
    }
}
