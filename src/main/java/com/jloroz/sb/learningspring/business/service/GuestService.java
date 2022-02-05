package com.jloroz.sb.learningspring.business.service;

import com.jloroz.sb.learningspring.data.entity.Guest;
import com.jloroz.sb.learningspring.data.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public List<Guest> getAllGuest(){
        Iterable<Guest> guests = guestRepository.findAll();
        List<Guest> guestList = new ArrayList<>();

        System.out.println(guests.iterator().next().getLastName());
        guests.forEach(guestList::add);

        guestList.sort(new Comparator<Guest>() {
            @Override
            public int compare(Guest o1, Guest o2) {
                if(o1.getLastName() == o2.getLastName()){
                    return o1.getFirstName().compareTo(o2.getFirstName());
                }
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });

        return guestList;
    }

}
