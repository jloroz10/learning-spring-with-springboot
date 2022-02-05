package com.jloroz.sb.learningspring.data.repository;

import com.jloroz.sb.learningspring.data.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;


@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Long> {

    Iterable<Reservation> findReservationByReservationDate(Date date);

}
