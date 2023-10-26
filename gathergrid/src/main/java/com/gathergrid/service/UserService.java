package com.gathergrid.service;

import com.gathergrid.embeddables.Password;
import com.gathergrid.entities.User;
import com.gathergrid.helpers.user.UserValidationHelper;
import com.gathergrid.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

public class UserService extends UserValidationHelper {

    public UserService() {
        super(new UserRepository());
    }
    
    /**
     * Registers a user.
     *
     * @param user the user to be registered
     */
    public void registerUser(User user) {
        // Validate the user
        validateUser(user);

        // Add the user account
        addAccount(user);
    }
    

    /**
     * Logs in a user.
     * 
     * @param givenUser The user to be logged in.
     * @param request   The HTTP servlet request object.
     */
    public void loginUser(User givenUser, HttpServletRequest request) {

        // Validate the email format
        validateEmail(givenUser.getEmail());

        // Fetch the user by email
        User fetchedUser = getUserByEmail(givenUser.getEmail().getAddressEmail());

        // Validate the user's password
        validatePassword(givenUser, fetchedUser);

        // Store the logged-in user in the session
        storeLoggedUserInSession(fetchedUser, request);
    }

    /**
     * Updates the profile of a given user.
     *
     * @param givenUser The user object with the updated profile information.
     * @param request   The HTTP servlet request object.
     */
    public void updateProfile(User givenUser, HttpServletRequest request) {

        // Validate the given user on update
        // Validate the given user object for any update errors
        validateUserOnUpdate(givenUser, request);

        // Get the logged-in user from the session by their ID
        User fetchedUser = getUserById(getStoredLoggedUserFromSession(request).getId());

        // Validate the given user's password against the fetched user's password
        validatePassword(givenUser, fetchedUser);

        // Update the user's account with the given user's profile information
        User updatedUser = updateAccount(givenUser, request);

        // Update the logged-in user in the session with the updated user object
        updateLoggedUserInSession(updatedUser, request);
    }

    /**
     * Changes the password for the current user.
     *
     * @param currentPassword   The current password of the user.
     * @param newPassword       The new password to set.
     * @param repeatNewPassword The repeated new password for confirmation.
     * @param request           The HTTP servlet request.
     */
    public void changePassword(String currentPassword, String newPassword, String repeatNewPassword,
            HttpServletRequest request) {

        // Get the current user object by retrieving the logged-in user from the session
        User user = getUserById(getStoredLoggedUserFromSession(request).getId());

        // Validate the passwords by comparing the current password with the provided
        // current password
        // and checking if the new password and the repeat new password match
        validatePasswords(user.getPassword().getPassword(), currentPassword, newPassword, repeatNewPassword);

        // Set the new password for the user
        user.setPassword(new Password(newPassword));

        // Update the user account with the new password
        updateAccount(user, request);
    }

    /**
     * Method to log out a user.
     *
     * @param request the HttpServletRequest object containing the user's request
     */
    public void logoutUser(HttpServletRequest request) {
        // Remove the "LoggedUser" attribute from the session
        request.getSession().removeAttribute("LoggedUser");
    }

}
