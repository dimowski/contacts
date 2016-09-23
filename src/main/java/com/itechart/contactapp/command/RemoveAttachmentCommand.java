package com.itechart.contactapp.command;

import com.itechart.contactapp.dao.ContactDAO;
import com.itechart.contactapp.dao.ContactDAOFactory;
import com.itechart.contactapp.helper.FileManager;
import com.itechart.contactapp.helper.FileManagerUtil;
import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.servlet.ContactControllerServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class RemoveAttachmentCommand implements Command {

    private static final Logger log = LogManager.getLogger(RemoveAttachmentCommand.class);

    private ContactDAO contactDAO;

    public RemoveAttachmentCommand(DataSource dataSource) {
        contactDAO = ContactDAOFactory.getContactDAO(dataSource);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        FileManager fileManager = new FileManagerUtil(ContactControllerServlet.properties);
        fileManager.removeAttachment(request, response);

        String fileName = request.getParameter("fileName");
        int contactId = ((Contact) request.getSession().getAttribute("CONTACT")).getId();
        contactDAO.removeAttachment(contactId, fileName);
        return "main?command=editContact&contactId=" + contactId;
    }
}
