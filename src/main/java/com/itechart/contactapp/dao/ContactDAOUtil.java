package com.itechart.contactapp.dao;

import com.itechart.contactapp.model.Address;
import com.itechart.contactapp.model.Attachment;
import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.model.Phone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.Date;

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
            String sql = "SELECT * FROM contacts ORDER BY contact_id LIMIT 10 OFFSET ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 10 * (page - 1));
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

                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                String flat = resultSet.getString("flat");
                String zipCode = resultSet.getString("zip_code");

                Address address = new Address(country, city, street, house, flat, zipCode);

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
        List<Phone> phones = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String sql = "SELECT * FROM phone WHERE contact_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, contactId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("phone_id");
                String countryCode = resultSet.getString("country_code");
                String operatorCode = resultSet.getString("operator_code");
                String phoneNumber = resultSet.getString("phone_number");
                String phoneType = resultSet.getString("phone_type");
                String comments = resultSet.getString("comments");

                Phone tempPhone = new Phone(id, countryCode, operatorCode, phoneNumber, phoneType, comments);
                phones.add(tempPhone);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            close(connection, statement, resultSet);
        }
        log.info(phones.size() + " phones retrieved");
        return phones;
    }

    @Override
    public List<Attachment> getAttachmentsByContactId(int contactId) {
        List<Attachment> attachments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String sql = "SELECT * FROM attachment WHERE contact_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, contactId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("attachment_id");
                String filename = resultSet.getString("filename");
                Date uploadDate = resultSet.getDate("upload_date");
                String comments = resultSet.getString("comments");

                Attachment tempAttachment = new Attachment(id, filename, uploadDate, comments);
                attachments.add(tempAttachment);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            close(connection, statement, resultSet);
        }
        return attachments;
    }

    @Override
    public void createAttachment(Attachment attachment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String sql = "INSERT INTO attachment (contact_id, filename, upload_date, comments)" +
                    "VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, attachment.getContactId());
            preparedStatement.setString(2, attachment.getFilename());
            preparedStatement.setTimestamp(3, new Timestamp(attachment.getUploadDate().getTime()));
            preparedStatement.setString(4, attachment.getComments());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void createContact(Contact contact) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String sql1 = "INSERT INTO contacts (first_name, last_name, middle_name, birthday, status, gender," +
                    "citizenship, web_site, email, job_current, photo, country, city, street, house, flat, zip_code)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            //String sql2 = ""
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getMiddleName());
            if (contact.getBirthday() != null) {
                preparedStatement.setDate(4, new java.sql.Date(contact.getBirthday().getTime()));
            } else
                preparedStatement.setDate(4, null);
            preparedStatement.setString(5, contact.getStatus());
            preparedStatement.setString(6, contact.getGender());
            preparedStatement.setString(7, contact.getCitizenship());
            preparedStatement.setString(8, contact.getWebSite());
            preparedStatement.setString(9, contact.getEmail());
            preparedStatement.setString(10, contact.getJobCurrent());
            preparedStatement.setString(11, contact.getPhoto());
            preparedStatement.setString(12, contact.getAddress().getCountry());
            preparedStatement.setString(13, contact.getAddress().getCity());
            preparedStatement.setString(14, contact.getAddress().getStreet());
            preparedStatement.setString(15, contact.getAddress().getHouse());
            preparedStatement.setString(16, contact.getAddress().getFlat());
            preparedStatement.setString(17, contact.getAddress().getZipCode());
            log.debug(preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("Unable to create contact", e);
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    @Override
    public void updateContact(Contact contact) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String sql1 = "UPDATE contacts SET first_name=?, last_name=?, middle_name=?, birthday=?, status=?, gender=?," +
                    "citizenship=?, web_site=?, email=?, job_current=?, photo=?, country=?, city=?, street=?, house=?," +
                    "flat=?, zip_code=? WHERE contact_id=?";
            //String sql2 = ""
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getMiddleName());
            preparedStatement.setDate(4, new java.sql.Date(contact.getBirthday().getTime()));
            preparedStatement.setString(5, contact.getStatus());
            preparedStatement.setString(6, contact.getGender());
            preparedStatement.setString(7, contact.getCitizenship());
            preparedStatement.setString(8, contact.getWebSite());
            preparedStatement.setString(9, contact.getEmail());
            preparedStatement.setString(10, contact.getJobCurrent());
            preparedStatement.setString(11, contact.getPhoto());
            preparedStatement.setString(12, contact.getAddress().getCountry());
            preparedStatement.setString(13, contact.getAddress().getCity());
            preparedStatement.setString(14, contact.getAddress().getStreet());
            preparedStatement.setString(15, contact.getAddress().getHouse());
            preparedStatement.setString(16, contact.getAddress().getFlat());
            preparedStatement.setString(17, contact.getAddress().getZipCode());
            preparedStatement.setInt(18, contact.getId());
            log.debug(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to update contact", e);
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    @Override
    public void deleteContacts(String contacts) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            String sql = "DELETE FROM contacts WHERE contact_id IN (" + contacts + ")";
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            log.error("Unable to remove contacts", e);
        } finally {
            close(connection, statement, null);
        }
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