package com.gathergrid.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Setter@Getter
public class Ticket {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private Date date_of_reservation;
    @ManyToOne
    private User user;
    @ManyToOne
    private Event event;

    public Ticket(Date date_of_reservation, User user, Event event) {
        this.date_of_reservation = date_of_reservation;
        this.user = user;
        this.event = event;
    }
    public Ticket() {
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", date_of_reservation=" + date_of_reservation +
                ", user=" + user +
                '}';
    }



}
