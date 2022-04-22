package com.example.trafficmi.Model;

public class AccidentSceneRecords {
    String carRegNumber, licenseNumber,location;

    public AccidentSceneRecords() {

    }

    public AccidentSceneRecords(String carRegNumber, String licenseNumber, String location) {
        this.carRegNumber = carRegNumber;
        this.licenseNumber = licenseNumber;
        this.location = location;
    }

    public String getCarRegNumber() {
        return carRegNumber;
    }

    public void setCarRegNumber(String carRegNumber) {
        this.carRegNumber = carRegNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
