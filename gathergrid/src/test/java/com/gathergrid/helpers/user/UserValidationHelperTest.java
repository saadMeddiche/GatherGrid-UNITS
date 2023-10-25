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
    public void setRepositoryAndUserValidationHelper() {
        userRepository = mock(UserRepository.class);
        userValidationHelper = new UserValidationHelper(userRepository);
    }

    @Nested
    class NoUserHasThisEmail {
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
    }

    @Nested
    class passwordsAreNotMatched {
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

        @Test
        public void testPasswordsAreNotMatchedWhenGivedPasswordNull() {
            String givedPassword = null;
            String fetchedPassword = "password#2003";

            boolean result = userValidationHelper.passwordsAreNotMatched(givedPassword, fetchedPassword);

            assertTrue(result);
        }

        @Test
        public void testPasswordsAreNotMatchedWhenFetchedPasswordNull() {
            String givedPassword = "password#2004";
            String fetchedPassword = null;

            boolean result = userValidationHelper.passwordsAreNotMatched(givedPassword, fetchedPassword);

            assertTrue(result);
        }

        @Test
        public void testPasswordsAreNotMatchedWhenEmpty() {
            String givedPassword = "";
            String fetchedPassword = "password#2004";

            boolean result = userValidationHelper.passwordsAreNotMatched(givedPassword, fetchedPassword);

            assertTrue(result);
        }
    }

}
