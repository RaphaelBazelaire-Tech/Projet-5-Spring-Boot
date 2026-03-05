package com.safetynet.alerts.service.firestation;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.DataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirestationServiceImpl implements FirestationService {

    private static final Logger logger = LogManager.getLogger(FirestationServiceImpl.class);

    private final DataRepository dataRepository;

    public FirestationServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void addFirestation(Firestation firestation) {
        logger.info("Ajout d'une station de pompiers : {}", firestation.getAddress());
        dataRepository.getFirestations().add(firestation);
    }

    @Override
    public void updateFirestation(Firestation firestation) {
        logger.info("Mise à jour d'une station de pompiers : {}", firestation.getAddress());

        Optional<Firestation> existing = dataRepository.getFirestations()
                .stream()
                .filter(f -> f.getAddress().equalsIgnoreCase(firestation.getAddress()))
                .findFirst();

        existing.ifPresent(f -> f.setStation(firestation.getStation()));
    }

    @Override
    public void deleteFirestation(String address) {
        logger.info("Suppression d'une station de pompiers : {}", address);

        dataRepository.getFirestations()
                .removeIf(f -> f.getAddress().equalsIgnoreCase(address));
    }

    @Override
    public List<Firestation> getFirestations() {
        return dataRepository.getFirestations();
    }

    @Override
    public Firestation getFirestationByAddress(String address) {
        return dataRepository.getFirestations()
                .stream()
                .filter(f -> f.getAddress().equalsIgnoreCase(address))
                .findFirst()
                .orElse(null);
    }
}
