package com.itechart.contactapp.dao;

import com.itechart.contactapp.model.Address;
import com.itechart.contactapp.model.Contact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactDAO {
    private DataSource dataSource;
    private static final Logger log = LogManager.getLogger(ContactDAO.class);

    public ContactDAO(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Contact> getContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            log.info("Establishing connection to DB");
            connection = dataSource.getConnection();
            String sql = "SELECT * FROM contacts c LEFT JOIN address a ON c.address_id=a.address_id LIMIT 10";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                long contactId = resultSet.getInt("contact_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String middleName = resultSet.getString("middle_name");
                Date birthday = resultSet.getDate("birthday");
                String status = resultSet.getString("status");
                String gender = resultSet.getString("gender");
                String citizenship = resultSet.getString("citizenship");
                String webSite = resultSet.getString("web_site");
                String email = resultSet.getString("email");
                String jobCurrent = resultSet.getString("job_current");

                long addressId = resultSet.getInt("address_id");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                String zipCode = resultSet.getString("zip_code");

                Address address = new Address(addressId, country, city, street, house, zipCode);

                Contact tempContact = new Contact(contactId, firstName, lastName, middleName, birthday, status, gender,
                        citizenship, webSite, email, jobCurrent, address);
                contacts.add(tempContact);
            }
            log.info(contacts.size() + "contacts added");
            return contacts;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    private void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            log.error(e);
        }
    }
}
