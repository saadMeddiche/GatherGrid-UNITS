package com.gathergrid.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "bookServlet", urlPatterns = "/books")
public class hello extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
//        EntityManagerFactory emf =     jakarta.persistence.Persistence.createEntityManagerFactory("my-ana");
//        jakarta.persistence.EntityManager em = emf.createEntityManager();
////        em.getTransaction().begin();
////        em.persist(test);
////        em.getTransaction().commit();
//        em.close();
//        emf.close();
//        out.println("<h1>test</h1>");
//        out.println("</body></html>");
    }
    public static void setTest() {
    }
    public void destroy() {
    }
}
