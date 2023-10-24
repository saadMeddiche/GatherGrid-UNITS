package com.gathergrid.entities;

import com.gathergrid.embeddables.AddressEmail;
import com.gathergrid.embeddables.Name;
import com.gathergrid.embeddables.Password;

import jakarta.persistence.Embedded;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Valid
    private Name name;

    @Embedded
    @Valid
    private AddressEmail email;

    @Embedded
    @Valid
    private Password password;

    public User() {
    }

    public User(String firstName, String lastName, String username, String email, String password) {
        this.name = new Name(firstName, lastName, username);
        this.email = new AddressEmail(email);
        this.password = new Password(password);
    }

    public User(Name name, AddressEmail email, Password password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return this.name;

    }

    public void setName(Name name) {
        this.name = name;
    }

    public AddressEmail getEmail() {
        return this.email;
    }

    public void setEmail(AddressEmail email) {
        this.email = email;
    }

    public Password getPassword() {
        return this.password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                "}";
    }
}
