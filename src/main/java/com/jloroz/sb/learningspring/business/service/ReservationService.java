package com.jloroz.sb.learningspring.business.service;

import com.jloroz.sb.learningspring.business.domain.RoomReservation;
import com.jloroz.sb.learningspring.data.entity.Guest;
import com.jloroz.sb.learningspring.data.entity.Reservation;
import com.jloroz.sb.learningspring.data.entity.Room;
import com.jloroz.sb.learningspring.data.repository.GuestRepository;
import com.jloroz.sb.learningspring.data.repository.ReservationRepository;
import com.jloroz.sb.learningspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public List<RoomReservation> getRoomReservationsForDate(Date date){
        System.out.println(date.getTime());
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long,RoomReservation> roomReservationMap = new HashMap();

        rooms.forEach(room ->{
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(),roomReservation);
        });

        Iterable<Reservation> reservations = reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));

        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);

            Guest guest = guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setGuestId(guest.getGuestId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());

        });

        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long id: roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(id));
        }

        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if(o1.getRoomName() == o2.getRoomName()){
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return roomReservations;
    }
}
