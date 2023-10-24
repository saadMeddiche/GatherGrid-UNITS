package com.gathergrid.servlet.authentification;

import java.io.IOException;

import com.gathergrid.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "logOut", urlPatterns = "/logOut")
public class logOut extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userService.logoutUser(request);

        response.sendRedirect(request.getContextPath() + "/authentification");

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userService.logoutUser(request);

        response.sendRedirect(request.getContextPath() + "/authentification");

    }
}
