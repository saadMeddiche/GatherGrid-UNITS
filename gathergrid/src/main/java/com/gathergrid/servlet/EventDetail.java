package com.gathergrid.servlet;

import com.gathergrid.entities.Event;
import com.gathergrid.entities.Response;
import com.gathergrid.repository.CategorieRepository;
import com.gathergrid.entities.User;
import com.gathergrid.factory.DbEntityManagerFactory;
import com.gathergrid.repository.EventRespository;
import com.gathergrid.repository.UserRepository;
import com.gathergrid.service.imp.CommentServiceImp;
import com.gathergrid.service.imp.EventServiceImp;
import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "event_detail", urlPatterns = "/event_detail")
public class EventDetail extends HttpServlet {

    EventServiceImp eventServiceImp;
    CommentServiceImp commentServiceImp;

    public void init() {
        eventServiceImp = new EventServiceImp(new EventRespository(), new CategorieRepository(), new UserRepository());
        commentServiceImp = new CommentServiceImp(new EventRespository());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("event") == null){
           response.sendRedirect("home");
           return;
        }else{
            Long id = Long.parseLong(request.getParameter("event"));
            Event event = null;
            Response res = eventServiceImp.getEvent(id);
            if(res.getStatus() == 200){
                event = (Event) res.getData();
                request.setAttribute("event",event);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/event/event.jsp");
                try {
                    dispatcher.forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().println("Error: " + e.getMessage());
                }
            }else{
                response.sendRedirect("home");
                return;
            }

        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // get add comment request
        String text = request.getParameter("text");
        Long event_id = Long.parseLong(request.getParameter("event_id"));
        User user = (User) request.getSession().getAttribute("LoggedUser");
        Response res  =  commentServiceImp.addComment(text,event_id,user);
        if(res.getStatus() == 200){
            response.sendRedirect("event_detail?event="+event_id);
        }else{
            response.sendRedirect("event_detail?event="+event_id+"&error="+res.getMessage());
        }

    }
}
