package com.itechart.contactapp.command;

import com.itechart.contactapp.dao.AttachmentDAO;
import com.itechart.contactapp.dao.AttachmentDAOFactory;
import com.itechart.contactapp.dao.ContactDAO;
import com.itechart.contactapp.dao.ContactDAOFactory;
import com.itechart.contactapp.helper.FileManager;
import com.itechart.contactapp.helper.FileManagerUtil;
import com.itechart.contactapp.model.Address;
import com.itechart.contactapp.model.Attachment;
import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.model.Phone;
import com.itechart.contactapp.servlet.ContactControllerServlet;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SaveContactCommand implements Command {

    private static final Logger log = LogManager.getLogger(SaveContactCommand.class);

    private ContactDAO contactDAO;
    private AttachmentDAO attachmentDAO;

    public SaveContactCommand(DataSource dataSource) {
        contactDAO = ContactDAOFactory.getContactDAO(dataSource);
        attachmentDAO = AttachmentDAOFactory.getAttachmentDAO(dataSource);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        FileManager fileManager = new FileManagerUtil(ContactControllerServlet.properties);

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String middleName = request.getParameter("middleName");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = format.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            log.warn("Empty field birthday will set to NULL");
        }
        String gender = request.getParameter("gender");
        String citizenship = request.getParameter("citizenship");
        String webSite = request.getParameter("webSite");
        String status = request.getParameter("status");
        String email = request.getParameter("email");
        String jobCurrent = request.getParameter("jobCurrent");

        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String house = request.getParameter("house");
        String flat = request.getParameter("flat");
        String zipCode = request.getParameter("zipCode");
        Address theAddress = new Address(country, city, street, house, flat, zipCode);

        //Parse phones
        List<Phone> phoneList = null;
        String[] phoneId = request.getParameterValues("phoneId");
        if (phoneId != null) {
            phoneList = new ArrayList<>(phoneId.length);
            String[] phoneType = request.getParameterValues("phoneType");
            String[] countryCode = request.getParameterValues("countryCode");
            String[] operatorCode = request.getParameterValues("operatorCode");
            String[] phoneNumber = request.getParameterValues("phoneNumber");
            String[] comments = request.getParameterValues("phoneComments");
            for (int i = 0; i < phoneId.length; i++) {
                Phone tempPhone = new Phone(Integer.parseInt(phoneId[i]), countryCode[i], operatorCode[i], phoneNumber[i], phoneType[i], comments[i]);
                phoneList.add(tempPhone);
            }
        }

        int[] phoneIdForDelete = new int[0];
        if (StringUtils.isNotEmpty(request.getParameter("idsForDel"))) {
            String[] strPhoneIdForDel = request.getParameter("idsForDel").split("/");
            log.debug("REMOVING {} PHONES", strPhoneIdForDel.length);
            phoneIdForDelete = Arrays.stream(strPhoneIdForDel).mapToInt(Integer::parseInt).toArray();
        }

        Contact theContact = new Contact(firstName, lastName, middleName, birthday, status, gender, citizenship,
                webSite, email, jobCurrent, theAddress, phoneList);

        Contact oldContact = (Contact) request.getSession().getAttribute("CONTACT");
        int contactId;
        String photo;
        if (oldContact != null) {
            contactId = oldContact.getId();
            theContact.setId(contactId);
            photo = fileManager.uploadProfilePhoto(request, response, oldContact.getPhoto(), contactId);
            theContact.setPhoto(photo);
            contactDAO.updateContact(theContact, phoneIdForDelete);
        } else {
            contactId = contactDAO.createContact(theContact);
            photo = fileManager.uploadProfilePhoto(request, response, null, contactId);
            theContact.setPhoto(photo);
            contactDAO.updateContact(theContact, null);
        }

        //Parse attachments
        int[] fileIdForDelete;
        if (StringUtils.isNotEmpty(request.getParameter("filesForDel"))) {
            String[] strFileIdForDel = request.getParameter("filesForDel").split("/");
            fileIdForDelete = Arrays.stream(strFileIdForDel).mapToInt(Integer::parseInt).toArray();
            log.debug("Removing files: {}", fileIdForDelete);
            for (int id : fileIdForDelete) {
                String fileName = attachmentDAO.removeAttachment(id);
                fileManager.removeAttachment(contactId, fileName);
            }
        }

        String[] fileId = request.getParameterValues("fileId");
        if (fileId != null) {
            List<Attachment> newFileList = new ArrayList<>();
            List<Attachment> oldFileList = new ArrayList<>();
            String[] fileComment = request.getParameterValues("newFileComment");
            String[] oldFileComment = request.getParameterValues("oldFileComment");
            int i = 0, j = 0;
            String[] fileName = fileManager.uploadAttachment(request, response, contactId);
            for (String id : fileId) {
                if (id.equals("0") && fileName!=null) {
                    Attachment tempAttachment = new Attachment(0, fileName[i], new Date(), fileComment[i], contactId);
                    newFileList.add(tempAttachment);
                    log.debug("Creating attachment {}", tempAttachment);
                    i++;
                } else {
                    Attachment tempAttachment = new Attachment(Integer.parseInt(id), oldFileComment[j++]);
                    log.debug("UPDATE ATTACHMENT = {}", tempAttachment);
                    oldFileList.add(tempAttachment);
                }
            }
            if (CollectionUtils.isNotEmpty(newFileList))
                attachmentDAO.createAttachments(newFileList);
            if (CollectionUtils.isNotEmpty(oldFileList))
                attachmentDAO.updateAttachments(oldFileList);
        }
        return "main?command=list&targetPage=" + request.getSession().getAttribute("CURRENT_PAGE");
    }
}
