package com.hotel.controller.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * This class uses to handle requests from myContacts.jsp.
 *
 */
@WebServlet("/myContacts")
public class MyContactsServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(MyContactsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("PersonalCabinetServlet#doGet");
        resp.setContentType("text/html; charset=UTF-8");

        req.getRequestDispatcher("/pages/user/myContacts.jsp").forward(req, resp);
    }
}
