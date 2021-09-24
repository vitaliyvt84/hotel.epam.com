package com.hotel.controller;

import com.hotel.dto.ApartmentDTO;
import com.hotel.dto.PreOrderDTO;
import com.hotel.exceptions.DBException;
import com.hotel.service.ApartmentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@WebServlet("/searchRoom")
public class SearchRoomServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(SearchRoomServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("SearchRoomServlet#doPost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        logger.info("Check-in: " + req.getParameter("check_in"));
        logger.info("Check-out: " + req.getParameter("check_out"));
        logger.info("Count_of_room: " + req.getParameter("count_of_room"));
        logger.info("Number_of_adult: " + req.getParameter("number_of_adult"));
        logger.info("Number_of_child: " + req.getParameter("number_of_child"));

        Date checkIn = null;
        Date checkOut = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            checkIn = format.parse(req.getParameter("check_in"));
            checkOut = format.parse(req.getParameter("check_out"));
            long diff = TimeUnit.DAYS.convert(checkOut.getTime() - checkIn.getTime(), TimeUnit.MILLISECONDS);
            logger.info("Days between two dates: " + diff);
            req.getSession().setAttribute("numberOfDays", diff);
        } catch (ParseException e) {
            logger.warn("Cannot parse date", e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
        }

        Integer countOfRoom = Integer.valueOf(req.getParameter("count_of_room"));
        Integer numberOfAdult = Integer.valueOf(req.getParameter("number_of_adult"));
        Integer numberOfChild = Integer.valueOf(req.getParameter("number_of_child"));

        PreOrderDTO preOrderDTO = new PreOrderDTO();
        preOrderDTO.setCheckIn(checkIn);
        preOrderDTO.setCheckOut(checkOut);
        preOrderDTO.setNumberOfRooms(countOfRoom);
        preOrderDTO.setNumberOfAdult(numberOfAdult);
        preOrderDTO.setNumberOfChild(numberOfChild);
        req.getSession().setAttribute("preOrderDTO", preOrderDTO);

        resp.sendRedirect(req.getContextPath()+"/bookingRoom");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("SearchRoomServlet#doGet");
        resp.setContentType("text/html; charset=UTF-8");

        List<ApartmentDTO> apartmentList = null;
        try {
            apartmentList = ApartmentServiceImpl.getInstance().getAll();
        } catch (DBException e) {
            logger.warn("Cannot get parameters", e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
        }
        int maxRoomNumber = 0;
        int maxAdultNumber = 0;
        int maxChildNumber = 0;
        if (apartmentList != null) {
            for (ApartmentDTO apartment : apartmentList) {
                if (maxRoomNumber < apartment.getCountOfRoom()) {
                    maxRoomNumber = apartment.getCountOfRoom();
                }
                if (maxAdultNumber < apartment.getMaxCountOfAdult()) {
                    maxAdultNumber = apartment.getMaxCountOfAdult();
                }
                if (maxChildNumber < apartment.getMaxCountOfChild()) {
                    maxChildNumber = apartment.getMaxCountOfChild();
                }
            }
        }
        req.setAttribute("maxRoomNumber", maxRoomNumber);
        req.setAttribute("maxAdultNumber", maxAdultNumber);
        req.setAttribute("maxChildNumber", maxChildNumber);

        req.getRequestDispatcher("/pages/searchRoom.jsp").forward(req, resp);
    }
}
