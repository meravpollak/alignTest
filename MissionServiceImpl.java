package com.example.aligntest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class MissionServiceImpl implements MissionService {

    @Autowired
    private MissionRepository missionRepository;

    private static final String apiKey = "my_api_key";

    @Autowired
    public MissionServiceImpl(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    public void addMission(Mission mission) {
        missionRepository.save(mission);
    }

    @Override
    public String getMostIsolatedCountry() {
        Optional<List<IsolatedCountries>> isolatedCountries = Optional.ofNullable(missionRepository.getIsolatedCountries());
        if (isolatedCountries.isPresent()) {
            List<IsolatedCountries> mostIsolatedCountries = isolatedCountries.get().stream().toList();
            IsolatedCountries mostIsolatedCountry = mostIsolatedCountries.stream()
                    .max(Comparator.comparingInt(IsolatedCountries::getCount)).get();
            return mostIsolatedCountry.toString();
        }
        return null;
    }

    @Override
    public String getMostIsolatedCountries() {
        Optional<List<IsolatedCountries>> isolatedCountries = Optional.ofNullable(missionRepository.getMostIsolatedCountries());
        List<IsolatedCountries> mostIsolatedCountry = isolatedCountries.get().stream().toList();
        return mostIsolatedCountry.toString();
    }

    @Override
    public Object findClosestMission(TargetLocation address) throws IOException {
        List<String> addressesOfMissions = missionRepository.findDistinctAddresses();
        String closestAddress = findClosestMission(address.getTargetAddress(), addressesOfMissions);
        if (closestAddress != null) {
            return missionRepository.findMissionByAddress(closestAddress);
        } else {
            return "can't find a mission";
        }
    }

    /**
     *
     * @param target
     * @param addressOfMission - list with all addresses of missions
     * @return the address that closest to target location
     * @throws IOException
     */
    private static String findClosestMission(String target, List<String> addressOfMission) throws IOException {
        String closestAddress = null;
        int minDistanceValue = -1;
        for (String address : addressOfMission) {
            try {
                String url = "https://api.distancematrix.ai/maps/api/distancematrix/json?origins=" + address + "&destinations=" + target + "&key=" + apiKey;
                int distance = getDistance(String.valueOf(ToolService.sendGETRequest(url)));
                if (distance != -1) {
                    if (minDistanceValue == -1 || distance < minDistanceValue) {
                        minDistanceValue = distance;
                        closestAddress = address;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return closestAddress;
    }

    /**
     *
     * @param jsonResponse extract distance value from the response body
     * @return int distance , if not exist return -1
     */
    public static Integer getDistance(String jsonResponse) {
        if (jsonResponse != null || !jsonResponse.isEmpty()) {
            if (jsonResponse.contains("distance")) {
                String distanceObject = jsonResponse.substring(jsonResponse.indexOf("distance") + 10, jsonResponse.indexOf("duration") - 2);
                JSONObject jsonDistance = new JSONObject(distanceObject);
                return (Integer) jsonDistance.get("value");
            }
        }
        return -1;
    }
}

