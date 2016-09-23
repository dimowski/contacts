package com.itechart.contactapp.command;

import com.itechart.contactapp.dao.ContactDAO;
import com.itechart.contactapp.dao.ContactDAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class DeleteContactCommand implements Command {

    private static final Logger log = LogManager.getLogger(DeleteContactCommand.class);

    private ContactDAO contactDAO;

    public DeleteContactCommand(DataSource dataSource) {
        contactDAO = ContactDAOFactory.getContactDAO(dataSource);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String contactsForDel = request.getParameter("items");
        log.debug("Contacts for removing: {}", contactsForDel);
        contactDAO.deleteContacts(contactsForDel);

        return "main?targetPage=" + request.getSession().getAttribute("CURRENT_PAGE");
    }
}
