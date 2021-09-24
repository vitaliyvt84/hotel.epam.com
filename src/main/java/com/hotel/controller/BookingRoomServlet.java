package com.hotel.controller;

import com.hotel.dto.*;
import com.hotel.entity.*;
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
import java.util.stream.Collectors;

/**
 *
 */
@WebServlet("/bookingRoom")
public class BookingRoomServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(BookingRoomServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("BookingRoomServlet#doPost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        if (req.getParameter("choose_room").equals("manager")) {
            logger.info("manager_choose = " + req.getParameter("manager_choose"));
            PreOrderDTO preOrderDTO = (PreOrderDTO) req.getSession().getAttribute("preOrderDTO");
            long apartmentId = Long.valueOf(req.getParameter("manager_choose"));
            preOrderDTO.setApartmentId(apartmentId);
            preOrderDTO.setStatus(PreOrderStatus.PROCESSED);
            try {
                PreOrderServiceImpl.getInstance().update(preOrderDTO);
            } catch (DBException e) {
                logger.warn("Cannot update parameters", e);
                req.setAttribute("errorMessage", e.getMessage());
                req.getRequestDispatcher(req.getContextPath()+"/pages/error.jsp").forward(req, resp);
            }
            resp.sendRedirect(req.getContextPath() + "/preOrders");

        } else {
            logger.info("user_choose = " + req.getParameter("user_choose"));
            PreOrderDTO preOrderDTO = (PreOrderDTO) req.getSession().getAttribute("preOrderDTO");
            long apartmentId = Long.valueOf(req.getParameter("user_choose"));
            preOrderDTO.setApartmentId(apartmentId);
            req.getSession().setAttribute("preOrderDTO", preOrderDTO);

            UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
            System.out.println("user = " + userDTO);

            if (userDTO != null) {
                //req.getRequestDispatcher("/pages/user/reviewRoom.jsp").forward(req, resp);
                resp.sendRedirect(req.getContextPath() + "/reviewRoom");
            } else {
                req.getSession().setAttribute("url", req.getServletPath());
                req.getSession().setAttribute("message", "You need to be register");
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("BookingRoomServlet#doGet");
        resp.setContentType("text/html; charset=UTF-8");
        /*for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
            logger.info(entry.getKey() + " " + entry.getValue());
        }
        logger.info("============");
        for (String valueName : req.getSession().getValueNames()) {
            logger.info(valueName);
        }*/


        PreOrderDTO preOrderDTO = (PreOrderDTO) req.getSession().getAttribute("preOrderDTO");
        long diff = TimeUnit.DAYS.convert(preOrderDTO.getCheckOut().getTime()
                - preOrderDTO.getCheckIn().getTime(), TimeUnit.MILLISECONDS);
        req.setAttribute("numberOfDays", diff);


        //PAGINATION
        int page = 1;
        int recordsPerPage = 7;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        try {
            List<ApartmentClassDTO> apartmentClassList = ApartmentClassServiceImpl.getInstance().getAll();
            List<BookingDTO> bookingList = BookingServiceImpl.getInstance().getAll();
            List<ApartmentImageDTO> apartmentImageList = ApartmentImageServiceImpl.getInstance().getAll();

            //Long numberOfRecords = ApartmentServiceImpl.getInstance().countOfRows();

            List<ApartmentDTO> apartmentList = null;
            //if (req.getSession().getAttribute("opt_val") != null || req.getParameter("button_sort") != null) {

            String sortValue = "APC_ID";
            if (req.getSession().getAttribute("opt_val") != null) {
                sortValue = String.valueOf(req.getSession().getAttribute("opt_val"));
            }
            if (req.getParameter("button_sort") != null) {
                sortValue = req.getParameter("sort_by");
                logger.info("SORT BY " + req.getParameter("sort_by"));
            }
            req.getSession().setAttribute("opt_val", sortValue);

            long numberOfRecords = 0L;

            if (sortValue.equals("ST")) {
               apartmentList = ApartmentServiceImpl.getInstance().getAllWhereThreeCondition(preOrderDTO.getNumberOfAdult(),
                        preOrderDTO.getNumberOfChild(), preOrderDTO.getNumberOfRooms());
                numberOfRecords = apartmentList.size();

            } else {
                apartmentList = ApartmentServiceImpl.getInstance()
                        .getAllWhereOrderByColumnOffsetNumOfRec(SortingType.valueOf(sortValue).getValue(),
                                (page-1)*recordsPerPage, recordsPerPage, preOrderDTO.getNumberOfAdult(),
                                preOrderDTO.getNumberOfChild(), preOrderDTO.getNumberOfRooms());
                numberOfRecords = ApartmentServiceImpl.getInstance().countOfRowsWhere(preOrderDTO.getNumberOfAdult(),
                        preOrderDTO.getNumberOfChild(), preOrderDTO.getNumberOfRooms());
            /*apartmentList = ApartmentServiceImpl.getInstance().getAllOrderByColumnOffsetNumberOfRecords(SortingType.valueOf(sortValue).getValue(),
                    (page-1)*recordsPerPage, recordsPerPage);*/
            }


            /*List<BookingDTO> dateBookingList = new ArrayList<>();
            for (ApartmentDTO apartment : apartmentList) {
                BookingDTO bookingDTO = new BookingDTO();
                bookingDTO.setApartmentId(apartment.getId());
                if (apartment.getStatus() == ApartmentStatus.NOT_AVAILABLE) {
                    bookingDTO.setStatus(BookingStatus.NOT_AVAILABLE);
                }
                if (apartment.getStatus() == ApartmentStatus.AVAILABLE) {
                    boolean flag = true;
                    for (BookingDTO booking : bookingList) {
                        if (booking.getApartmentId() == apartment.getId()) {
                            if (booking.getDateOut().getTime() >= preOrderDTO.getCheckIn().getTime()
                                    && booking.getDateIn().getTime() <= preOrderDTO.getCheckOut().getTime()) {
                                bookingDTO.setStatus(booking.getStatus());
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        bookingDTO.setStatus(BookingStatus.EMPTY);
                    }
                }
                dateBookingList.add(bookingDTO);
            }

            dateBookingList.sort(Comparator.comparing(o -> o.getStatus().getValue()));
            if (sortValue.equals("ST")) {
                List<ApartmentDTO> resultList = new ArrayList<>();
                for (int i = (page-1)*(recordsPerPage); i < page*recordsPerPage && i < numberOfRecords; i++) {
                    for (ApartmentDTO apartment : apartmentList) {
                        if (apartment.getId() == dateBookingList.get(i).getApartmentId()) {
                            resultList.add(apartment);
                            break;
                        }
                    }
                }
                apartmentList = resultList;
            }
            req.setAttribute("dateBookingList", dateBookingList);

            int noOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
            req.setAttribute("apartmentList", apartmentList);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            req.setAttribute("apartmentClassList", apartmentClassList);
            */


            Map<Long, BookingStatus> apartmentBookingStatus = new HashMap<>();
            for (ApartmentDTO apartment : apartmentList) {
                if (apartment.getStatus() == ApartmentStatus.NOT_AVAILABLE) {
                    apartmentBookingStatus.put(apartment.getId(), BookingStatus.NOT_AVAILABLE);
                }
                if (apartment.getStatus() == ApartmentStatus.AVAILABLE) {
                    boolean flag = true;
                    for (BookingDTO booking : bookingList) {
                        if (booking.getApartmentId() == apartment.getId()) {
                            if (booking.getDateOut().getTime() >= preOrderDTO.getCheckIn().getTime()
                                    && booking.getDateIn().getTime() <= preOrderDTO.getCheckOut().getTime()) {
                                apartmentBookingStatus.put(apartment.getId(), booking.getStatus());
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        apartmentBookingStatus.put(apartment.getId(), BookingStatus.EMPTY);
                    }
                }
            }


            if (sortValue.equals("ST")) {
                LinkedHashMap<Long, BookingStatus> sortedMap = apartmentBookingStatus.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
                List<ApartmentDTO> apartmentStatusSorted = new ArrayList<>();
                for (Long id : sortedMap.keySet()) {
                    for (ApartmentDTO apartment : apartmentList) {
                        if (id == apartment.getId()) {
                            apartmentStatusSorted.add(apartment);
                        }
                    }
                }

                apartmentList = new ArrayList<>();
                for (int i = (page-1)*(recordsPerPage); i < page*recordsPerPage && i < numberOfRecords; i++) {
                    apartmentList.add(apartmentStatusSorted.get(i));
                }
                req.setAttribute("apartmentBookingStatus", sortedMap);
            } else {
                req.setAttribute("apartmentBookingStatus", apartmentBookingStatus);
            }


            int noOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
            req.setAttribute("apartmentList", apartmentList);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            req.setAttribute("apartmentClassList", apartmentClassList);
            req.setAttribute("apartmentImageList", apartmentImageList);

        } catch (DBException e) {
            logger.warn("Cannot get parameters", e);
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(req.getContextPath() + "/pages/error.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("/pages/booking.jsp").forward(req, resp);
    }
}