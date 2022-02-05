package com.jloroz.sb.learningspring.web;

import com.jloroz.sb.learningspring.business.service.GuestService;
import com.jloroz.sb.learningspring.data.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestWebController {

    @Autowired
    private GuestService guestService;

    @GetMapping
    public String getAllGuests(Model model){
        List<Guest> guestList = guestService.getAllGuest();
        System.out.println(guestList.get(0).getFirstName());
        model.addAttribute("guests",guestList);
        return "guests";
    }
}
