package com.itechart.contactapp.model;

public class Phone {

    private long id;

    private String countryCode;
    private String operatorCode;
    private String phoneNumber;

    private long contactId;
    private String phoneType;
    private String comments;

    public Phone(long id, String countryCode, String operatorCode, String phoneNumber, long contactId, String phoneType, String comments) {
        this.id = id;
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.phoneNumber = phoneNumber;
        this.contactId = contactId;
        this.phoneType = phoneType;
        this.comments = comments;
    }

    public Phone(String countryCode, String operatorCode, String phoneNumber, long contactId, String phoneType, String comments) {
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.phoneNumber = phoneNumber;
        this.contactId = contactId;
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
                ", contactId=" + contactId +
                ", phoneType='" + phoneType + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
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