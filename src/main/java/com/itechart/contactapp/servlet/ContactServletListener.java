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

    private Properties properties;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("mailServer.properties");
        try {
            properties.load(input);
        } catch (IOException e) {
            log.error("Unable to initialize properties file", e);
        }
        servletContextEvent.getServletContext().setAttribute("dataSource", dataSource);

        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new DailyMailingJob(dataSource, properties), 0, 1, TimeUnit.DAYS);
        log.info("Daily mailing service started.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        scheduler.shutdownNow();
    }
}