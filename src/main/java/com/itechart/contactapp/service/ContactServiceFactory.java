package com.itechart.contactapp.service;

import javax.sql.DataSource;
import java.util.Properties;

public class ContactServiceFactory {
    public static ContactService getContactService(DataSource dataSource, Properties properties) {
        return new DefaultContactService(dataSource, properties);
    }
}
