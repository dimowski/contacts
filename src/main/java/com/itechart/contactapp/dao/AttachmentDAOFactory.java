package com.itechart.contactapp.dao;

import javax.sql.DataSource;

public class AttachmentDAOFactory {
    public static AttachmentDAO getAttachmentDAO(DataSource dataSource) {
        return new AttachmentDAOUtil(dataSource);
    }
}
