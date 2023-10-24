package com.gathergrid.service.imp;

import com.gathergrid.entities.Event;
import com.gathergrid.entities.Response;
import com.gathergrid.entities.Ticket;
import com.gathergrid.entities.User;
import com.gathergrid.repository.TicketRepository;
import com.gathergrid.repository.EventRespository;
import com.gathergrid.repository.UserRepository;

import java.sql.Date;
import java.util.List;

public class TicketServiceImp {
    EventRespository eventRepository;
    UserRepository userRepository;
    TicketRepository ticketRepository;

    public TicketServiceImp(EventRespository eventRepository, UserRepository userRepository, TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    public void createTicket(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId);
        User user = userRepository.findById(userId);

        if (event != null && user != null) {
            Date date = new Date(System.currentTimeMillis());
            Ticket ticket = new Ticket(date, user, event);

            ticketRepository.save(ticket);
        }
    }

    public Response getTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        if(tickets.isEmpty()){
            return new Response("No Events Found",404);
        }else{
            return new Response("Events Found",tickets,200);
        }
    }

}
