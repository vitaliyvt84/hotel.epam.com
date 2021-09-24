package com.hotel.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sContext = sce.getServletContext();
        String path = sContext.getRealPath("/WEB-INF/log4j2.log");
        System.setProperty("logFile", path);

        String contextPath = sContext.getContextPath();
        sContext.setAttribute("app", contextPath);

        //System.out.println("app ==> " + contextPath);
        final Logger logger = LogManager.getLogger(ContextListener.class);
        logger.debug("path = " + path);

       /* System.out.println("Start Scheduler!!!!!");
        ScheduleDeleting scheduleDeleting = new ScheduleDeleting();
        scheduleDeleting.checkBookings();*/
    }
}
