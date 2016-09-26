package com.itechart.contactapp.dao;

import javax.sql.DataSource;

public class PhoneDAOFactory {
    public static PhoneDAO getPhoneDAO(DataSource dataSource) {
        return new PhoneDAOUtil(dataSource);
    }
}
