package com.itechart.contactapp.helper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class FileManagerUtil implements FileManager {

    private static final Logger log = LogManager.getLogger(FileManagerUtil.class);
    private Properties properties;

    public FileManagerUtil(Properties theProperties) {
        properties = theProperties;
    }

    @Override
    public String[] uploadAttachment(HttpServletRequest request, HttpServletResponse response, int dir) {
        String[] fullFileName = null;
        try {
            List<Part> fileParts = request.getParts().stream().filter(part -> "attachment".equals(part.getName())).collect(Collectors.toList());

            int i = 0;
            fullFileName = new String[fileParts.size()];
            log.debug("FILES COUNT {}", fileParts.size());
            for (Part filePart : fileParts) {
                fullFileName[i] = filePart.getSubmittedFileName();
                InputStream fileContent = filePart.getInputStream();
                if (StringUtils.isEmpty(fullFileName[i]))
                    return null;
                log.debug("UPLOAD LOCATION = {}, FILE NAME = {}", properties.getProperty("users.attachments"), fullFileName[i]);
                File repository = new File(properties.getProperty("users.attachments") + "/" + dir);
                if (!repository.exists()) {
                    repository.mkdirs();
                }
                String[] tokens = fullFileName[i].split("\\.(?=[^\\.]+$)");
                File newAttachment = new File(repository, fullFileName[i]);
                for (int j = 2; newAttachment.exists(); j++) {
                    log.debug("CHECK IF EXISTS: {}", newAttachment.getName());
                    newAttachment = new File(repository, tokens[0] + "(" + j + ")." + tokens[1]);
                }
                fullFileName[i] = newAttachment.getName();
                Files.copy(fileContent, newAttachment.toPath(), StandardCopyOption.REPLACE_EXISTING);
                i++;
            }
        } catch (Exception e) {
            log.error(e);
        }
        return fullFileName;
    }

    @Override
    public void removeAttachment(int dir, String fileName) {
        String path = properties.getProperty("users.attachments") + "/" + dir + "/" + fileName;
        log.debug("Removing file {}", path);
        File deleteFile = new File(path);
        if (deleteFile.exists())
            deleteFile.delete();
    }

    @Override
    public void removeAllAttachments(int dir) {
        String path = properties.getProperty("users.attachments") + "/" + dir;
        File deleteFile = new File(path);
        if (deleteFile.exists()) {
            log.debug("Removing file {}", path);
            FileUtils.deleteQuietly(deleteFile);
        }
    }

    @Override
    public String uploadProfilePhoto(HttpServletRequest request, HttpServletResponse response, String oldPhoto, int id) {
        String fileName = null;
        try {
            Part filePart = request.getPart("profilePhoto");
            fileName = filePart.getSubmittedFileName();
            boolean deletePhoto = request.getParameter("deletedPhoto").equals("1");
            if (StringUtils.isEmpty(fileName) && !deletePhoto)
                return oldPhoto;
            if (StringUtils.isEmpty(fileName) && deletePhoto) {
                String path = properties.getProperty("users.photo") + "/" + oldPhoto;
                log.debug("Removing old photo {}", path);
                File deleteFile = new File(path);
                if (deleteFile.exists())
                    deleteFile.delete();
                return null;
            }

            InputStream fileContentStream = filePart.getInputStream();
            File repository = new File(properties.getProperty("users.photo"));
            if (!repository.exists()) {
                repository.mkdirs();
            }
            log.debug("Photo filename = {}", fileName);

            String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
            File newPhoto = new File(repository, "user_id_" + id + fileExtension);
            fileName = newPhoto.getName();
            Files.copy(fileContentStream, newPhoto.toPath(), StandardCopyOption.REPLACE_EXISTING);

            log.debug("OldExtension {}, NewExtension {}", oldPhoto, fileExtension);
            if (oldPhoto != null && !oldPhoto.equals(newPhoto.getName())) {
                String path = properties.getProperty("users.photo") + "/" + oldPhoto;
                log.debug("Removing old photo {}", path);
                File deleteFile = new File(path);
                if (deleteFile.exists())
                    deleteFile.delete();
            }
        } catch (Exception e) {
            log.error(e);
        }
        return fileName;
    }

    @Override
    public void deleteProfilePhoto(int id) {
        String path = properties.getProperty("users.photo");
        File[] files = new File(path + "/").listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().contains("user_id_" + id)) {
                    log.debug("Deleting photo: {}", file.getName());
                    file.delete();
                }
            }
        }
    }
}