package com.example.trafficmi.Model;

import com.google.android.material.textfield.TextInputLayout;

public class VehicleTheftReport {

    String  carName,
            carMake,
            carColor,
            vehicleRegNumber,
            vehicle_blue_book,
            vehicleTheftDescription,
            selectedSex,
            location,
            latitude, longitude;

    public VehicleTheftReport() {

    }

    public VehicleTheftReport(String carName, String carMake, String carColor, String vehicleRegNumber, String vehicle_blue_book, String vehicleTheftDescription, String selectedSex, String location, String latitude, String longitude) {
        this.carName = carName;
        this.carMake = carMake;
        this.carColor = carColor;
        this.vehicleRegNumber = vehicleRegNumber;
        this.vehicle_blue_book = vehicle_blue_book;
        this.vehicleTheftDescription = vehicleTheftDescription;
        this.selectedSex = selectedSex;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public VehicleTheftReport(String  carName, String  carMake, String carColor, String vehicleRegNumber, String vehicle_blue_book, String vehicleTheftDescription, String selectedSex, String location) {

        this.carName = carName;
        this.carMake = carMake;
        this.carColor = carColor;
        this.vehicleRegNumber = vehicleRegNumber;
        this.vehicle_blue_book = vehicle_blue_book;
        this.vehicleTheftDescription = vehicleTheftDescription;
        this.selectedSex = selectedSex;
        this.location = location;

    }

    public VehicleTheftReport( String  carName, String carColor, String vehicleRegNumber,String vehicleTheftDescription, String selectedSex) {
        this.carName = carName;
        this.carColor = carColor;
        this.vehicleRegNumber = vehicleRegNumber;
        this.vehicleTheftDescription = vehicleTheftDescription;
        this.selectedSex = selectedSex;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    public String getVehicle_blue_book() {
        return vehicle_blue_book;
    }

    public void setVehicle_blue_book(String vehicle_blue_book) {
        this.vehicle_blue_book = vehicle_blue_book;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVehicleTheftDescription() {
        return vehicleTheftDescription;
    }

    public void setVehicleTheftDescription(String vehicleTheftDescription) {
        this.vehicle_blue_book = vehicleTheftDescription;
    }

    public String getSelectedSex() {
        return selectedSex;
    }

    public void setSelectedSex(String selectedSex) {
        this.selectedSex = selectedSex;
    }
}
