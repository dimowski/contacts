package com.itechart.contactapp.command;

import com.itechart.contactapp.dao.ContactDAO;
import com.itechart.contactapp.dao.ContactDAOFactory;
import com.itechart.contactapp.helper.FileManager;
import com.itechart.contactapp.helper.FileManagerUtil;
import com.itechart.contactapp.model.Attachment;
import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.servlet.ContactControllerServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class AddAttachmentCommand implements Command {

    private static final Logger log = LogManager.getLogger(AddAttachmentCommand.class);

    private ContactDAO contactDAO;

    public AddAttachmentCommand(DataSource dataSource) {
        contactDAO = ContactDAOFactory.getContactDAO(dataSource);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Adding attachment");
        FileManager fileManager = new FileManagerUtil(ContactControllerServlet.properties);
        Attachment attachment = fileManager.uploadAttachment(request, response);

        if (attachment != null)
            contactDAO.createAttachment(attachment);
        int contactId = ((Contact) request.getSession().getAttribute("CONTACT")).getId();
        return "main?command=editContact&contactId=" + contactId;
    }
}
