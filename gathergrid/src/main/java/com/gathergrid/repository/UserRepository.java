package com.gathergrid.repository;

import com.gathergrid.entities.User;

public class UserRepository extends BaseRepository<User> {

    /**
     * Finds a user by their email address.
     *
     * @param  email  the email address of the user
     * @return        the user object with the specified email address
     */
    public User findByEmail(String email) {
        return findBy(User.class, "email.addressEmail", email);
    }

    /**
     * Finds a user by their ID.
     *
     * @param  id   the ID of the user to find
     * @return      the user with the specified ID, or null if no user is found
     */
    public User findById(Long id) {
        return findBy(User.class, "id", id);
    }

    /**
     * Check if a user exists by email address.
     *
     * @param  email  the email address to check
     * @return        true if a user exists with the given email address, false otherwise
     */
    public boolean existsByEmail(String email) {
        return existsByField(User.class, "email.addressEmail", email);
    }

    /**
     * Determines if a user exists by their username.
     *
     * @param  username  the username of the user to check
     * @return           true if a user with the given username exists, false otherwise
     */
    public boolean existsByUsername(String username) {
        return existsByField(User.class, "name.userName", username);
    }

}
