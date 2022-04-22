package com.example.trafficmi.Model;

public class AccidentSceneModel {
    private String regNum,color,name,yearOfMake,otherDetails;

    public AccidentSceneModel(String regNum, String color, String name, String yearOfMake,String otherDetails) {
        this.regNum = regNum;
        this.color = color;
        this.name = name;
        this.yearOfMake = yearOfMake;
        this.otherDetails = otherDetails;

    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfMake() {
        return yearOfMake;
    }

    public void setYearOfMake(String yearOfMake) {
        this.yearOfMake = yearOfMake;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }
}
