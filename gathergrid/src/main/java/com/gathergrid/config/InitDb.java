package com.gathergrid.config;

import com.gathergrid.factory.DbEntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/initDb", loadOnStartup = 1)
public class InitDb extends HttpServlet {

    public void init() {
        
        try {
            super.init();
            DbEntityManagerFactory.getEntityManagerFactory();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}
