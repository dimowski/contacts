package com.itechart.contactapp.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileManager {

    /**
     * Returns the String which is the representation of the path to the uploaded file. If file is not uploaded,
     * then null is returned.
     *
     * @param request
     * @param response
     * @return a {@code String} that contains the path to the uploaded file.
     */
    String[] uploadAttachment(HttpServletRequest request, HttpServletResponse response, int dir);

    String uploadProfilePhoto(HttpServletRequest request, HttpServletResponse response, String oldPhoto);

    void removeAttachment(int dir, String fileName);

    void removeAllAttachments(int dir);
}
