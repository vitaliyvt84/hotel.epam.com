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
 * This class uses to handle requests from hotel.jsp.
 */
@WebServlet("/")
public class HotelServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(HotelServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("HotelServlet#doGet");
        resp.setContentType("text/html; charset=UTF-8");

        if (req.getSession().getAttribute("payMessage") != null) {
            req.setAttribute("payMessage", req.getSession().getAttribute("payMessage"));
            req.getSession().removeAttribute("payMessage");
        }

        req.getRequestDispatcher("/pages/hotel.jsp").forward(req, resp);
    }
}