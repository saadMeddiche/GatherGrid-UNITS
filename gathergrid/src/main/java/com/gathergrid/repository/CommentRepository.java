package com.gathergrid.repository;

import com.gathergrid.entities.Comment;
import com.gathergrid.factory.DbEntityManagerFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CommentRepository {
    public void save(Comment comment) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        em.persist(comment);
        em.getTransaction().commit();
    }
    public void update(Comment comment) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        em.merge(comment);
        em.getTransaction().commit();
    }
    public void delete(Comment comment) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(comment) ? comment : em.merge(comment));
        em.getTransaction().commit();
    }
    public Comment findById(Long id) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        Comment comment = em.find(Comment.class,id);
        em.getTransaction().commit();
        return comment;
    }
    public List<Comment> findAll() {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        java.util.List<Comment> comments = em.createQuery("select c from Comment c",Comment.class).getResultList();
        em.getTransaction().commit();
        return comments;
    }
    public List<Comment> findAllByEvent(Long id) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        java.util.List<Comment> comments = em.createQuery("select c from Comment c where c.event.id = :id",Comment.class).setParameter("id",id).getResultList();
        em.getTransaction().commit();
        return comments;
    }
}
