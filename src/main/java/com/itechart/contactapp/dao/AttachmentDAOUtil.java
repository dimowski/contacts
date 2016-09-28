package com.itechart.contactapp.dao;

import com.itechart.contactapp.model.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AttachmentDAOUtil implements AttachmentDAO {

    private DataSource dataSource;
    private static final Logger log = LogManager.getLogger(AttachmentDAOUtil.class);

    public AttachmentDAOUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createAttachments(List<Attachment> attachments) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String sql = "INSERT INTO attachment (contact_id, filename, upload_date, comments) VALUES (?, ?, ?, ?)";
            Iterator<Attachment> iterator = attachments.iterator();
            iterator.next();
            while (iterator.hasNext()) {
                sql += ",(?, ?, ?, ?)";
                iterator.next();
            }
            log.debug(sql);
            preparedStatement = connection.prepareStatement(sql);
            int i = 0;
            for (Attachment attachment : attachments) {
                preparedStatement.setInt(i + 1, attachment.getContactId());
                preparedStatement.setString(i + 2, attachment.getFilename());
                preparedStatement.setTimestamp(i + 3, new Timestamp(attachment.getUploadDate().getTime()));
                preparedStatement.setString(i + 4, attachment.getComments());
                i += 4;
            }
            preparedStatement.execute();
        } catch (
                SQLException e) {
            log.error(e);
        } finally {
            ContactDAOUtil.close(connection, preparedStatement, null);
        }
    }

    @Override
    public String removeAttachment(int id) {
        String fileName = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            String sql1 = "SELECT filename FROM attachment WHERE attachment_id=?";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            fileName = resultSet.getString("filename");

            String sql2 = "DELETE FROM attachment WHERE attachment_id=?";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            ContactDAOUtil.close(connection, preparedStatement, resultSet);
        }
        return fileName;
    }

    @Override
    public void updateAttachments(List<Attachment> attachments) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String sql = "UPDATE attachment SET comments=? WHERE attachment_id=?";
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (Attachment attachment : attachments) {
                preparedStatement.setString(1, attachment.getComments());
                preparedStatement.setInt(2, attachment.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (
                SQLException e) {
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
