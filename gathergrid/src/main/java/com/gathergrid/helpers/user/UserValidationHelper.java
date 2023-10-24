package com.gathergrid.helpers.user;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.gathergrid.embeddables.AddressEmail;
import com.gathergrid.embeddables.Name;
import com.gathergrid.embeddables.Password;
import com.gathergrid.entities.User;
import com.gathergrid.exceptions.costums.AlreadyExistsException;
import com.gathergrid.exceptions.costums.DoNotExistsException;
import com.gathergrid.exceptions.costums.NotMatchedException;
import com.gathergrid.exceptions.costums.ValidationException;
import com.gathergrid.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class UserValidationHelper {

    private final UserRepository userRepository;
    private final Validator validator;

    public UserValidationHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    protected User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    protected User getUserById(Long id) {
        return userRepository.findById(id);
    }

    protected User getStoredLoggedUserFromSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("LoggedUser");
    }

    protected void addAccount(User user) {
        userRepository.save(user);
    }

    protected User updateAccount(User user, HttpServletRequest request) {
        user.setId(getStoredLoggedUserFromSession(request).getId());
        userRepository.update(user);
        return user;
    }

    protected void storeLoggedUserInSession(User user, HttpServletRequest request) {
        request.getSession().setAttribute("LoggedUser", user);
    }

    protected void updateLoggedUserInSession(User user, HttpServletRequest request) {
        request.getSession().setAttribute("LoggedUser", user);
    }

    protected void validateEmail(AddressEmail addressEmail) {

        validateObject(addressEmail);

        if (noUserHasThisEmail(addressEmail.getAddressEmail())) {
            throw new DoNotExistsException("This Email Do Not Exist");
        }
    }

    protected void validateUser(User user) {

        validateObject(user);

        if (emailAlreadyExists(user.getEmail().getAddressEmail())) {
            throw new AlreadyExistsException("Email is already exists");
        }

        if (userNameAlreadyExists(user.getName().getUserName())) {
            throw new AlreadyExistsException("Username is already exists");
        }

    }

    protected void validateUserOnUpdate(User user, HttpServletRequest request) {

        validateObject(user);

        Long userId = getStoredLoggedUserFromSession(request).getId();

        if (userHasUpdatesHisEmail(userId, user.getEmail()) && emailAlreadyExists(user.getEmail().getAddressEmail())) {
            throw new AlreadyExistsException("Email is already exists");
        }

        if (userHasUpdatesHisUserName(userId, user.getName()) && userNameAlreadyExists(user.getName().getUserName())) {
            throw new AlreadyExistsException("Username is already exists");
        }

    }

    protected void validatePassword(User givenUser, User fetchedUser) {

        validateObject(givenUser.getPassword());

        String givedPassword = givenUser.getPassword().getPassword();

        String fetchedPassword = fetchedUser.getPassword().getPassword();

        if (passwordsAreNotMatched(givedPassword, fetchedPassword)) {
            throw new NotMatchedException("Password Is Incorrect");
        }
    }

    protected <O> void validateObject(O object) {

        Set<ConstraintViolation<O>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            List<String> errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            throw new ValidationException(errors);
        }
    }

    protected void validatePasswords(String userPassword, String currentPassword, String newPassword,
            String repeatNewPassword) {

        if (passwordsAreNotMatched(userPassword, currentPassword)) {
            throw new NotMatchedException("The current password is incrorrect");
        }

        validateObject(new Password(newPassword));

        if (passwordsAreNotMatched(newPassword, repeatNewPassword)) {
            throw new NotMatchedException("Please repeat the same password");
        }
    }

    protected boolean noUserHasThisEmail(String email) {
        return !userRepository.existsByEmail(email);
    }

    protected boolean passwordsAreNotMatched(String givedPassword, String fetchedPassword) {
        return !givedPassword.equals(fetchedPassword);
    }

    protected boolean userNameAlreadyExists(String userName) {
        return userRepository.existsByUsername(userName);
    }

    protected boolean emailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }

    protected boolean userHasUpdatesHisEmail(Long userId, AddressEmail email) {
        return !getUserById(userId).getEmail().getAddressEmail().equals(email.getAddressEmail());
    }

    protected boolean userHasUpdatesHisUserName(Long userId, Name name) {
        return !getUserById(userId).getName().getUserName().equals(name.getUserName());
    }

}
