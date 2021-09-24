package com.hotel.controller.user;

import com.hotel.dto.BookingDTO;
import com.hotel.dto.PreOrderDTO;
import com.hotel.entity.Booking;
import com.hotel.entity.BookingStatus;
import com.hotel.entity.PreOrderStatus;
import com.hotel.exceptions.DBException;
import com.hotel.service.BookingServiceImpl;
import com.hotel.service.PreOrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class uses to handle requests from payment.jsp. According to booking_id method doGet takes data from DB and sends
 * them to payment.jsp. Method doPost takes req from payment.jsp and if user choose button 'Pay' than check user entered
 * data and makes payment. If user choose 'Cancel payment' than payment is canceled.
 *
 */
@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(PaymentServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("PaymentServlet#doPost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        logger.info("============");
        for (String valueName : req.getSession().getValueNames()) {
            logger.info(valueName);
        }

        System.out.println(req.getSession().getAttribute("bookingId"));
        String payStatus = req.getParameter("pay");
        Long bookingId = Long.valueOf(String.valueOf(req.getSession().getAttribute("bookingId")));

        if (payStatus.equals("payOk")) {

            if (bookingId != null) {
                try {
                    BookingDTO bookingDTO = BookingServiceImpl.getInstance().getById(bookingId);
                    bookingDTO.setStatus(BookingStatus.OCCUPIED);
                    BookingServiceImpl.getInstance().update(bookingDTO);
                } catch (DBException e) {
                    logger.warn("Cannot update booking", e);
                    req.setAttribute("errorMessage", e.getMessage());
                    req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
                }
            }
            req.getSession().setAttribute("payMessage", "Congratulations!!!!!!");
        } else {
            if (bookingId != null) {
                try {
                    BookingDTO bookingDTO = BookingServiceImpl.getInstance().getById(bookingId);
                    BookingServiceImpl.getInstance().delete(bookingDTO.getId());
                } catch (DBException e) {
                    logger.warn("Cannot delete booking", e);
                    req.setAttribute("errorMessage", e.getMessage());
                    req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
                }
            }
            req.getSession().setAttribute("payMessage", "Booking canceled!!!!!!");
        }

        //resp.sendRedirect(req.getContextPath() + "/pages/greetings.jsp");
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("PaymentServlet#doGet");
        resp.setContentType("text/html; charset=UTF-8");
        Long bookingId = Long.valueOf(String.valueOf(req.getSession().getAttribute("bookingId")));
        try {
            BookingDTO bookingDTO = BookingServiceImpl.getInstance().getById(bookingId);
            req.setAttribute("bookingDTO", bookingDTO);
        } catch (DBException e) {
            logger.warn("Cannot get parameters", e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("/pages/user/payment.jsp").forward(req, resp);
    }
}
