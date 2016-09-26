package com.itechart.contactapp.model;

import org.apache.commons.lang3.StringUtils;

public class Address {

    private String country;
    private String city;
    private String street;
    private String house;
    private String flat;

    private String zipCode;

    public Address(String country, String city, String street, String house, String flat, String zipCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        String[] fullAddress = {country, city, street, house, flat};
        String result = "";
        for (String s : fullAddress) {
            if (StringUtils.isNotEmpty(s))
                result += s + ", ";
        }
        if (StringUtils.isNotEmpty(result))
            return result.substring(0, result.length() - 2);
        else return result;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
