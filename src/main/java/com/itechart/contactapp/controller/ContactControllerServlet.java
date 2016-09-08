package com.itechart.contactapp.controller;

import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.dao.ContactDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

public class ContactControllerServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ContactControllerServlet.class);
    private ContactDAO contactDAO;

    @Resource(name="jdbc/dmitry_kach_db")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            contactDAO = new ContactDAO(dataSource);
        } catch (Exception e) {
            log.error(e);
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            listContacts(req, resp);
        } catch (Exception e) {
            log.error(e);
            throw new ServletException(e);
        }
    }

    private void listContacts(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Contact> contacts = contactDAO.getContacts();

        req.setAttribute("CONTACT_LIST", contacts);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/list-contacts.jsp");
        dispatcher.forward(req, resp);
    }
}
