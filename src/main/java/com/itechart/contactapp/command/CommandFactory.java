package com.itechart.contactapp.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.sql.DataSource;

public class CommandFactory {

    private static final Logger log = LogManager.getLogger(CommandFactory.class);

    public static Command getCommand(ServletRequest request) {
        DataSource dataSource = (DataSource) request.getServletContext().getAttribute("dataSource");
        String theCommand = request.getParameter("command");
        log.info("Command = {}", theCommand);
        if (theCommand == null) {
            theCommand = "list";
        }
        switch (theCommand) {
            case "list":
                return new ShowContactsCommand(dataSource);
            case "editContact":
                return new EditContactCommand(dataSource);
            case "deleteContact":
                return new DeleteContactCommand(dataSource);
            case "createContact":
                return new CreateContactCommand();
            case "saveContact":
                return new SaveContactCommand(dataSource);
            case "emailForm":
                return new FillEmailFormCommand();
            case "sendEmail":
                return new SendEmailCommand();
            case "search":
                return new SearchContactsCommand(dataSource);
            default:
                return null;
        }
    }
}