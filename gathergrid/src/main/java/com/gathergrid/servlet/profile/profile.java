package com.gathergrid.servlet.profile;

import java.io.IOException;

import com.gathergrid.entities.User;
import com.gathergrid.exceptions.factories.ExceptionHandlerFactory;
import com.gathergrid.exceptions.interfaces.ExceptionHandler;
import com.gathergrid.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "profileServlet", urlPatterns = "/profile")
public class profile extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("LoggedUser");

        request.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/profile/profile.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(firstName, lastName, userName, email, password);

        try {

            userService.updateProfile(user, request);

            request.getSession().setAttribute("successUpdationAccount", true);

        } catch (Exception e) {

            ExceptionHandler exceptionHandler = ExceptionHandlerFactory.getExceptionHandler(e);

            exceptionHandler.handleException(e, request);

        }

        response.sendRedirect(request.getContextPath() + "/profile");

    }
}
