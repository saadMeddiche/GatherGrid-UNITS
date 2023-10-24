package com.gathergrid.repository;

import com.gathergrid.entities.Categorie;
import com.gathergrid.factory.DbEntityManagerFactory;

import jakarta.persistence.EntityManager;

import java.util.List;

public class CategorieRepository {
    public void save(Categorie categorie) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        em.persist(categorie);
        em.getTransaction().commit();
    }
    public void update(Categorie categorie) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        em.merge(categorie);
        em.getTransaction().commit();
    }
    public void delete(Categorie categorie) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(categorie) ? categorie : em.merge(categorie));
        em.getTransaction().commit();
    }
    public Categorie findById(Long id) {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        Categorie categorie = em.find(Categorie.class,id);
        em.getTransaction().commit();
        return categorie;
    }
    public List<Categorie> findAll() {
        EntityManager em = DbEntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        java.util.List<Categorie> categories = em.createQuery("select c from Categorie c",Categorie.class).getResultList();
        em.getTransaction().commit();
        return categories;
    }


}
