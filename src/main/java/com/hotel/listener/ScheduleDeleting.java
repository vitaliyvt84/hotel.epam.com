package com.hotel.listener;

import com.hotel.dto.BookingDTO;
import com.hotel.exceptions.DBException;
import com.hotel.service.BookingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ScheduleDeleting {

    private static Logger logger = LogManager.getLogger(ScheduleDeleting.class);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void checkBookings() {
        final Runnable checker = new Runnable() {
            @Override
            public void run() {
                logger.info("Start checking Bookings!!!");
                try {
                    List<BookingDTO> bookings = BookingServiceImpl.getInstance().getAll();
                    for (BookingDTO booking : bookings) {
                        LocalDateTime createTime = booking.getCreateTime();
                        if (booking.getStatus().getValue() == 1 && createTime.plusSeconds(60).isBefore(LocalDateTime.now())) {
                            BookingServiceImpl.getInstance().delete(booking.getId());

                            logger.info("Booking create time: " + createTime);
                            logger.info("Booking create time + 30: " + createTime.plusSeconds(30));
                        }
                    }
                } catch (DBException e) {
                    logger.warn("Cannot delete booking", e);
                }

            }
        };
        final ScheduledFuture<?> bookingHandler = scheduler.scheduleWithFixedDelay(checker, 30, 30, SECONDS);
    }
}