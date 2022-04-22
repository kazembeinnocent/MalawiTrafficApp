package com.example.trafficmi.Model;

public class DriversOffenceModel {
    private String disPayName;
    private String lisenceNumber;
    private String driverOffenceDescription;
    private String selectedSex;
    private String lat;
    private String longt;

    private String addres;

    public DriversOffenceModel(String disPayName, String lisenceNumber, String driverOffenceDescription, String selectedSex) {
        this.disPayName = disPayName;
        this.lisenceNumber = lisenceNumber;
        this.driverOffenceDescription = driverOffenceDescription;
        this.selectedSex = selectedSex;
    }

    public DriversOffenceModel(String disPayName,  String driverOffenceDescription, String lisenceNumber, String selectedSex,String lat,String longt, String addres) {
        this.disPayName = disPayName;
        this.lisenceNumber = lisenceNumber;
        this.driverOffenceDescription = driverOffenceDescription;
        this.selectedSex = selectedSex;
        this.lat = lat;
        this.longt = longt;
        this.addres = addres;
    }


    public String getDriverOffenceDescription() {
        return driverOffenceDescription;
    }

    public void setDriverOffenceDescription(String driverOffenceDescription) {
        this.driverOffenceDescription = driverOffenceDescription;
    }

    public String getSelectedSex() {
        return selectedSex;
    }

    public void setSelectedSex(String selectedSex) {
        this.selectedSex = selectedSex;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }


    public String getDisPayName() {
        return disPayName;
    }

    public void setDisPayName(String disPayName) {
        this.disPayName = disPayName;
    }

    public String getLisenceNumber() {
        return lisenceNumber;
    }

    public void setLisenceNumber(String lisenceNumber) {
        this.lisenceNumber = lisenceNumber;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongt() {
        return longt;
    }

    public void setLongt(String longt) {
        this.longt = longt;
    }
}
