package com.itechart.contactapp.service;

import javax.sql.DataSource;

public class ContactServiceFactory {
    public static ContactService getContactService() {
        return new DefaultContactService();
    }
}
