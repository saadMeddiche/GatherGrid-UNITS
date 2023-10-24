package com.gathergrid.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Embeddable
public class Name {

    @NotEmpty(message = "Please enter your First Name")
    @NotNull(message = "Please enter your First Name")
    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed")
    private String firstName;

    @NotEmpty(message = "Please enter your Last Name")
    @NotNull(message = "Please enter your last Name")
    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed")

    private String lastName;

    @NotEmpty(message = "Please enter your User Name")
    @NotNull(message = "Please enter your User Name")
    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed")
    private String userName;

    public Name() {
    }

    public Name(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
