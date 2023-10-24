package com.gathergrid.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class AddressEmail {

    @NotEmpty(message = "Please enter your Email")
    @NotNull(message = "Please enter your Email")
    @Email(message = "Invalid email format")
    private String addressEmail;

    public AddressEmail() {
    }

    public AddressEmail(String addressEmail) {
        this.addressEmail = addressEmail;
    }

    public String getAddressEmail() {
        return addressEmail;
    }

    public void setAddressEmail(String addressEmail) {
        this.addressEmail = addressEmail;
    }
}
