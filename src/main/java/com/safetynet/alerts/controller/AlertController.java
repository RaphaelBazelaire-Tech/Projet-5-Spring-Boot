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

@RestController
public class AlertController {

    private static final Logger logger = LogManager.getLogger(AlertController.class);

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/firestation")
    public ResponseEntity<FirestationResponseDTO> getPersonCoveredByStation(@RequestParam int stationNumber) {
        logger.info("GET /firestation - Récupération des personnes couvertes par la station {}", stationNumber);

        return ResponseEntity.ok(alertService.getPersonCoveredByStation(stationNumber));
    }

    @GetMapping("/childAlert")
    public ResponseEntity<ChildAlertDTO> getChildAddress(@RequestParam String address) {
        logger.info("GET /childAlert - Récupération des enfants à l'adresse {}", address);

        return ResponseEntity.ok(alertService.getChildrenByAddress(address));
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> getPhoneAddressCoveredByStation(@RequestParam int firestation) {
        logger.info("GET /phoneAlert - Récupération des numéros de téléphones affilié à la station {}", firestation);

        return ResponseEntity.ok(alertService.getPhoneNumbersByStation(firestation));
    }

    @GetMapping("/fire")
    public ResponseEntity<FireAddressDTO> getFireByAddress(@RequestParam String address) {
        logger.info("GET /fire - Récupération des numéros de téléphones affilié à la station {}", address);

        return ResponseEntity.ok(alertService.getFireByAddress(address));
    }

    @GetMapping("/flood/stations")
    public ResponseEntity<Map<Integer, List<FloodDTO>>> getFloodByStation(@RequestParam List<Integer> stations) {
        logger.info("GET /flood/stations - Récupération des adresses pour les stations {}", stations);

        return ResponseEntity.ok(alertService.getFloodByStations(stations));
    }

    @GetMapping("/personInfo")
    public ResponseEntity<List<PersonInfoDTO>> getPersonInfoByLastName(@RequestParam String lastName) {
        logger.info("GET /personInfo - Récupération des informations de la personne {}", lastName);

        return ResponseEntity.ok(alertService.getPersonInfoByLastName(lastName));
    }

    @GetMapping("/communityEmail")
    public ResponseEntity<List<String>> getCommunityEmail(@RequestParam String city) {
        logger.info("GET /communityEmail - Récupérations des mails dans la ville {}", city);

        return ResponseEntity.ok(alertService.getCommunityEmails(city));
    }
}
