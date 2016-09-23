package com.itechart.contactapp.helper;

import com.itechart.contactapp.model.Attachment;
import com.itechart.contactapp.model.Contact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Properties;

public class FileManagerUtil implements FileManager {

    private static final Logger log = LogManager.getLogger(FileManagerUtil.class);
    private Properties properties;

    public FileManagerUtil(Properties theProperties) {
        properties = theProperties;
    }

    @Override
    public Attachment uploadAttachment(HttpServletRequest request, HttpServletResponse response) {
        Attachment attachment = null;
        try {
            Part filePart = request.getPart("attachment");
            String fileName = filePart.getSubmittedFileName();
            if (fileName.equals(""))
                return null;
            int contactId = ((Contact) request.getSession().getAttribute("CONTACT")).getId();
            attachment = new Attachment(fileName, new Date(), request.getParameter("comments"), contactId);
            InputStream fileContentStream = filePart.getInputStream();
            log.debug("UPLOAD LOCATION = {}, FILE NAME = {}", properties.getProperty("users.attachments"), fileName);
            File repository = new File(properties.getProperty("users.attachments"));
            if (!repository.exists()) {
                repository.mkdirs();
            }
            File newAttachment = new File(repository, fileName);
            Files.copy(fileContentStream, newAttachment.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error(e);
        }
        return attachment;
    }

    @Override
    public void removeAttachment(HttpServletRequest request, HttpServletResponse response) {
        String fileName = properties.getProperty("users.attachments") + "/" + request.getParameter("fileName");
        log.debug("{} WILL BE REMOVED!", fileName);
        File deleteFile = new File(fileName);
        if (deleteFile.exists())
            deleteFile.delete();
    }

    @Override
    public String uploadProfilePhoto(HttpServletRequest request, HttpServletResponse response) {
        String fileName = null;
        try {
            Part filePart = request.getPart("profilePhoto");
            fileName = filePart.getSubmittedFileName();
            if (fileName.equals(""))
                return null;
            InputStream fileContentStream = filePart.getInputStream();
            File repository = new File(properties.getProperty("users.photo"));
            if (!repository.exists()) {
                repository.mkdirs();
            }
            log.debug("FILE NAME = {}", fileName);

            File newPhoto = new File(repository, fileName);
            Files.copy(fileContentStream, newPhoto.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error(e);
        }
        return fileName;
    }
}
