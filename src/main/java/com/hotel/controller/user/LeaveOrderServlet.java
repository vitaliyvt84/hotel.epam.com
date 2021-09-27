package com.hotel.controller.user;

import com.hotel.dto.ApartmentClassDTO;
import com.hotel.dto.ApartmentDTO;
import com.hotel.dto.PreOrderDTO;
import com.hotel.dto.UserDTO;
import com.hotel.entity.PreOrderStatus;
import com.hotel.exceptions.DBException;
import com.hotel.service.ApartmentClassServiceImpl;
import com.hotel.service.ApartmentServiceImpl;
import com.hotel.service.PreOrderServiceImpl;
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
import java.util.Date;
import java.util.List;

/**
 * This class uses to handle requests from leaveOrder.jsp. Method doGet takes data from DB which are necessary for
 * leaveOrder.jsp and transfer them there. Method doPost takes data from leaveOrder.jsp, handles them and save
 * PreOrder to DB.
 *
 */
@WebServlet("/leaveOrder")
public class LeaveOrderServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(LeaveOrderServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("LeaveOrderServlet#doPost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Date checkIn = null;
        Date checkOut = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            checkIn = format.parse(req.getParameter("check_in"));
            checkOut = format.parse(req.getParameter("check_out"));
        } catch (ParseException e) {
            logger.warn("Cannot parse date", e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(req.getContextPath() + "/pages/error.jsp").forward(req, resp);
        }
        Long apartmentClass = Long.valueOf(req.getParameter("apartment_class"));
        Integer numberOfAdult = Integer.valueOf(req.getParameter("number_of_adult"));
        Integer numberOfChild = Integer.valueOf(req.getParameter("number_of_child"));
        Integer countOfRoom = Integer.valueOf(req.getParameter("count_of_room"));
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");

        PreOrderDTO preOrderDTO = new PreOrderDTO();
        preOrderDTO.setCheckIn(checkIn);
        preOrderDTO.setCheckOut(checkOut);
        preOrderDTO.setApartmentClassId(apartmentClass);
        preOrderDTO.setNumberOfAdult(numberOfAdult);
        preOrderDTO.setNumberOfChild(numberOfChild);
        preOrderDTO.setNumberOfRooms(countOfRoom);
        preOrderDTO.setUserId(userDTO.getId());
        preOrderDTO.setStatus(PreOrderStatus.NEW);
        preOrderDTO.setApartmentId(0L);
        try {
            PreOrderServiceImpl.getInstance().save(preOrderDTO);
        } catch (DBException e) {
            logger.warn("Cannot get parameters", e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(req.getContextPath() + "/pages/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("LeaveOrderServlet#doGet");
        resp.setContentType("text/html; charset=UTF-8");

        List<ApartmentDTO> apartmentList = null;
        List<ApartmentClassDTO> apartmentClasses = null;
        try {
            apartmentList = ApartmentServiceImpl.getInstance().getAll();
            apartmentClasses = ApartmentClassServiceImpl.getInstance().getAll();
        } catch (DBException e) {
            logger.warn("Cannot get parameters", e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(req.getContextPath() + "/pages/error.jsp").forward(req, resp);
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
        if (apartmentClasses != null) {
            req.setAttribute("apartment_classes", apartmentClasses);
        }
        req.setAttribute("maxRoomNumber", maxRoomNumber);
        req.setAttribute("maxAdultNumber", maxAdultNumber);
        req.setAttribute("maxChildNumber", maxChildNumber);
        req.getRequestDispatcher("/pages/user/leaveOrder.jsp").forward(req, resp);
    }
}
