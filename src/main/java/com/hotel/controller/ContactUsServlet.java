package com.hotel.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class uses to handle requests from contactUs.jsp.
 */
@WebServlet("/contactUs")
public class ContactUsServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(ContactUsServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("ContactUsServlet#doPost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        req.getSession().setAttribute("infoMessage", "Thank you for contacting us!");
        resp.sendRedirect(req.getContextPath() + "/contactUs");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("ReviewRoomServlet#doGet");
        resp.setContentType("text/html; charset=UTF-8");
        req.getRequestDispatcher( "/pages/contactUs.jsp").forward(req, resp);
    }
}