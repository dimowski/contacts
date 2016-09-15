package com.itechart.contactapp.controller;

import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.service.ContactService;
import com.itechart.contactapp.service.ContactServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

public class ContactControllerServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ContactControllerServlet.class);

    private ContactService contactService;

    @Resource(name = "jdbc/dmitry_kach_db")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        log.info("initialized");
        contactService = ContactServiceFactory.getContactService(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String theCommand = request.getParameter("command");
        log.info("command ={}", theCommand);
        if (theCommand == null) {
            theCommand = "list";
        }
        switch (theCommand) {
            case "list":
                contactService.listContacts(request, response);
                break;
            case "edit":
                contactService.fillContact(request, response);
                break;
            case "saveContact":
                contactService.saveContact(request, response);
                break;
            case "deleteContact":
                contactService.deleteContact(request, response);
                break;
            default:
                break;
        }
    }
}