package com.itechart.contactapp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateContactCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //Removes attribute to display empty edit-contact.jsp page
        if (request.getSession().getAttribute("CONTACT") != null) {
            request.getSession().removeAttribute("CONTACT");
        }
        return "/edit-contact.jsp";
    }
}
