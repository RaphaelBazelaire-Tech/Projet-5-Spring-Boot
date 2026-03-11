package com.safetynet.alerts.service.alert;

import com.safetynet.alerts.controller.dto.child.ChildAlertDTO;
import com.safetynet.alerts.controller.dto.fire.FireAddressDTO;
import com.safetynet.alerts.controller.dto.firestation.FirestationResponseDTO;
import com.safetynet.alerts.controller.dto.flood.FloodDTO;
import com.safetynet.alerts.controller.dto.person.PersonInfoDTO;

import java.util.List;
import java.util.Map;

public interface AlertService {

    FirestationResponseDTO getPersonCoveredByStation(int stationNumber);

    ChildAlertDTO getChildrenByAddress(String address);

    List<String> getPhoneNumbersByStation(int stationNumber);

    FireAddressDTO getFireByAddress(String address);

    Map<Integer, List<FloodDTO>> getFloodByStations(List<Integer> stations);

    List<PersonInfoDTO> getPersonInfo(String firstName, String lastName);

    List<String> getCommunityEmails(String city);
}
