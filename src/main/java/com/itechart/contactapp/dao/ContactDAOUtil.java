package com.itechart.contactapp.dao;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.CustomSql;
import com.healthmarketscience.sqlbuilder.JdbcEscape;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.itechart.contactapp.helper.FileManager;
import com.itechart.contactapp.helper.FileManagerUtil;
import com.itechart.contactapp.model.Address;
import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.model.Phone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class ContactDAOUtil implements ContactDAO {

    private static final Logger log = LogManager.getLogger(ContactDAOUtil.class);
    private DataSource dataSource;

    public ContactDAOUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    @Override
    public Map<Integer, Contact> searchContacts(Map<String, String> params) {
        SelectQuery sql = new SelectQuery();
        sql.addAllColumns().addCustomFromTable("contacts");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String param = entry.getKey();
                switch (param) {
                    case "birthSince":
                        sql.addCondition(BinaryCondition.greaterThan(new CustomSql("birthday"), JdbcEscape.date(format.parse(entry.getValue())), true));
                        break;
                    case "birthUpto":
                        sql.addCondition(BinaryCondition.lessThan(new CustomSql("birthday"), JdbcEscape.date(format.parse(entry.getValue())), true));
                        break;
                    default:
                        String value = "%" + entry.getValue() + "%";
                        sql.addCondition(BinaryCondition.like(new CustomSql(param), value));
                        break;
                }
            }
        } catch (ParseException e) {
            log.error("Unable to parse date", e);
        }
        String query = sql.validate().toString();
        log.debug("Search sql query is: \"{}\"", query);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Map<Integer, Contact> searchResult = new HashMap<>();
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
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
                String photo = resultSet.getString("photo");
                String jobCurrent = resultSet.getString("job_current");

                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String house = resultSet.getString("house");
                String flat = resultSet.getString("flat");
                String zipCode = resultSet.getString("zip_code");
                Address address = new Address(country, city, street, house, flat, zipCode);

                Contact theContact = new Contact(contactId, firstName, lastName, middleName, birthday, status, gender,
                        citizenship, webSite, email, jobCurrent, address, photo);
                searchResult.put(theContact.getId(), theContact);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            close(connection, statement, resultSet);
        }
        return searchResult;
    }

    @Override
    public List<Contact> getContactsByTodayBirthday() {
        List<Contact> contacts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String sql = "SELECT contact_id, first_name, last_name, middle_name FROM contacts WHERE " +
                    "MONTH(birthday) = MONTH(NOW()) AND DAY(birthday) = DAY(NOW());";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("contact_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String middleName = resultSet.getString("middle_name");
                Contact tempContact = new Contact(id, firstName, lastName, middleName);
                contacts.add(tempContact);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            close(connection, statement, resultSet);
        }
        return contacts;
    }

    @Override
    public Map<Integer, Contact> getContacts(int page) {
        Map<Integer, Contact> contacts = new HashMap<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
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
        } catch (SQLException e) {
            log.error("Unable to get contact list", e);
        } finally {
            close(connection, statement, resultSet);
        }
        return contacts;
    }

    @Override
    public int createContact(Contact contact) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int contactId = 0;

        try {
            connection = dataSource.getConnection();
            String sql1 = "INSERT INTO contacts (first_name, last_name, middle_name, birthday, status, gender," +
                    "citizenship, web_site, email, job_current, photo, country, city, street, house, flat, zip_code)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
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

            preparedStatement.executeUpdate();

            ResultSet contactKey = preparedStatement.getGeneratedKeys();
            if (contactKey.next()) {
                contactId = contactKey.getInt(1);
                contact.setId(contactId);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            if (contact.getPhones() != null) {
                createPhones(connection, contact.getPhones(), contactId);
            }
        } catch (SQLException e) {
            log.error("Unable to create contact", e);
        } finally {
            close(connection, preparedStatement, null);
        }
        return contactId;
    }

    @Override
    public void updateContact(Contact contact, int[] phoneIdForDelete) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String sql1 = "UPDATE contacts SET first_name=?, last_name=?, middle_name=?, birthday=?, status=?, gender=?," +
                    "citizenship=?, web_site=?, email=?, job_current=?, photo=?, country=?, city=?, street=?, house=?," +
                    "flat=?, zip_code=? WHERE contact_id=?";
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
            preparedStatement.setInt(18, contact.getId());

            preparedStatement.executeUpdate();

            List<Phone> phoneList = contact.getPhones();
            //Checks, what phones must be created, or updated
            if (phoneList != null) {
                List<Phone> phonesForUpdate = new ArrayList<>();
                List<Phone> phonesForCreate = new ArrayList<>();
                for (Phone tempPhone : phoneList) {
                    if (tempPhone.getId() != 0) {
                        phonesForUpdate.add(tempPhone);
                    } else {
                        phonesForCreate.add(tempPhone);
                    }
                }
                if (phonesForUpdate.size() > 0) {
                    log.info("Number of phones for update: {}", phonesForUpdate.size());
                    String sql = "UPDATE phone SET country_code=?, operator_code=?, phone_number=?, phone_type=?," +
                            "comments=? WHERE phone_id=?";
                    preparedStatement = connection.prepareStatement(sql);
                    connection.setAutoCommit(false);
                    for (Phone tempPhone : phonesForUpdate) {
                        preparedStatement.setString(1, tempPhone.getCountryCode());
                        preparedStatement.setString(2, tempPhone.getOperatorCode());
                        preparedStatement.setString(3, tempPhone.getPhoneNumber());
                        preparedStatement.setString(4, tempPhone.getPhoneType());
                        preparedStatement.setString(5, tempPhone.getComments());
                        preparedStatement.setInt(6, tempPhone.getId());
                        preparedStatement.addBatch();
                    }
                    preparedStatement.executeBatch();
                    connection.commit();
                }
                if (phonesForCreate.size() > 0) {
                    log.info("Number of phones for create: {}", phonesForCreate.size());
                    createPhones(connection, phonesForCreate, contact.getId());
                }
            }
            if (phoneIdForDelete != null && phoneIdForDelete.length > 0) {
                log.info("Number of phones for deleting: {}", phoneIdForDelete.length);
                String sql = "DELETE FROM phone WHERE phone_id=?";
                preparedStatement = connection.prepareStatement(sql);
                connection.setAutoCommit(false);
                for (int id : phoneIdForDelete) {
                    preparedStatement.setInt(1, id);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
            }
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
            log.info("There are {} contacts in DB", result);
        } catch (SQLException e) {
            log.error("Unable to get contacts count", e);
        } finally {
            close(connection, statement, resultSet);
        }
        return result;
    }

    private void createPhones(Connection connection, List<Phone> phoneList, int contactId) throws SQLException {
        String sql = "INSERT INTO phone (contact_id, country_code, operator_code, phone_number, phone_type," +
                "comments) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        connection.setAutoCommit(false);

        for (Phone tempPhone : phoneList) {
            statement.setInt(1, contactId);
            statement.setString(2, tempPhone.getCountryCode());
            statement.setString(3, tempPhone.getOperatorCode());
            statement.setString(4, tempPhone.getPhoneNumber());
            statement.setString(5, tempPhone.getPhoneType());
            statement.setString(6, tempPhone.getComments());
            statement.addBatch();
        }
        statement.executeBatch();
        connection.commit();
        statement.close();
    }

    @Override
    public void setProfilePhoto(String photo, int contactId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE contacts SET photo=? WHERE contact_id=?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, photo);
            statement.setInt(2, contactId);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            close(connection, statement, null);
        }
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
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