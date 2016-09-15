package com.itechart.contactapp.model;

import java.util.Date;

public class Attachment {

    private int id;

    private String filename;
    private Date uploadDate;
    private String comments;

    public Attachment(int id, String filename, Date uploadDate, String comments) {
        this.id = id;
        this.filename = filename;
        this.uploadDate = uploadDate;
        this.comments = comments;
    }

    public Attachment(String filename, Date uploadDate, String comments) {
        this.filename = filename;
        this.uploadDate = uploadDate;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", uploadDate=" + uploadDate +
                ", comments='" + comments + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
