package com.itechart.contactapp.command;

import com.itechart.contactapp.dao.AttachmentDAO;
import com.itechart.contactapp.dao.AttachmentDAOFactory;
import com.itechart.contactapp.helper.FileManager;
import com.itechart.contactapp.helper.FileManagerUtil;
import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.servlet.ContactControllerServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class RemoveAttachmentCommand implements Command {

    private AttachmentDAO attachmentDAO;

    public RemoveAttachmentCommand(DataSource dataSource) {
        attachmentDAO = AttachmentDAOFactory.getAttachmentDAO(dataSource);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        FileManager fileManager = new FileManagerUtil(ContactControllerServlet.properties);
        fileManager.removeAttachment(request, response);

        String fileName = request.getParameter("fileName");
        int contactId = ((Contact) request.getSession().getAttribute("CONTACT")).getId();
        attachmentDAO.removeAttachment(contactId, fileName);
        return "main?command=editContact&contactId=" + contactId;
    }
}
