package com.gathergrid.repository;

import java.util.List;

import com.gathergrid.factory.DbEntityManagerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public abstract class BaseRepository<E> {

    private final EntityManager entityManager;
    private final EntityTransaction transaction;

    protected BaseRepository() {
        entityManager = DbEntityManagerFactory.getEntityManager();
        transaction = entityManager.getTransaction();
    }

    /**
     * Saves an entity to the database.
     *
     * @param entity the entity to be saved
     */
    public void save(E entity) {
        // Begin a transaction
        transaction.begin();

        // Persist the entity to the database
        entityManager.persist(entity);

        // Commit the transaction
        transaction.commit();
    }

    /**
     * Updates the given entity in the database.
     *
     * @param entity the entity to be updated
     */
    public void update(E entity) {
        // Begin a new transaction
        transaction.begin();

        // Merge the updated entity into the entity manager
        entityManager.merge(entity);

        // Commit the transaction to save the changes
        transaction.commit();
    }

    /**
     * Fetches all entities of a given class from the database.
     *
     * @param entityClass the class of the entities to fetch
     * @return a list of entities of the given class
     */
    public List<E> fetchAll(Class<E> entityClass) {

        transaction.begin();

        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";

        TypedQuery<E> query = entityManager.createQuery(jpql, entityClass);

        List<E> entities = query.getResultList();

        transaction.commit();

        return entities;
    }

    /**
     * Retrieves an entity of type E based on the specified criteria.
     *
     * @param entityClass the class of the entity to be retrieved
     * @param fieldName   the name of the field to be used as the search criteria
     * @param value       the value to be used as the search criteria
     * @return the retrieved entity, or null if no entity is found
     */
    public E findBy(Class<E> entityClass, String fieldName, Object value) {

        // Begin a new transaction
        transaction.begin();

        // Create a JPQL query to retrieve the entity based on the specified criteria
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e." + fieldName + " = :value";
        TypedQuery<E> query = entityManager.createQuery(jpql, entityClass);

        // Set the value parameter in the query
        query.setParameter("value", value);

        // Get the single result of the query, which should be the retrieved entity
        E entity = query.getSingleResult();

        // Commit the transaction
        transaction.commit();

        // Return the retrieved entity
        return entity;
    }

    /**
     * Checks if an entity exists in the database based on a specific field value.
     *
     * @param entityClass the class of the entity to check
     * @param fieldName   the name of the field to check for
     * @param value       the value to search for in the field
     * @return true if an entity with the specified field value exists, false
     *         otherwise
     */
    protected boolean existsByField(Class<E> entityClass, String fieldName, Object value) {
        // Begin a transaction
        transaction.begin();

        // Create a JPQL query to count the number of entities with the specified field
        // value
        String jpql = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e WHERE e." + fieldName + " = :value";

        // Create a typed query with the JPQL query and the expected result type
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);

        // Set the parameter value for the query
        query.setParameter("value", value);

        // Execute the query and check if the result is greater than 0
        Boolean result = query.getSingleResult() > 0;

        // Commit the transaction
        transaction.commit();

        // Return the result
        return result;
    }

}
