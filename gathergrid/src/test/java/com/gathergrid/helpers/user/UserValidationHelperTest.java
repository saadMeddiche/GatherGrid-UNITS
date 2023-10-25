package com.gathergrid.helpers.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.gathergrid.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserValidationHelperTest {

    UserRepository userRepository;
    UserValidationHelper userValidationHelper;

    @BeforeEach
    public void setUpRepositoryAndUserValidationHelper() {
        userRepository = mock(UserRepository.class);
        userValidationHelper = new UserValidationHelper(userRepository);
    }

    @Nested
    class NoUserHasThisEmail {

        @Test
        public void shouldReturnFalseWhenEmailExists() {
            when(userRepository.existsByEmail("saadmeddiche2004201@gmail.com")).thenReturn(true);

            boolean result = userValidationHelper.noUserHasThisEmail("saadmeddiche2004201@gmail.com");

            assertFalse(result); 
        }

        @Test
        public void shouldReturnTrueWhenEmailDoesNotExist() {
            when(userRepository.existsByEmail("notARealEmail@gmail.com")).thenReturn(false);

            boolean result = userValidationHelper.noUserHasThisEmail("notARealEmail@gmail.com");

            assertTrue(result); 
        }
    }

    @Nested
    class PasswordsAreNotMatched {

        @Test
        public void shouldReturnFalseWhenPasswordsMatch() {
            String givenPassword = "password#2004";
            String fetchedPassword = "password#2004";

            boolean result = userValidationHelper.passwordsAreNotMatched(givenPassword, fetchedPassword);

            assertFalse(result);
        }

        @Test
        public void shouldReturnTrueWhenPasswordsDoNotMatch() {
            String givenPassword = "password#2004";
            String fetchedPassword = "password#2003";

            boolean result = userValidationHelper.passwordsAreNotMatched(givenPassword, fetchedPassword);

            assertTrue(result);
        }

        @Test
        public void shouldReturnTrueWhenGivenPasswordIsNull() {
            String givenPassword = null;
            String fetchedPassword = "password#2003";

            boolean result = userValidationHelper.passwordsAreNotMatched(givenPassword, fetchedPassword);

            assertTrue(result);
        }

        @Test
        public void shouldReturnTrueWhenFetchedPasswordIsNull() {
            String givenPassword = "password#2004";
            String fetchedPassword = null;

            boolean result = userValidationHelper.passwordsAreNotMatched(givenPassword, fetchedPassword);

            assertTrue(result);
        }

        @Test
        public void shouldReturnTrueWhenPasswordsAreEmpty() {
            String givenPassword = "";
            String fetchedPassword = "password#2004";

            boolean result = userValidationHelper.passwordsAreNotMatched(givenPassword, fetchedPassword);

            assertTrue(result);
        }
    }
}
