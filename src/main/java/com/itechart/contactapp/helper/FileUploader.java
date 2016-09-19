package com.itechart.contactapp.helper;

import com.itechart.contactapp.model.Attachment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileUploader {

    /**
     * Returns the String which is the representation of the path to the uploaded file. If file is not uploaded,
     * then null is returned.
     *
     * @param request
     * @param response
     * @return a {@code String} that contains the path to the uploaded file.
     */
    Attachment uploadAttachment(HttpServletRequest request, HttpServletResponse response);

    String uploadProfilePhoto(HttpServletRequest request, HttpServletResponse response);

    void downloadProfilePhoto(String photo, HttpServletResponse response);
}
