package com.safetynet.alerts.service.firestation;

import com.safetynet.alerts.model.Firestation;

import java.util.List;

public interface FirestationService {

    void addFirestation(Firestation firestation);

    void updateFirestation(Firestation firestation);

    void deleteFirestation(String address);

    List<Firestation> getFirestations();

    Firestation getFirestationByAddress(String address);
}
