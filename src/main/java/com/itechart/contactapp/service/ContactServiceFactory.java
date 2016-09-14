package com.itechart.contactapp.service;

import javax.sql.DataSource;

public class ContactServiceFactory {
    public static ContactService getContactService(DataSource dataSource) {
        return new DefaultContactService(dataSource);
    }
}
