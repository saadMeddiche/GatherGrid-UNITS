package com.gathergrid.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.gathergrid.entities.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthentificationFilter implements Filter {

    private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;

    private final List<String> reachablePathsWithoutAuthentication = Arrays.asList("/authentification", "/signUp",
            "/signIn");

    Predicate<User> noAccessToThisRoute = loggedUser -> {

        if (loggedUser != null && reachablePathWithoutLogging()) {
            return true;
        }

        return loggedUser == null && !reachablePathWithoutLogging();
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        httpRequest = (HttpServletRequest) request;
        httpResponse = (HttpServletResponse) response;

        HttpSession httpSession = httpRequest.getSession();

        User loggedUser = (User) httpSession.getAttribute("LoggedUser");

        // This Condition Is For Not returning to anthetifications servlets if the user
        // already Logged In
        if (loggedUser != null && reachablePathWithoutLogging()) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
            return;
        }

        // This Condition Is For Not Accesseding Other Pages Without Logging In
        if (noAccessToThisRoute.test(loggedUser)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() +
                    "/authentification");
            return;
        }

        chain.doFilter(request, response);

    }

    public boolean reachablePathWithoutLogging() {
        String url = httpRequest.getRequestURL().toString();
        return reachablePathsWithoutAuthentication.stream().anyMatch(url::contains);
    }

}
