package com.itechart.contactapp.servlet;

import com.itechart.contactapp.command.Command;
import com.itechart.contactapp.command.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class ContactControllerServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ContactControllerServlet.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Command command = CommandFactory.getCommand(request);
        try {
            String view = command.execute(request, response);
            log.debug("Target view: {}", view);
            if (!view.contains("main")) {
                request.getRequestDispatcher(view).forward(request, response);
            } else {
                response.sendRedirect(view);
            }
        } catch (Exception e) {
            log.fatal("Executing command failed!", e);
            throw new ServletException("Executing command failed!", e);
        }
    }
}