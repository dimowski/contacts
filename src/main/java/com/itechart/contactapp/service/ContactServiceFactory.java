package com.itechart.contactapp.service;

import javax.sql.DataSource;
import java.util.Properties;

public class ContactServiceFactory {
    public static ContactService getContactService() {
        return new DefaultContactService();
    }
}
