package com.itechart.contactapp.service;

import com.itechart.contactapp.dao.ContactDAO;
import com.itechart.contactapp.dao.ContactDAOFactory;
import com.itechart.contactapp.model.Contact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class DefaultContactService implements ContactService {

    private static final Logger log = LogManager.getLogger(DefaultContactService.class);

    private ContactDAO contactDAO;

    public DefaultContactService(DataSource theDataSource) {
        contactDAO = ContactDAOFactory.getContactDAO(theDataSource);
    }

    @Override
    public void listContacts(HttpServletRequest request, HttpServletResponse response) {
        int targetPage = 1;
        String targetPageParam = request.getParameter("targetPage");
        if (targetPageParam != null) {
            switch (targetPageParam) {
                case "next":
                    targetPage = ((int) request.getSession().getAttribute("CURRENT_PAGE")) + 1;
                    break;
                case "prev":
                    targetPage = ((int) request.getSession().getAttribute("CURRENT_PAGE") - 1);
                    break;
                default:
                    targetPage = Integer.parseInt(targetPageParam);
                    break;
            }
        }
        Map<Integer, Contact> contacts = contactDAO.getContacts(targetPage);
        int contactsCount = getContactsCount();
        int pagesCount = (int) Math.ceil(contactsCount / 10.0);

        request.setAttribute("CONTACTS_COUNT", contactsCount);
        request.setAttribute("PAGES_COUNT", pagesCount);
        request.getSession().setAttribute("CURRENT_PAGE", targetPage);
        request.getSession().setAttribute("CONTACT_LIST", contacts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-contact.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void deleteContact(HttpServletRequest request, HttpServletResponse response) {
        String contactsForDel = request.getParameter("items");
        log.debug("Contacts for removing: {}", contactsForDel);
        contactDAO.deleteContacts(contactsForDel);
        try {
            response.sendRedirect("main?targetPage=" + request.getSession().getAttribute("CURRENT_PAGE"));
        } catch (IOException e) {
            log.error("Unable to redirect to main page", e);
        }
    }

    @Override
    public void fillContact(HttpServletRequest request, HttpServletResponse response) {
        int contactId = Integer.parseInt(request.getParameter("contactId"));
        log.debug("contactId = {}", contactId);

        Map<Integer, Contact> tempList = (Map<Integer, Contact>) request.getSession().getAttribute("CONTACT_LIST");

        Contact theContact = tempList.get(contactId);
        log.debug(theContact);
        theContact.setPhones(contactDAO.getPhonesByContactId(contactId));
        theContact.setAttachments(contactDAO.getAttachmentsByContactId(contactId));

        request.getSession().setAttribute("CONTACT", theContact);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-contact.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void saveContact(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String middleName = request.getParameter("middleName");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = format.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            log.warn("Empty birthday field will set to NULL");
        }
        String gender = request.getParameter("gender");
        String citizenship = request.getParameter("citizenship");
        String status = request.getParameter("status");
        String email = request.getParameter("email");
        String jobCurrent = request.getParameter("jobCurrent");

        String country = request.getParameter("county");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String house = request.getParameter("house");
        String flat = request.getParameter("flat");
        String zipCode = request.getParameter("zipCode");

        //String phoneType

        //Checks if contact needs to be updated or created new
        if (request.getSession().getAttribute("CONTACT") != null) {

        }
    }

    @Override
    public void addAttachment(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void createContact(HttpServletRequest request, HttpServletResponse response) {
        //Removes attribute to display empty edit-contact.jsp page
        if (request.getSession().getAttribute("CONTACT") != null) {
            request.getSession().removeAttribute("CONTACT");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-contact.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public int getContactsCount() {
        return contactDAO.getContactsCount();
    }
}
