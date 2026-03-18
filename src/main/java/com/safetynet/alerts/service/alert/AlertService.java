package com.safetynet.alerts.service.alert;

import com.safetynet.alerts.controller.dto.child.ChildAlertDTO;
import com.safetynet.alerts.controller.dto.fire.FireAddressDTO;
import com.safetynet.alerts.controller.dto.firestation.FirestationResponseDTO;
import com.safetynet.alerts.controller.dto.flood.FloodDTO;
import com.safetynet.alerts.controller.dto.person.PersonInfoDTO;

import java.util.List;
import java.util.Map;

/**
 * Interface définissant les services d'alerte pour SafetyNet Alerts.
 *
 * <p>Cette interface fournit les opérations permettant de récupérer
 * des informations sur les personnes, leur couverture par les stations
 * de pompiers, et les alertes spécifiques comme les incendies ou les
 * inondations.</p>
 *
 * <p>Elle est utilisée par les controllers pour exposer les endpoints
 * REST correspondants.</p>
 */
public interface AlertService {

    /**
     * Récupère la liste des personnes couvertes par une station
     * de pompiers ainsi que le nombre d'adultes et d'enfants.
     *
     * @param stationNumber numéro de la station de pompiers.
     * @return {@link FirestationResponseDTO} contenant les personnes et les compteurs.
     */
    FirestationResponseDTO getPersonCoveredByStation(int stationNumber);

    /**
     * Récupère les enfants vivant à une adresse
     * donnée et les autres membres du foyer.
     *
     * @param address adresse à interroger.
     * @return {@link ChildAlertDTO} contenant la liste des enfants et de leurs foyers.
     */
    ChildAlertDTO getChildrenByAddress(String address);

    /**
     * Récupère les numéros de téléphone des
     * personnes couvertes par une station.
     *
     * @param stationNumber numéro de la station de pompiers.
     * @return la liste des numéros de téléphone.
     */
    List<String> getPhoneNumbersByStation(int stationNumber);

    /**
     * Récupère les informations liées à une adresse
     * dans le cadre d'une alerte incendie.
     *
     * @param address - Adresse à interroger.
     * @return {@link FireAddressDTO} contenant le numéro de la station et les habitants.
     */
    FireAddressDTO getFireByAddress(String address);

    /**
     * Récupère les informations des habitants pour
     * plusieurs stations en cas d'inondation.
     *
     * @param stations la liste des numéros de stations à interroger.
     * @return map associant chaque numéro de station à la liste des {@link FloodDTO} correspondants.
     */
    Map<Integer, List<FloodDTO>> getFloodByStations(List<Integer> stations);

    /**
     * Récupère les informations détaillées d'une personne
     * identifiée par son prénom et son nom de famille.
     *
     * @param firstName prénom de la personne.
     * @param lastName nom de famille de la personne.
     * @return une liste de {@link PersonInfoDTO} correspondant aux critères.
     */
    List<PersonInfoDTO> getPersonInfo(String firstName, String lastName);

    /**
     * Récupère les informations détaillées de toutes
     * les personnes portant un nom de famille donné.
     *
     * @param lastName nom de famille.
     * @return une liste de {@link PersonInfoDTO} correspondant aux critères.
     */
    List<PersonInfoDTO> getPersonInfoByLastName(String lastName);

    /**
     * Récupère les adresses email des habitants d'une ville donnée.
     *
     * @param city nom de la ville.
     * @return une liste des adresses email distinctes.
     */
    List<String> getCommunityEmails(String city);
}
