package com.itechart.contactapp.command;

import com.itechart.contactapp.model.Contact;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FillEmailFormCommand implements Command {

    private static final Logger log = LogManager.getLogger(FillEmailFormCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Integer, Contact> contacts = (Map<Integer, Contact>) request.getSession().getAttribute("CONTACT_LIST");
        String id = request.getParameter("contacts");
        log.debug("Contacts for mailing {}", id);
        String emailsStr = "";
        if (StringUtils.isNotEmpty(id)) {
            List<String> idList = Arrays.asList(id.split(","));
            request.getSession().setAttribute("idForMailing", idList);
            for (String tempId : idList) {
                String email = contacts.get(Integer.parseInt(tempId)).getEmail();
                if (StringUtils.isNotEmpty(email))
                    emailsStr += email + ", ";
            }
            if (StringUtils.isNotEmpty(emailsStr))
                emailsStr = emailsStr.substring(0, emailsStr.length() - 2);
        }

        //Add templates sources to JSP file
        STGroup group = new STGroupFile("emailTemplates.stg");
        List<String> templates = new ArrayList<>();
        ST st1 = group.getInstanceOf("template1");
        ST st2 = group.getInstanceOf("template2");
        templates.add(st1.impl.getTemplateSource());
        templates.add(st2.impl.getTemplateSource());

        request.setAttribute("TEMPLATES", templates);
        request.setAttribute("EMAILS", emailsStr);
        return "/email-form.jsp";
    }
}