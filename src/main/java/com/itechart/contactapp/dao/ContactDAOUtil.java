package com.itechart.contactapp.dao;

import com.itechart.contactapp.model.Address;
import com.itechart.contactapp.model.Attachment;
import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.model.Phone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactDAOUtil implements ContactDAO {

    private static final Logger log = LogManager.getLogger(ContactDAOUtil.class);
    private DataSource dataSource;

    public ContactDAOUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    @Override
    public Map<Integer, Contact> getContacts(int page) {
        Map<Integer, Contact> contacts = new HashMap<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            log.info("Establishing connection to DB");
            connection = dataSource.getConnection();
            String sql = "SELECT * FROM contacts c LEFT JOIN address a ON c.address_id=a.address_id " +
                    "ORDER BY c.contact_id LIMIT 10 OFFSET ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 10*(page - 1));
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int contactId = resultSet.getInt("contact_id");
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
                String photo = resultSet.getString("photo");

                long addressId = resultSet.getInt("address_id");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                String flat = resultSet.getString("flat");
                String zipCode = resultSet.getString("zip_code");

                Address address = new Address(addressId, country, city, street, house, flat, zipCode);

                Contact tempContact = new Contact(contactId, firstName, lastName, middleName, birthday, status, gender,
                        citizenship, webSite, email, jobCurrent, address, photo);
                contacts.put(tempContact.getId(), tempContact);
            }
            log.info(contacts.size() + " contacts retrieved");
        } catch (SQLException e) {
            log.error("Unable to get contact list", e);
        } finally {
            close(connection, statement, resultSet);
        }
        return contacts;
    }

    @Override
    public List<Phone> getPhonesByContactId(int contactId) {
        return null;
    }

    @Override
    public List<Attachment> getAttachementsByContactId(int contactId) {
        return null;
    }

    @Override
    public int getContactsCount() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int result = 0;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT COUNT(*) count FROM contacts";
            resultSet = statement.executeQuery(sql);
            resultSet.next();

            result = resultSet.getInt("count");
            log.info("Returned {} contacts", result);
        } catch (SQLException e) {
            log.error("Unable to get contacts count", e);
        } finally {
            close(connection, statement, resultSet);
        }
        return result;
    }

    /*@Override
    public Address getContactById(int id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        return null;
    }*/

    private void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            log.error("Unable to close connection", e);
        }
    }
}
