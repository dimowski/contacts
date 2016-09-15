package com.itechart.contactapp.model;

public class Phone {

    private int id;

    private String countryCode;
    private String operatorCode;
    private String phoneNumber;

    //private long contactId;
    private String phoneType;
    private String comments;

    public Phone(int id, String countryCode, String operatorCode, String phoneNumber, String phoneType, String comments) {
        this.id = id;
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
        this.comments = comments;
    }

    public Phone(String countryCode, String operatorCode, String phoneNumber, String phoneType, String comments) {
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", operatorCode='" + operatorCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneType='" + phoneType + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}