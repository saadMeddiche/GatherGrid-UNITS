package com.gathergrid.servlet;

import com.gathergrid.entities.Event;
import com.gathergrid.entities.Response;
import com.gathergrid.repository.CategorieRepository;
import com.gathergrid.repository.EventRespository;
import com.gathergrid.repository.UserRepository;
import com.gathergrid.service.imp.EventServiceImp;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    EventServiceImp eventServiceImp;

    public void init() {
        eventServiceImp = new EventServiceImp(new EventRespository(), new CategorieRepository(), new UserRepository());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pageNumber = 1;
        if (request.getParameter("page") != null) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }
        Response res = eventServiceImp.SearchEvents(pageNumber, request.getParameter("search"));
        List<Event> events = null;
        Integer totalPages = 0;
        if (res.getStatus() == 200) {
            List<Object> data = (List<Object>) res.getData();
            events = (List<Event>) data.get(0);
            totalPages = (Integer) data.get(1);
        }
        request.setAttribute("events", events);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageNumber", pageNumber);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
