package com.gathergrid.servlet;

import com.gathergrid.entities.Response;
import com.gathergrid.entities.Ticket;
import com.gathergrid.repository.TicketRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "My_reservations", urlPatterns = "/My_reservations")
public class My_reservations extends HttpServlet {
    TicketRepository ticketRepository;
    public void init()
    {
        ticketRepository = new TicketRepository();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        List<Ticket> events = ticketRepository.findByUser(1L);
        request.setAttribute("events", events);


        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/event/My_reservations.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
