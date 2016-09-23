package com.itechart.contactapp.servlet;

import com.itechart.contactapp.command.Command;
import com.itechart.contactapp.command.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@MultipartConfig
public class ContactControllerServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ContactControllerServlet.class);

    @Resource(name = "jdbc/dmitry_kach_db")
    private DataSource dataSource;
    public static Properties properties;

    @Override
    public void init() throws ServletException {
        properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("contactapp.properties");
        try {
            properties.load(input);
        } catch (IOException e) {
            log.error("Unable to initialize properties file", e);
        }
        getServletConfig().getServletContext().setAttribute("contactapp.properties", properties);
        log.info("Servlet initialized");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Command command = CommandFactory.getCommand(request, dataSource);
        String view = command.execute(request, response);
        log.debug("VIEW {}", view);
        try {
            if (!view.contains("main")) {
                request.getRequestDispatcher(view).forward(request, response);
            } else {
                response.sendRedirect(view);
            }
        } catch (Exception e) {
            throw new ServletException("Executing command failed.", e);
        }
    }
}