package com.hotel.controller;

import com.hotel.dto.RoleDTO;
import com.hotel.dto.UserDTO;
import com.hotel.exceptions.DBException;
import com.hotel.service.RoleServiceImpl;
import com.hotel.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class uses to handle requests from login.jsp.
 *
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("LoginServlet#doPost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String pageAddress = "/login";
        String message = "Wrong input login or password!";
        try {
            UserDTO userDTO = UserServiceImpl.getInstance().getByLogin(login);
            if (userDTO != null) {
                MessageDigest digest = null;
                digest = MessageDigest.getInstance("SHA1");
                byte[] hashedBytes = digest.digest(password.getBytes("UTF-8"));
                RoleDTO roleDTO = RoleServiceImpl.getInstance().getById(userDTO.getRoleId());
                if (roleDTO != null && userDTO.getPassword().equals(new BigInteger(1, hashedBytes).toString(16))) {
                    req.getSession().setAttribute("user", userDTO);
                    message = "You are logged in";
                    String url = (String) req.getSession().getAttribute("url");
                    pageAddress = "/";
                    if (url != null) {
                        pageAddress = url;
                    }
                }
            }
        } catch (NoSuchAlgorithmException e) {
            logger.warn("Cannot check data", e);
            req.getSession().setAttribute("errorMessage", e.getMessage());
            pageAddress = "/pages/error.jsp";
        } catch (DBException e) {
            logger.warn("Cannot get data from DB", e);
            req.getSession().setAttribute("errorMessage", e.getMessage());
            pageAddress = "/pages/error.jsp";
        }
        req.getSession().setAttribute("loginMessage", message);
        resp.sendRedirect(req.getContextPath() + pageAddress);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("LoginServlet#doGet");
        resp.setContentType("text/html; charset=UTF-8");
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }
}