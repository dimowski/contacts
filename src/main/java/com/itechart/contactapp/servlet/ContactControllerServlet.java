package com.itechart.contactapp.servlet;

import com.itechart.contactapp.service.ContactService;
import com.itechart.contactapp.service.ContactServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@MultipartConfig
public class ContactControllerServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ContactControllerServlet.class);

    private ContactService contactService;

    @Resource(name = "jdbc/dmitry_kach_db")
    private DataSource dataSource;
    private static Properties properties;

    @Override
    public void init() throws ServletException {
        properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("contactapp.properties");
        log.info("initialized");
        try {
            properties.load(input);
        } catch (IOException e) {
            log.error("Unable to initialize properties file", e);
        }
        getServletConfig().getServletContext().setAttribute("contactapp.properties", properties);
        contactService = ContactServiceFactory.getContactService(dataSource, properties);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String theCommand = request.getParameter("command");
        log.info("Method GET, command = {}", theCommand);
        if (theCommand == null) {
            theCommand = "list";
        }
        switch (theCommand) {
            case "list":
                contactService.listContacts(request, response);
                break;
            case "editContact":
                contactService.fillContact(request, response);
                break;
            case "deleteContact":
                contactService.deleteContact(request, response);
                break;
            case "createContact":
                contactService.createContact(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String theCommand = request.getParameter("command");
        log.info("Method POST, command = {}", theCommand);
        switch (theCommand) {
            case "saveContact":
                contactService.saveContact(request, response);
                break;
            case "addAttachment":
                contactService.addAttachment(request, response);
                break;
        }
    }
}