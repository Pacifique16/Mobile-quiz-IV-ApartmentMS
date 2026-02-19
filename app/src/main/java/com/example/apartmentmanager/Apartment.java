package com.example.apartmentmanager;

public class Apartment {
    private Integer apartmentID;
    private String unitNumber;
    private Float squareFootage;
    private Double rentAmount;
    private Boolean isAirBnb;
    private Boolean allowsPets;
    private String location;

    public Apartment() {}

    public Apartment(Integer apartmentID, String unitNumber, Float squareFootage, 
                     Double rentAmount, Boolean isAirBnb, Boolean allowsPets, String location) {
        this.apartmentID = apartmentID;
        this.unitNumber = unitNumber;
        this.squareFootage = squareFootage;
        this.rentAmount = rentAmount;
        this.isAirBnb = isAirBnb;
        this.allowsPets = allowsPets;
        this.location = location;
    }

    public Integer getApartmentID() { return apartmentID; }
    public void setApartmentID(Integer apartmentID) { this.apartmentID = apartmentID; }

    public String getUnitNumber() { return unitNumber; }
    public void setUnitNumber(String unitNumber) { this.unitNumber = unitNumber; }

    public Float getSquareFootage() { return squareFootage; }
    public void setSquareFootage(Float squareFootage) { this.squareFootage = squareFootage; }

    public Double getRentAmount() { return rentAmount; }
    public void setRentAmount(Double rentAmount) { this.rentAmount = rentAmount; }

    public Boolean getIsAirBnb() { return isAirBnb; }
    public void setIsAirBnb(Boolean isAirBnb) { this.isAirBnb = isAirBnb; }

    public Boolean getAllowsPets() { return allowsPets; }
    public void setAllowsPets(Boolean allowsPets) { this.allowsPets = allowsPets; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getDailyRent() {
        return rentAmount / 30;
    }
}
