package com.itechart.contactapp.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class ReturnFileServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ReturnFileServlet.class);
    private static Properties properties;

    @Override
    public void init() throws ServletException {
        properties = (Properties) getServletConfig().getServletContext().getAttribute("contactapp.properties");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestedFile = properties.getProperty("users.attachments") + "/" + request.getParameter("fileName");
        String fileExtension = requestedFile.substring(requestedFile.lastIndexOf('.'));
        log.debug("Requested file = " + requestedFile);

        response.setContentType(fileExtension);

        response.setHeader("Content-disposition", "attachment; filename=" + requestedFile);

        File theFile = new File(requestedFile);

        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(theFile);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > -1) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }
}