package com.itechart.contactapp;

import java.util.Date;

public class Contact {
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private String gender;
    private String citizenship;
    private String status;
    private String webSite;
    private String email;
    private String jobCurrent;

    public Contact(String firstName, String lastName, String middleName, Date birthday, String gender,
                   String citizenship, String status, String webSite, String email, String jobCurrent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.gender = gender;
        this.citizenship = citizenship;
        this.status = status;
        this.webSite = webSite;
        this.email = email;
        this.jobCurrent = jobCurrent;
    }

    public Contact(int id, String firstName, String lastName, String middleName, Date birthday, String gender,
                   String citizenship, String status, String webSite, String email, String jobCurrent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.gender = gender;
        this.citizenship = citizenship;
        this.status = status;
        this.webSite = webSite;
        this.email = email;
        this.jobCurrent = jobCurrent;
    }

    //TEMP CONSTRUCTOR
    public Contact(long id, String firstName, String lastName, String middleName, Date birthday, String citizenship, String webSite, String email, String jobCurrent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.citizenship = citizenship;
        this.webSite = webSite;
        this.email = email;
        this.jobCurrent = jobCurrent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobCurrent() {
        return jobCurrent;
    }

    public void setJobCurrent(String jobCurrent) {
        this.jobCurrent = jobCurrent;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", status='" + status + '\'' +
                ", webSite='" + webSite + '\'' +
                ", email='" + email + '\'' +
                ", jobCurrent='" + jobCurrent + '\'' +
                '}';
    }
}
