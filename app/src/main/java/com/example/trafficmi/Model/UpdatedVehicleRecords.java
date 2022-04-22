package com.example.trafficmi.Model;

public class UpdatedVehicleRecords {

        String nameOfVehicle, make, vehicleColor, vehicleRegNumber,otherDetails;

        public UpdatedVehicleRecords() {

        }

        public UpdatedVehicleRecords(String nameOfVehicle, String make, String vehicleColor, String vehicleRegNumber, String otherDetails) {
            this.nameOfVehicle = nameOfVehicle;
            this.make = make;
            this.vehicleColor = vehicleColor;
            this.vehicleRegNumber = vehicleRegNumber;
            this.otherDetails = otherDetails;
        }

        public String getNameOfVehicle() {
            return nameOfVehicle;
        }

        public String getMake() {
            return make;
        }

        public String getVehicleColor() {
            return vehicleColor;
        }

        public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

        public void setNameOfVehicle(String firstName) {
            this.nameOfVehicle = nameOfVehicle;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public void setVehicleColor(String vehicleColor) {
            this.vehicleColor = vehicleColor;
        }

    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

}
