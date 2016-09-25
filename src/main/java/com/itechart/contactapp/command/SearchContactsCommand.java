package com.itechart.contactapp.command;

import com.itechart.contactapp.dao.ContactDAO;
import com.itechart.contactapp.dao.ContactDAOFactory;
import com.itechart.contactapp.model.Contact;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchContactsCommand implements Command {

    private final static Logger log = LogManager.getLogger(SearchContactsCommand.class);
    private List<String> parametersList = Arrays.asList("first_name", "last_name", "middle_name", "gender", "status",
            "citizenship", "country", "city", "street", "house", "flat", "birthSince", "birthUpto");
    private ContactDAO contactDAO;

    public SearchContactsCommand(DataSource dataSource) {
        contactDAO = ContactDAOFactory.getContactDAO(dataSource);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = new HashMap<>();
        for (String param: parametersList) {
            String value = request.getParameter(param);
            if (StringUtils.isNotEmpty(value)) {
                params.put(param, value);
            }
        }
        Map<Integer, Contact> contacts = contactDAO.searchContacts(params);
        log.debug("Found {} contacts", contacts.size());
        int contactsCount = contacts.size();

        request.setAttribute("CONTACTS_COUNT", contactsCount);
        request.getSession().setAttribute("CONTACT_LIST", contacts);
        return "/list-contact.jsp";
    }
}
