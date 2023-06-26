package com.ipn.temperature.api.model;

public class Temperature {

    private double degrees;
    private char type;

    public Temperature(double degrees, char type) {
        this.degrees = degrees;
        this.type = type;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
}
