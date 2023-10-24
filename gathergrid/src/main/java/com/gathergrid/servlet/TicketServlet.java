package com.gathergrid.servlet;

import com.gathergrid.service.imp.TicketServiceImp;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.gathergrid.repository.TicketRepository;
import com.gathergrid.repository.EventRespository;
import com.gathergrid.repository.UserRepository;

import java.io.IOException;

@WebServlet("/submitTicket/*")
public class TicketServlet extends HttpServlet {

    TicketRepository ticketRepository;
    EventRespository eventRespository;
    UserRepository userRepository;

    TicketServiceImp createTicket;
    public void init()
    {
        ticketRepository = new TicketRepository();
        eventRespository = new EventRespository();
        userRepository = new UserRepository();
        createTicket = new TicketServiceImp(eventRespository, userRepository, ticketRepository);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String pathInfo = request.getPathInfo();
        String id = "";
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length == 2) {
                id = pathParts[1];
            }
        }
        Long userId = 1L;
        Long idd = Long.parseLong(id);

        if (idd != null && userId != null) {
            Long eventId = idd;
            String  input = request.getParameter("input");
            for (Integer i = 0; i < Integer.parseInt(input); i++) {
                createTicket.createTicket(eventId, userId);
            }
            response.getWriter().println("Ticket created successfully.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/event/My_reservations.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error: " + e.getMessage());
            }
        }

    }
}
