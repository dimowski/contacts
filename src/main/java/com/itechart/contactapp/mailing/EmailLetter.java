package com.itechart.contactapp.mailing;

public class EmailLetter {

    private String emailRecipient;
    private  String emailSubject;
    private String messageBody;

    public EmailLetter(String emailRecipient, String emailSubject, String messageBody) {
        this.emailRecipient = emailRecipient;
        this.emailSubject = emailSubject;
        this.messageBody = messageBody;
    }

    public String getEmailRecipient() {
        return emailRecipient;
    }

    public void setEmailRecipient(String emailRecipient) {
        this.emailRecipient = emailRecipient;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
