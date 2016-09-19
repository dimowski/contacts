package com.itechart.contactapp.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ReturnPhotoServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ReturnPhotoServlet.class);
    private static Properties properties;

    @Override
    public void init() throws ServletException {
        properties = (Properties) getServletConfig().getServletContext().getAttribute("contactapp.properties");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestedFile = properties.getProperty("users.photo") + request.getPathInfo();
        log.debug("Requested file = " + requestedFile);

        byte[] image = Files.readAllBytes(Paths.get(requestedFile));
        response.setContentType("image/jpg");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(image);
        out.close();
    }
}