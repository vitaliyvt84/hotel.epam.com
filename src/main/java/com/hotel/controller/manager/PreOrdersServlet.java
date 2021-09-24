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
import java.util.concurrent.TimeUnit;

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
        logger.info("PaymentServlet#doPost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Long preOrderId = Long.valueOf(req.getParameter("pre_order_id"));
        PreOrderDTO preOrderDTO = null;
        Map<ApartmentClassDTO, List<ApartmentDTO>> resultMap = new TreeMap<>(Comparator.comparing(ApartmentClassDTO::getName));
        try {
            preOrderDTO = PreOrderServiceImpl.getInstance().getById(preOrderId);
            List<ApartmentDTO> apartmentList = ApartmentServiceImpl.getInstance().getAll();
            ApartmentClassDTO apartmentClass = ApartmentClassServiceImpl.getInstance().getById(preOrderDTO.getApartmentClassId());
            List<BookingDTO> bookingList = BookingServiceImpl.getInstance().getAll();

            List<ApartmentDTO> resultList = new ArrayList<>();

            for (ApartmentDTO apartment : apartmentList) {
                if (preOrderDTO.getApartmentClassId() == apartment.getApartmentClassId()
                        && preOrderDTO.getNumberOfRooms() <= apartment.getCountOfRoom()
                        && preOrderDTO.getNumberOfAdult() <= apartment.getMaxCountOfAdult()
                        && preOrderDTO.getNumberOfChild() <= apartment.getMaxCountOfChild()) {
                    boolean flag = true;
                    for (BookingDTO booking : bookingList) {
                        if (booking.getApartmentId() == apartment.getId()
                                && booking.getDateOut().getTime() >= preOrderDTO.getCheckIn().getTime()
                                && booking.getDateIn().getTime() <= preOrderDTO.getCheckOut().getTime()) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        resultList.add(apartment);
                    }
                }
            }
            resultMap.put(apartmentClass, resultList);
        } catch (DBException e) {
            logger.warn("Cannot get parameters", e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
        }
        req.getSession().setAttribute("mapAClassAList", resultMap);
        req.getSession().setAttribute("preOrderDTO", preOrderDTO);
        long diff = TimeUnit.DAYS.convert(preOrderDTO.getCheckOut().getTime() - preOrderDTO.getCheckIn().getTime(), TimeUnit.MILLISECONDS);
        logger.info("Days between two dates: " + diff);
        req.getSession().setAttribute("numberOfDays", diff);

        resp.sendRedirect(req.getContextPath()+"/bookingRoom");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("PreOrdersServlet#doGet");
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
        logger.info(preOrders.get(0).getCheckIn());
        logger.info(preOrders.get(0).getCheckOut());
        req.getRequestDispatcher("/pages/manager/pre_orders.jsp").forward(req, resp);
    }
}
