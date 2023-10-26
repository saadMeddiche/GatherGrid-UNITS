package com.gathergrid.helpers.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import com.gathergrid.entities.User;
import com.gathergrid.exceptions.costums.AlreadyExistsException;
import com.gathergrid.exceptions.costums.ValidationException;
import com.gathergrid.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

        /**
         * A test to verify that "NoUserHasThisEmail" returns false when the specified email already exists.
         *
         */
        @Test
        public void khasReturnFalseWhenEmailExists() {
            when(userRepository.existsByEmail("saadmeddiche2004201@gmail.com")).thenReturn(true);

            boolean result = userValidationHelper.noUserHasThisEmail("saadmeddiche2004201@gmail.com");

            assertFalse(result); 
        }

        /**
         * A test case to verify that the method "noUserHasThisEmail" returns true
         * when the email does not exist in the user repository.
         *
         */
        @Test
        public void khasReturnTrueWhenEmailDoesNotExist() {
            when(userRepository.existsByEmail("notARealEmail@gmail.com")).thenReturn(false);

            boolean result = userValidationHelper.noUserHasThisEmail("notARealEmail@gmail.com");

            assertTrue(result); 
        }
    }

    @Nested
    class PasswordsAreNotMatched {

        /**
         * Test case to verify that the 'passwordsAreNotMatched' function returns false when the given password
         * matches the fetched password.
         *
         */
        @Test
        public void khasReturnFalseWhenPasswordsMatch() {
            String givenPassword = "password#2004";
            String fetchedPassword = "password#2004";

            boolean result = userValidationHelper.passwordsAreNotMatched(givenPassword, fetchedPassword);

            assertFalse(result);
        }

        /**
         * A test case to verify that the method 'passwordsAreNotMatched' returns true
         * when the given password and the fetched password do not match.
         *
         */
        @Test
        public void khasReturnTrueWhenPasswordsDoNotMatch() {
            String givenPassword = "password#2004";
            String fetchedPassword = "password#2003";

            boolean result = userValidationHelper.passwordsAreNotMatched(givenPassword, fetchedPassword);

            assertTrue(result);
        }

        /**
         * Test case to verify that the method `passwordsAreNotMatched` returns
         * `true` when given password is null.
         */
        @Test
        public void khasReturnTrueWhenGivenPasswordIsNull() {
            String givenPassword = null;
            String fetchedPassword = "password#2003";

            boolean result = userValidationHelper.passwordsAreNotMatched(givenPassword, fetchedPassword);

            assertTrue(result);
        }

        /**
         * Test case to verify if the method `passwordsAreNotMatched` returns `true`
         * when the fetched password is null.
         *
         */
        @Test
        public void khasReturnTrueWhenFetchedPasswordIsNull() {
            String givenPassword = "password#2004";
            String fetchedPassword = null;

            boolean result = userValidationHelper.passwordsAreNotMatched(givenPassword, fetchedPassword);

            assertTrue(result);
        }

        /**
         * A test case to verify that the method 'passwordsAreNotMatched' returns true 
         * when the given password is empty and the fetched password is not empty.
         *
         */
        @Test
        public void khasReturnTrueWhenPasswordsAreEmpty() {
            String givenPassword = "";
            String fetchedPassword = "password#2004";

            boolean result = userValidationHelper.passwordsAreNotMatched(givenPassword, fetchedPassword);

            assertTrue(result);
        }
    }

    @Nested
    class validateUser {

        /**
         * A test case to validate that the function throws an AlreadyExistsException
         * when the email already exists in the user repository.
         */
        @Test
        public void khasThrowAlreadyExistsExceptionWhenEmailAlreadyExists() {
            User user = new User("Saad", "Meddiche", "SaadOun", "saadmeddiche2004201@gmail.com", "Password#2004");
            when(userRepository.existsByEmail(user.getEmail().getAddressEmail())).thenReturn(true);
            when(userRepository.existsByUsername(user.getName().getUserName())).thenReturn(false);

            assertThrows(AlreadyExistsException.class, () -> {
                userValidationHelper.validateUser(user);
            });
        }

        /**
         * Test method to verify that the function throws an AlreadyExistsException
         * when the username already exists.
         */
        @Test
        public void khasThrowAlreadyExistsExceptionWhenUserNameAlreadyExists() {
            User user = new User("Saad", "Meddiche", "SaadOun", "saadmeddiche2004201@gmail.com", "Password#2004");
            when(userRepository.existsByEmail(user.getEmail().getAddressEmail())).thenReturn(false);
            when(userRepository.existsByUsername(user.getName().getUserName())).thenReturn(true);

            assertThrows(AlreadyExistsException.class, () -> {
                userValidationHelper.validateUser(user);
            });
        }

        /**
         * Test case to verify that the function does not throw any exception when all conditions are good.
         */
        @Test
        public void khasNotThrowExceptionWhenAllIsGood() {
            User user = new User("Saad", "Meddiche", "SaadOun", "saadmeddiche2004201@gmail.com", "Password#2004");
            when(userRepository.existsByEmail(user.getEmail().getAddressEmail())).thenReturn(false);
            when(userRepository.existsByUsername(user.getName().getUserName())).thenReturn(false);

            assertDoesNotThrow(() -> {
                userValidationHelper.validateUser(user);
            });
        }

        /**
         * Generates a stream of dynamic tests to validate that the function throws a ValidationException
         * when some attributes of the User object are null.
         */
        @TestFactory
        public Stream<DynamicTest> khasThrowValidationExceptionWhenSomeAttributesAreNull() {

            List<User> users = new ArrayList<>();
            users.add(new User(null, "Meddiche", "SaadOun", "saadmeddiche2004201@gmail.com", "Password#2004"));
            users.add(new User("Saad", null, "SaadOun", "saadmeddiche2004201@gmail.com", "Password#2004"));
            users.add(new User("Saad", "Meddiche", null, "saadmeddiche2004201@gmail.com", "Password#2004"));
            users.add(new User("Saad", "Meddiche", "SaadOun", null, "Password#2004"));
            users.add(new User("Saad", "Meddiche", "SaadOun", "saadmeddiche2004201@gmail.com", null));

            return users.stream().map(user -> DynamicTest.dynamicTest(user.toString(), () -> {

                assertThrows(ValidationException.class, () -> {
                    userValidationHelper.validateUser(user);
                });

            }));

        }

    }
}
