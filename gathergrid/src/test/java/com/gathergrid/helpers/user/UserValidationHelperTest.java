package com.gathergrid.helpers.user;

import org.junit.jupiter.api.Test;

import com.gathergrid.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserValidationHelperTest {

    private UserRepository userRepository = mock(UserRepository.class);

    private UserValidationHelper userValidationHelper = new UserValidationHelper(userRepository);

    @Test
    public void testNoUserHasThisEmailWithEmailExists() {

        when(userRepository.existsByEmail("saadmeddiche2004201@gmail.com")).thenReturn(true);

        boolean result = userValidationHelper.noUserHasThisEmail("saadmeddiche2004201@gmail.com");

        assertFalse(result); 
    }

    @Test
    public void testNoUserHasThisEmailWithEmailDoesNotExist() {

        when(userRepository.existsByEmail("notARealEmail@gmail.com")).thenReturn(false);

        boolean result = userValidationHelper.noUserHasThisEmail("notARealEmail@gmail.com");

        assertTrue(result); 
    }

    @Test
    public void testPasswordsAreNotMatchedWhenMatched() {
        String givedPassword = "password#2004";
        String fetchedPassword = "password#2004";

        boolean result = userValidationHelper.passwordsAreNotMatched(givedPassword, fetchedPassword);

        assertFalse(result);
    }

    @Test
    public void testPasswordsAreNotMatchedWhenNotMatched() {
        String givedPassword = "password#2004";
        String fetchedPassword = "password#2003";

        boolean result = userValidationHelper.passwordsAreNotMatched(givedPassword, fetchedPassword);

        assertTrue(result);
    }
}
