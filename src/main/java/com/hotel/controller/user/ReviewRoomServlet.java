package com.hotel.controller.user;

import com.hotel.dto.*;
import com.hotel.entity.ApartmentStatus;
import com.hotel.entity.BookingStatus;
import com.hotel.entity.PreOrderStatus;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class uses to handle requests from reviewRoom.jsp. Method doGet prepares data for reviewRoom.jsp, where the user
 * can choose book the apartment or cancel the order, or go back to select another apartment. Method doPost takes data from
 * reviewRoom.jsp and if user choose 'Booking the room' creates the booking and saves it to DB, if the apartment was
 * selected from the managers link than it saves status of pre-order to DB - COMPLETED, otherwise if user choose
 * 'Cancel the order' than it saves status of pre-order to DB - CANCELED.
 *
 */
@WebServlet("/reviewRoom")
public class ReviewRoomServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(ReviewRoomServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("ReviewRoomServlet#doPost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PreOrderDTO preOrderDTO = (PreOrderDTO) req.getSession().getAttribute("preOrderDTO");
        if (req.getParameter("book_room").equals("book")) {
            try {
                //Long bookRoomId = Long.valueOf(String.valueOf(req.getSession().getAttribute("bookRoomId")));

                UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
                Double totalPrice = (Double) req.getSession().getAttribute("totalPrice");

                if (preOrderDTO != null && userDTO != null && totalPrice != null) {
                    List<BookingDTO> bookingList = BookingServiceImpl.getInstance().getAll();
                    ApartmentDTO apartment = ApartmentServiceImpl.getInstance().getById(preOrderDTO.getApartmentId());

                    boolean flag = true;
                    for (BookingDTO booking : bookingList) {
                        if (booking.getApartmentId() == apartment.getId()) {
                            if (booking.getDateOut().getTime() >= preOrderDTO.getCheckIn().getTime()
                                    && booking.getDateIn().getTime() <= preOrderDTO.getCheckOut().getTime()) {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag && apartment.getStatus() == ApartmentStatus.AVAILABLE) {
                        BookingDTO bookingDTO = new BookingDTO();
                        bookingDTO.setDateIn(preOrderDTO.getCheckIn());
                        bookingDTO.setDateOut(preOrderDTO.getCheckOut());
                        bookingDTO.setStatus(BookingStatus.BOOKED);
                        bookingDTO.setPrice(totalPrice);
                        bookingDTO.setNumberOfAdult(preOrderDTO.getNumberOfAdult());
                        bookingDTO.setNumberOfChild(preOrderDTO.getNumberOfChild());
                        bookingDTO.setNumberOfRooms(apartment.getCountOfRoom());
                        bookingDTO.setUserId(userDTO.getId());
                        bookingDTO.setApartmentId(apartment.getId());
                        long bookingId = BookingServiceImpl.getInstance().save(bookingDTO);
                        req.getSession().setAttribute("bookingId", bookingId);
                        if (preOrderDTO.getStatus() != null) {
                            logger.info("EXISTS PRE ORDER STATUS!!!" + preOrderDTO.getStatus().getValue());
                            preOrderDTO.setStatus(PreOrderStatus.COMPLETED);
                            PreOrderServiceImpl.getInstance().update(preOrderDTO);
                        }
                        resp.sendRedirect(req.getContextPath() + "/payment");
                    } else {
                        logger.warn("Apartment " + apartment.getId() + " already occupied");
                        req.setAttribute("errorMessage", "Apartment " + apartment.getId() + " already occupied");
                        req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
                    }
                }
            } catch (DBException e) {
                logger.warn("Cannot get parameters", e);
                req.setAttribute("errorMessage", e.getMessage());
                req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
            }
        } else {
            logger.info("Нужно ВСЕ ОТЧИСТИТЬ!!! Session!!!!");
            //logger.info("EXISTS PRE ORDER STATUS!!!" + preOrderDTO.getStatus().getValue());
            try {
                if (preOrderDTO.getStatus() != null) {
                    logger.info("EXISTS PRE ORDER STATUS!!!" + preOrderDTO.getStatus().getValue());
                    preOrderDTO.setStatus(PreOrderStatus.CANCELED);
                    PreOrderServiceImpl.getInstance().update(preOrderDTO);
                }
            } catch (DBException e) {
                logger.warn("Cannot update parameters", e);
                req.setAttribute("errorMessage", e.getMessage());
                req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
            }
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("ReviewRoomServlet#doGet");
        resp.setContentType("text/html; charset=UTF-8");

        PreOrderDTO preOrderDTO = (PreOrderDTO) req.getSession().getAttribute("preOrderDTO");
        if (preOrderDTO != null) {
            try {
                ApartmentDTO apartment = ApartmentServiceImpl.getInstance().getById(preOrderDTO.getApartmentId());
                ApartmentClassDTO apartmentClass = ApartmentClassServiceImpl.getInstance().getById(apartment.getApartmentClassId());
                List<ApartmentImageDTO> apartmentImages = ApartmentImageServiceImpl.getInstance().getAllByApartmentId(preOrderDTO.getApartmentId());
                req.setAttribute("apartmentImages", apartmentImages);
                long numberOfDaysBetween = TimeUnit.DAYS.convert(preOrderDTO.getCheckOut().getTime()
                        - preOrderDTO.getCheckIn().getTime(), TimeUnit.MILLISECONDS);
                Double totalPrice = 0D;
                if (apartment != null) {
                    req.setAttribute("apartment", apartment);
                    totalPrice = preOrderDTO.getNumberOfAdult() * (apartment.getPrice() * numberOfDaysBetween)
                            + preOrderDTO.getNumberOfChild() * ((apartment.getPrice() /2) * numberOfDaysBetween);
                    req.getSession().setAttribute("totalPrice", totalPrice);
                }
                if (apartmentClass != null) {
                    req.setAttribute("apartmentClass", apartmentClass);
                }

            } catch (DBException e) {
                logger.warn("Cannot get parameters", e);
                req.setAttribute("errorMessage", e.getMessage());
                req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
            }
        }

        req.getRequestDispatcher("/pages/user/reviewRoom.jsp").forward(req, resp);
    }
}
