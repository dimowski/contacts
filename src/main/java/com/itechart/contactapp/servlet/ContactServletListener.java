package com.itechart.contactapp.servlet;

import com.itechart.contactapp.mailing.DailyMailingJob;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class ContactServletListener implements ServletContextListener {

    private static final Logger log = LogManager.getLogger(ContactServletListener.class);
    private ScheduledExecutorService scheduler;

    @Resource(name = "jdbc/dmitry_kach_db")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("Reading properties...");
        Properties mailProp = new Properties();
        Properties dirProp = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream mailInput = classLoader.getResourceAsStream("mailServer.properties");
        InputStream dirInput = classLoader.getResourceAsStream("contactapp.properties");
        try {
            mailProp.load(mailInput);
            dirProp.load(dirInput);
        } catch (IOException e) {
            log.error("Unable to load properties file", e);
        }
        servletContextEvent.getServletContext().setAttribute("dataSource", dataSource);
        servletContextEvent.getServletContext().setAttribute("mailing.properties", mailProp);
        servletContextEvent.getServletContext().setAttribute("contactapp.properties", dirProp);
        log.info("Servlet properties loaded.");

        log.info("Starting daily mailing service...");
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new DailyMailingJob(dataSource, mailProp), 0, 1, TimeUnit.DAYS);
        log.info("Daily mailing service started successfully.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        scheduler.shutdownNow();
    }
}