package com.itechart.contactapp.command;

import com.itechart.contactapp.dao.ContactDAO;
import com.itechart.contactapp.dao.ContactDAOFactory;
import com.itechart.contactapp.helper.Paginator;
import com.itechart.contactapp.model.Contact;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Map;

public class ShowContactsCommand implements Command {

    private ContactDAO contactDAO;

    public ShowContactsCommand(DataSource dataSource) {
        contactDAO = ContactDAOFactory.getContactDAO(dataSource);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
        int contactsCount = contactDAO.getContactsCount();
        int pagesCount = (int) Math.ceil(contactsCount / 10.0);

        request.setAttribute("CONTACTS_COUNT", contactsCount);
        request.setAttribute("PAGINATOR", Paginator.getPaginator(targetPage, pagesCount));
        request.getSession().setAttribute("CURRENT_PAGE", targetPage);
        request.getSession().setAttribute("CONTACT_LIST", contacts);
        return "/list-contact.jsp";
    }
}