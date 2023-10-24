package com.gathergrid.Tests;

import java.util.Set;

import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import com.gathergrid.entities.User;
import com.gathergrid.factory.DbEntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TestingHibernateValidation {

    public static void main(String[] args) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        EntityManager entityManager = DbEntityManagerFactory.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        User newUser = new User("Saad", "Meddiche", "Saadoun", "saadmeddiche2004201@gmail.com", "22");

        Set<ConstraintViolation<User>> constraintViolationsInvalidUser = validator.validate(newUser);

        // If There Was Any Error
        if (constraintViolationsInvalidUser.size() > 0) {
            for (ConstraintViolation<User> constraintViolation : constraintViolationsInvalidUser) {
                System.out.println(constraintViolation.getMessage());
            }
            return;
        }

        transaction.begin();

        System.out.println("User Name: " + newUser.getName().getFirstName() + " " + newUser.getName().getLastName());
        System.out.println("User Email: " + newUser.getEmail().getAddressEmail());
        System.out.println("User Password: " + newUser.getPassword().getPassword());

        entityManager.persist(newUser);

        transaction.commit();

        entityManager.close();
    }
}
