package com.hotel.controller;

import com.hotel.dto.UserDTO;
import com.hotel.exceptions.DBException;
import com.hotel.service.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class uses to handle requests from registration.jsp.
 *
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(RegistrationServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("Registration Servlet# doPost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String pageAddress = "/login";
        UserDTO userDTO = null;
        String login = req.getParameter("login");
        try {
            userDTO = UserServiceImpl.getInstance().getByLogin(login);
        } catch (DBException e) {
            logger.warn("Cannot get user by login", e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
        }
        if (userDTO != null) {
            logger.info("Try to input Login that already exists");
            req.getSession().setAttribute("message", "User with this login already exists, please enter another login!");
            pageAddress = "/registration";
        }
        String email = req.getParameter("email");
        try {
            userDTO = UserServiceImpl.getInstance().getByEmail(email);
        } catch (DBException e) {
            logger.warn("Cannot get user by email", e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
        }
        if (userDTO != null) {
            logger.info("Try to input Email that already exists");
            req.getSession().setAttribute("message", "User with this email already exists, please enter another email address!");
            pageAddress = "/registration";
        }
        String userName = req.getParameter("userName");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        if (!password.equals(repassword)) {
            logger.info("Inserted two different passwords");
            req.getSession().setAttribute("message", "You entered two different passwords please re-enter!");
            pageAddress = "/registration";
        }

        if (pageAddress.equals("/login")) {
            try {
                UserServiceImpl.getInstance().save(new UserDTO(login, email, password, userName, phone, address, 3L));
                req.getSession().setAttribute("message", "Congratulations, you have successfully registered!");
            } catch (DBException e) {
                logger.log(Level.WARN, "Can't insert user", e);
                req.getSession().setAttribute("errorMessage", e.getMessage());
                resp.sendRedirect(req.getContextPath()+"/pages/error.jsp");
            }
        }
        resp.sendRedirect(req.getContextPath()+pageAddress);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("Registration Servlet# doGet");
        resp.setContentType("text/html; charset=UTF-8");
        req.getRequestDispatcher("/pages/registration.jsp").forward(req, resp);
    }
}