package com.itechart.contactapp.model;

import java.util.Date;
import java.util.List;

/**
 * Contact entity
 */
public class Contact {

    private int id;

    private String firstName;
    private String lastName;
    private String middleName;

    private Date birthday;
    private String status;
    private String gender;
    private String citizenship;
    private String webSite;
    private String email;
    private String jobCurrent;
    private Address address;

    private String photo;

    private List<Phone> phones;
    private List<Attachment> attachments;

    public Contact(int id, String firstName, String lastName, String middleName, Date birthday, String status,
                   String gender, String citizenship, String webSite, String email, String jobCurrent,
                   Address address, String photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.status = status;
        this.gender = gender;
        this.citizenship = citizenship;
        this.webSite = webSite;
        this.email = email;
        this.jobCurrent = jobCurrent;
        this.address = address;
        this.photo = photo;
    }

    public Contact(String firstName, String lastName, String middleName, Date birthday, String status, String gender,
                   String citizenship, String webSite, String email, String jobCurrent, Address address, String photo,
                   List<Phone> phones, List<Attachment> attachments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.status = status;
        this.gender = gender;
        this.citizenship = citizenship;
        this.webSite = webSite;
        this.email = email;
        this.jobCurrent = jobCurrent;
        this.address = address;
        this.photo = photo;
        this.phones = phones;
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthday=" + birthday +
                ", status='" + status + '\'' +
                ", gender='" + gender + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", webSite='" + webSite + '\'' +
                ", email='" + email + '\'' +
                ", jobCurrent='" + jobCurrent + '\'' +
                ", address=" + address +
                ", photo='" + photo + '\'' +
                ", phones=" + phones +
                ", attachments=" + attachments +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
