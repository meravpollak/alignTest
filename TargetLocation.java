package com.example.aligntest;


import com.fasterxml.jackson.annotation.JsonProperty;


public class TargetLocation {

    @JsonProperty("target-location")
    private String targetLocation;

    public TargetLocation(String targetAddress) {
        this.targetLocation = targetAddress;
    }
    
    public TargetLocation() {}
    
    public String getTargetAddress() {
        return targetLocation;
    }
    public void setTargetAddress(String targetAddress) {
        this.targetLocation = targetAddress;
    }
   
}
