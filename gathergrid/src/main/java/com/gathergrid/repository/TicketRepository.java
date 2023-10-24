package com.gathergrid.repository;

import com.gathergrid.entities.Ticket;
import com.gathergrid.factory.DbEntityManagerFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TicketRepository {
    public void save(Ticket ticket) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        em.persist(ticket);
        em.getTransaction().commit();
    }
    public void update(Ticket ticket) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        em.merge(ticket);
        em.getTransaction().commit();
    }
    public void delete(Ticket ticket) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(ticket) ? ticket : em.merge(ticket));
        em.getTransaction().commit();
    }
    public Ticket findById(Long id) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        Ticket ticket = em.find(Ticket.class,id);
        em.getTransaction().commit();
        return ticket;
    }
    public List<Ticket> findAll() {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        java.util.List<Ticket> tickets = em.createQuery("select t from Ticket t",Ticket.class).getResultList();
        em.getTransaction().commit();
        return tickets;
    }
    public List<Ticket> findByEvent(Long id) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        java.util.List<Ticket> tickets = em.createQuery("select t from Ticket t where t.event.id = :id",Ticket.class).setParameter("id",id).getResultList();
        em.getTransaction().commit();
        return tickets;
    }
    public List<Ticket> findByUser(Long id) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        java.util.List<Ticket> tickets = em.createQuery("select t from Ticket t where t.user.id = :id",Ticket.class).setParameter("id",id).getResultList();
        em.getTransaction().commit();
        return tickets;
    }
    

}
