package com.gathergrid.servlet;

import com.gathergrid.entities.Event;
import com.gathergrid.entities.Response;
import com.gathergrid.entities.User;
import com.gathergrid.repository.CategorieRepository;
import com.gathergrid.repository.EventRespository;
import com.gathergrid.repository.UserRepository;
import com.gathergrid.service.CategoryService;
import com.gathergrid.service.EventService;
import com.gathergrid.service.imp.EventServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "editEvent", urlPatterns = "/myEvents/edit")
public class editEvent extends HttpServlet {
    EventService eventService;
    CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        CategorieRepository categorieRepository = new CategorieRepository();
        eventService = new EventServiceImp(new EventRespository(), categorieRepository, new UserRepository());
        categoryService = new CategoryService(categorieRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        try {
            id = req.getParameter("id") != null ? Long.valueOf(req.getParameter("id")) : null;
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/myEvents");
            return;
        }
        Response eventRes = eventService.getEvent(id);
        if (eventRes.getStatus() == 200) {
            Event event = (Event) eventRes.getData();
            req.setAttribute("event", event);
            req.setAttribute("categories", categoryService.getCategories().getData());
            req.getRequestDispatcher("/WEB-INF/event/edit.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("error", eventRes.getMessage());
            resp.sendRedirect(req.getContextPath() + "/myEvents");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedUser = (User) req.getSession().getAttribute("LoggedUser");
        Long editId;
        try {
            editId = req.getParameter("edit_id") != null ? Long.valueOf(req.getParameter("edit_id")) : null;
        }
        catch (NumberFormatException e){
            req.getSession().setAttribute("error", "Invalid Event Id");
            resp.sendRedirect(req.getContextPath() + "/myEvents");
            return;
        }
        if (editId != null) {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String dateTime = req.getParameter("datetime");
            String location = req.getParameter("location");
            Long categoryId = Long.valueOf(req.getParameter("category"));
            Integer vip_price = Integer.valueOf(req.getParameter("vip_price"));
            Integer regular_price = Integer.valueOf(req.getParameter("regular_price"));
            Integer basic_price = Integer.valueOf(req.getParameter("basic_price"));
            Response res = eventService.updateEvent(editId, name, description, location, dateTime, vip_price, regular_price, basic_price, categoryId, loggedUser.getId());
            if (res.getStatus() == 200) {
                req.getSession().setAttribute("success", res.getMessage());
                resp.sendRedirect(req.getContextPath() + "/myEvents");
            } else {
                req.getSession().setAttribute("error", res.getMessage());
                resp.sendRedirect(req.getContextPath() + "/myEvents");
            }
        }
        else {
            req.getSession().setAttribute("error", "Invalid Event Id");
            resp.sendRedirect(req.getContextPath() + "/myEvents");
        }
    }
}
