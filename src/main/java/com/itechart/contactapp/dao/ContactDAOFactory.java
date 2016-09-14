package com.itechart.contactapp.dao;

import javax.sql.DataSource;

public class ContactDAOFactory {

    public static ContactDAO getContactDAO(DataSource dataSource) {
        return new ContactDAOUtil(dataSource);
    }
}
