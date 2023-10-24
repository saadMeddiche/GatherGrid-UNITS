package com.gathergrid.servlet.authentification;

import java.io.IOException;

import com.gathergrid.entities.User;
import com.gathergrid.exceptions.factories.ExceptionHandlerFactory;
import com.gathergrid.exceptions.interfaces.ExceptionHandler;
import com.gathergrid.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "signUpServlet", urlPatterns = "/signUp")
@ServletSecurity
public class signUp extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/authentification");

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

            userService.registerUser(user);

            request.setAttribute("successCreationAccount", true);

        } catch (Exception e) {

            ExceptionHandler exceptionHandler = ExceptionHandlerFactory.getExceptionHandler(e);

            exceptionHandler.handleException(e, request);

        }

        request.setAttribute("userOnRegistration", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/authentification");

        dispatcher.forward(request, response);
    }
}
