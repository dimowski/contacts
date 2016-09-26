package com.itechart.contactapp.dao;

import com.itechart.contactapp.model.Phone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDAOUtil implements PhoneDAO {

    private static final Logger log = LogManager.getLogger(PhoneDAOUtil.class);
    private DataSource dataSource;

    public PhoneDAOUtil(DataSource theDataSource) {
        dataSource = theDataSource;
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
            ContactDAOUtil.close(connection, statement, resultSet);
        }
        log.info(phones.size() + " phones retrieved");
        return phones;
    }
}
