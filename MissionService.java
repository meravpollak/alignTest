package com.example.aligntest;


import java.io.IOException;

public interface MissionService {
    void addMission(Mission mission);
    String getMostIsolatedCountry();
    String getMostIsolatedCountries();
    Object findClosestMission(TargetLocation address) throws IOException;
}
