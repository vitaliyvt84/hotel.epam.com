package com.hotel.controller.user;

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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class uses to handle requests from myBookings.jsp. Method doGet prepares data for myBookings.jsp, where user can
 * see all his orders and pre-orders. If the order was not paid then user can choose pay for this order or cancel booking. Also
 * user can check the status of his pre-order and if it was processed by the manager, user can view and book the apartment.
 * Method doPost handles data that it gets from the user.
 *
 */
@WebServlet("/userOrders")
public class UserOrdersServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(UserOrdersServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("UserOrdersServlet#doPost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String address = null;
        if (req.getParameter("booking_id") != null) {
            address = "/payment";
            req.getSession().setAttribute("bookingId", req.getParameter("booking_id"));
        } else if (req.getParameter("cancel_booking_id") != null) {
            try {
                address = "/userOrders";
                BookingServiceImpl.getInstance().delete(Long.valueOf(req.getParameter("cancel_booking_id")));
            } catch (DBException e) {
                logger.warn("Cannot delete booking", e);
                req.setAttribute("errorMessage", e.getMessage());
                req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
            }
        } else {
            address = "/reviewRoom";
            Long preOrderId = Long.valueOf(req.getParameter("pre_order_id"));

            PreOrderDTO preOrderDTO = null;
            try {
                preOrderDTO = PreOrderServiceImpl.getInstance().getById(preOrderId);
                req.getSession().setAttribute("preOrderDTO", preOrderDTO);
            } catch (DBException e) {
                logger.warn("Cannot get parameters", e);
                req.setAttribute("errorMessage", e.getMessage());
                req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
            }
        }
        resp.sendRedirect(req.getContextPath() + address);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("UserOrdersServlet#doGet");
        resp.setContentType("text/html; charset=UTF-8");

        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        List<BookingDTO> userBookingList = null;
        List<PreOrderDTO> userPreOrderList = null;
        if (userDTO != null) {
            try {
                userBookingList = BookingServiceImpl.getInstance().getByUserId(userDTO.getId());
                userPreOrderList = PreOrderServiceImpl.getInstance().getByUserId(userDTO.getId());
                List<ApartmentImageDTO> apartmentImageList = ApartmentImageServiceImpl.getInstance().getAll();
                req.setAttribute("apartmentImageList", apartmentImageList);
                List<ApartmentDTO> apartments = ApartmentServiceImpl.getInstance().getAll();
                req.setAttribute("apartments", apartments);
                List<ApartmentClassDTO> apartmentClassList = ApartmentClassServiceImpl.getInstance().getAll();
                req.setAttribute("apartmentClassList", apartmentClassList);
            } catch (DBException e) {
                logger.warn("Cannot get parameters", e);
                req.setAttribute("errorMessage", e.getMessage());
                req.getRequestDispatcher(req.getContextPath() + "/pages/error.jsp").forward(req, resp);
            }
            req.setAttribute("bookingList", userBookingList);
            req.setAttribute("preOrderList", userPreOrderList);
        }
        req.getRequestDispatcher("/pages/user/myBookings.jsp").forward(req, resp);
    }
}