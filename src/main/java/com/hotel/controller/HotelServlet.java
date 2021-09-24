package com.hotel.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebServlet("/")
public class HotelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("payMessage", req.getSession().getAttribute("payMessage"));
        req.getSession().removeAttribute("payMessage");
        req.getRequestDispatcher("/pages/hotel.jsp").forward(req, resp);
    }
}
