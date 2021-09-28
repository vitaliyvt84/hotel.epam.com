package com.hotel.controller.manager;

import com.hotel.dto.*;
import com.hotel.exceptions.DBException;
import com.hotel.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * This class uses to handle requests from pre_order.jsp. doGet method sends to pre_order.jsp information about pre-orders
 * and if status of pre-order equals NEW manager can push the button and choose the apartment he likes and send request
 * to user.
 */
@WebServlet("/preOrders")
public class PreOrdersServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(PreOrdersServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("PaymentServlet#doPost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Long preOrderId = Long.valueOf(req.getParameter("pre_order_id"));
        PreOrderDTO preOrderDTO = null;
        try {
            preOrderDTO = PreOrderServiceImpl.getInstance().getById(preOrderId);
        } catch (DBException e) {
            logger.warn("Cannot get parameters", e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
        }
        req.getSession().setAttribute("preOrderDTO", preOrderDTO);

        resp.sendRedirect(req.getContextPath()+"/bookingRoom");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("PreOrdersServlet#doGet");
        resp.setContentType("text/html; charset=UTF-8");
        List<PreOrderDTO> preOrders = null;
        List<UserDTO> users = null;
        List<ApartmentClassDTO> apartmentClasses = null;
        try {
            preOrders = PreOrderServiceImpl.getInstance().getAll();
            users = UserServiceImpl.getInstance().getAll();
            apartmentClasses = ApartmentClassServiceImpl.getInstance().getAll();
        } catch (DBException e) {
            logger.warn("Cannot get parameters", e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
        }
        if (preOrders != null && users != null & apartmentClasses != null) {
            req.setAttribute("preOrders", preOrders);
            req.setAttribute("users", users);
            req.setAttribute("apartmentClasses", apartmentClasses);
        }
        req.getRequestDispatcher("/pages/manager/pre_orders.jsp").forward(req, resp);
    }
}