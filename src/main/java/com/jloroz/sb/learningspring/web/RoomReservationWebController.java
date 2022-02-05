package com.jloroz.sb.learningspring.web;

import com.jloroz.sb.learningspring.business.domain.RoomReservation;
import com.jloroz.sb.learningspring.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class RoomReservationWebController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public String getReservations(@RequestParam(value="date",required = false)String dateString, Model model){
        Date date = DateUtils.createDateFromDateString(dateString);
        System.out.println(date.getTime());
        List<RoomReservation> roomReservationList =  reservationService.getRoomReservationsForDate(date);

        model.addAttribute("roomReservations",roomReservationList);

        return "reservations";
    }
}
