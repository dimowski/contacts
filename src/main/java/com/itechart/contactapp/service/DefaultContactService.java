package com.itechart.contactapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultContactService implements ContactService {

    private static final Logger log = LogManager.getLogger(DefaultContactService.class);

    public DefaultContactService() {
    }

    @Override
    public void showContactsList(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-contact.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void deleteContact(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("main?targetPage=" + request.getSession().getAttribute("CURRENT_PAGE"));
        } catch (IOException e) {
            log.error("Unable to redirect to main page", e);
        }
    }

    @Override
    public void editContact(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-contact.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void saveContact(HttpServletRequest request, HttpServletResponse response) {
        showContactsList(request, response);
    }

    @Override
    public void addAttachment(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("main?command=editContact&contactId=" /*+ contactId*/);
        } catch (IOException e) {
            log.error("Unable to redirect to page", e);
        }
    }

    @Override
    public void removeAttachment(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("main?command=editContact&contactId="/* + contactId*/);
        } catch (IOException e) {
            log.error("Unable to redirect to page", e);
        }
    }

    @Override
    public void createContact(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-contact.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public int getContactsCount() {
        return 0; /*contactDAO.getContactsCount();*/
    }
}
