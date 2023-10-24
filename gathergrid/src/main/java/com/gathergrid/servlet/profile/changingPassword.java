package com.gathergrid.servlet.profile;

import java.io.IOException;

import com.gathergrid.exceptions.factories.ExceptionHandlerFactory;
import com.gathergrid.exceptions.interfaces.ExceptionHandler;
import com.gathergrid.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "changePasswordServlet", urlPatterns = "/changePassword")
public class changingPassword extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/profile/changePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String currentPassword = request.getParameter("current-password");
        String newPassword = request.getParameter("new-password");
        String repeatNewPassword = request.getParameter("repeat-new-password");

        try {

            userService.changePassword(currentPassword, newPassword, repeatNewPassword, request);

            request.getSession().setAttribute("successChangingPassword", true);

        } catch (Exception e) {

            ExceptionHandler exceptionHandler = ExceptionHandlerFactory.getExceptionHandler(e);

            exceptionHandler.handleException(e, request);

        }

        response.sendRedirect(request.getContextPath() + "/changePassword");
    }
}
