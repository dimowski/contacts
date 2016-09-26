package com.itechart.contactapp.dao;

import com.itechart.contactapp.model.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttachmentDAOUtil implements AttachmentDAO {

    private DataSource dataSource;
    private static final Logger log = LogManager.getLogger(AttachmentDAOUtil.class);

    public AttachmentDAOUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createAttachment(Attachment attachment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String sql = "INSERT INTO attachment (contact_id, filename, upload_date, comments) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, attachment.getContactId());
            preparedStatement.setString(2, attachment.getFilename());
            preparedStatement.setTimestamp(3, new Timestamp(attachment.getUploadDate().getTime()));
            preparedStatement.setString(4, attachment.getComments());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            ContactDAOUtil.close(connection, preparedStatement, null);
        }
    }

    @Override
    public void removeAttachment(int contactId, String fileName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String sql = "DELETE FROM attachment WHERE contact_id=? AND filename=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, contactId);
            preparedStatement.setString(2, fileName);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            ContactDAOUtil.close(connection, preparedStatement, null);
        }
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
                Date uploadDate = new Date(resultSet.getTimestamp("upload_date").getTime());
                String comments = resultSet.getString("comments");
                log.debug(uploadDate);
                Attachment tempAttachment = new Attachment(id, filename, uploadDate, comments);
                attachments.add(tempAttachment);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            ContactDAOUtil.close(connection, statement, resultSet);
        }
        return attachments;
    }
}
