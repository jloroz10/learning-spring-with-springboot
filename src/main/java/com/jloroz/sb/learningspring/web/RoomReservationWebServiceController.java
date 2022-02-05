package com.jloroz.sb.learningspring.web;

import com.jloroz.sb.learningspring.business.domain.RoomReservation;
import com.jloroz.sb.learningspring.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class RoomReservationWebServiceController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<RoomReservation> getAllReservationFromDate(@RequestParam(name="date",required = false)String dateString){
        Date date = DateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservationList = reservationService.getRoomReservationsForDate(date);

        return roomReservationList;
    }

}
