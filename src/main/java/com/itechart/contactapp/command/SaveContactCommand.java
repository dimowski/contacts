package com.itechart.contactapp.command;

import com.itechart.contactapp.dao.ContactDAO;
import com.itechart.contactapp.dao.ContactDAOFactory;
import com.itechart.contactapp.helper.FileManager;
import com.itechart.contactapp.helper.FileManagerUtil;
import com.itechart.contactapp.model.Address;
import com.itechart.contactapp.model.Contact;
import com.itechart.contactapp.model.Phone;
import com.itechart.contactapp.servlet.ContactControllerServlet;
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

    private static final Logger log = LogManager.getLogger(EditContactCommand.class);

    private ContactDAO contactDAO;

    public SaveContactCommand(DataSource dataSource) {
        contactDAO = ContactDAOFactory.getContactDAO(dataSource);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
        FileManager fileManager = new FileManagerUtil(ContactControllerServlet.properties);
        String photo = fileManager.uploadProfilePhoto(request, response);

        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String house = request.getParameter("house");
        String flat = request.getParameter("flat");
        String zipCode = request.getParameter("zipCode");
        Address theAddress = new Address(country, city, street, house, flat, zipCode);

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
                //------VALIDATION NEED HERE!!!!------
                Phone tempPhone = new Phone(Integer.parseInt(phoneId[i]), countryCode[i], operatorCode[i], phoneNumber[i], phoneType[i], comments[i]);
                phoneList.add(tempPhone);
                log.debug(tempPhone);
            }
        }

        int[] phoneIdForDelete = new int[0];
        if (!request.getParameter("idsForDel").equals("")) {
            String[] strPhoneIdForDel = request.getParameter("idsForDel").split("/");
            phoneIdForDelete = Arrays.stream(strPhoneIdForDel).mapToInt(Integer::parseInt).toArray();
        }

        Contact theContact = new Contact(firstName, lastName, middleName, birthday, status, gender, citizenship,
                webSite, email, jobCurrent, theAddress, photo, phoneList);

        Contact oldContact = (Contact) request.getSession().getAttribute("CONTACT");
        if (oldContact != null) {
            theContact.setId(oldContact.getId());
            if (photo == null) {
                theContact.setPhoto(oldContact.getPhoto());
            }
            contactDAO.updateContact(theContact, phoneIdForDelete);
        } else {
            contactDAO.createContact(theContact);
        }
        return "main";
    }
}
