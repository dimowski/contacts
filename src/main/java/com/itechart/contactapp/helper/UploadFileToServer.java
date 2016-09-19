package com.itechart.contactapp.helper;

import com.itechart.contactapp.model.Attachment;
import com.itechart.contactapp.model.Contact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Properties;

public class UploadFileToServer implements FileUploader {

    private static final Logger log = LogManager.getLogger(UploadFileToServer.class);
    private Properties properties;

    public UploadFileToServer(Properties theProperties) {
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
            attachment = new Attachment(fileName, new Date(), request.getParameter("comments"), ((Contact) request.getSession().getAttribute("CONTACT")).getId());
            InputStream fileContentStream = filePart.getInputStream();
            File repository = new File(properties.getProperty("users.attachments"));
            if (!repository.exists())
                repository.mkdir();
            log.debug("UPLOAD LOCATION = {}, FILE NAME = {}", properties.getProperty("users.attachments"), fileName);

            File newAttachment = new File(repository, fileName);
            Files.copy(fileContentStream, newAttachment.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error(e);
        }
        return attachment;
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
            if (!repository.exists())
                repository.mkdir();
            log.debug("UPLOAD LOCATION = {}, FILE NAME = {}", properties.getProperty("users.photo"), fileName);

            File newPhoto = new File(repository, fileName);
            Files.copy(fileContentStream, newPhoto.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error(e);
        }
        return fileName;
    }

    @Override
    public void downloadProfilePhoto(String photo, HttpServletResponse response) {
        log.debug("Requested file = {}", photo);

        try {
            byte[] image = Files.readAllBytes(Paths.get(photo));
            response.setContentType("image/jpg");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(image);
            out.close();
        } catch (IOException e) {
            log.error(e);
        }
    }
}
