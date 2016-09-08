package com.itechart.contactapp.model;

import java.util.Date;

public class Attachment {

    private long id;

    private String filename;
    private Date uploadDate;
    private String comments;

    private long contactId;

    public Attachment(long id, String filename, Date uploadDate, String comments, long contactId) {
        this.id = id;
        this.filename = filename;
        this.uploadDate = uploadDate;
        this.comments = comments;
        this.contactId = contactId;
    }

    public Attachment(String filename, Date uploadDate, String comments, long contactId) {
        this.filename = filename;
        this.uploadDate = uploadDate;
        this.comments = comments;
        this.contactId = contactId;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", uploadDate=" + uploadDate +
                ", comments='" + comments + '\'' +
                ", contactId=" + contactId +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }
}
