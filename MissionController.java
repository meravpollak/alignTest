package com.example.aligntest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class MissionController {

    private final MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping(value="/mission",produces = MediaType.APPLICATION_JSON_VALUE)
    public void addMission(@Valid @RequestBody Mission mission) {
        missionService.addMission(mission);
    }

    @GetMapping("/countries-by-isolation")
    public String countriesByIsolation() {
        return missionService.getMostIsolatedCountry();
    }

    @GetMapping("/countries-by-most-isolation")
    public String mostIsolatedCountries() {
        return missionService.getMostIsolatedCountries();
    }

    @PostMapping(value="/find-closest",produces = MediaType.APPLICATION_JSON_VALUE)
    public Object findClosest(@Valid @RequestBody TargetLocation targetLocation) throws IOException {
        return (Object) missionService.findClosestMission(targetLocation);
    }
}
