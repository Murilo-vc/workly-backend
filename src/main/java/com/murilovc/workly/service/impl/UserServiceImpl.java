package com.murilovc.workly.service.impl;

import com.murilovc.workly.domain.dto.FieldErrorDto;
import com.murilovc.workly.domain.dto.UserDto;
import com.murilovc.workly.domain.entity.User;
import com.murilovc.workly.domain.enumeration.RoleKeyEnum;
import com.murilovc.workly.domain.payload.UserCreatePayload;
import com.murilovc.workly.domain.payload.UserUpdatePayload;
import com.murilovc.workly.handler.exception.ForbiddenException;
import com.murilovc.workly.handler.exception.UserNotFoundException;
import com.murilovc.workly.handler.exception.UsernameAlreadyExistsException;
import com.murilovc.workly.handler.exception.ValidationErrorException;
import com.murilovc.workly.repository.UserRepository;
import com.murilovc.workly.security.user.AppUserDetails;
import com.murilovc.workly.service.AuthService;
import com.murilovc.workly.service.UserService;
import com.murilovc.workly.util.SecurityUtils;
import com.murilovc.workly.util.ValidationUtils;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final AuthService authService;

    private final UserRepository userRepository;

    public UserServiceImpl(final AuthService authService,
                           final UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getUserById(@NonNull @NotNull final Long id) throws UserNotFoundException, ForbiddenException {
        final AppUserDetails currentUser = SecurityUtils.getCurrentUser();

        if (!currentUser.getId().equals(id)) {
            throw new ForbiddenException();
        }

        final User user = this.userRepository.findOneById(currentUser.getId())
            .orElseThrow(UserNotFoundException::new);

        return UserDto.toDto(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(@NonNull @NotNull final UserCreatePayload payload) throws UsernameAlreadyExistsException, ValidationErrorException {
        final String username = payload.getUsername();
        final String password = payload.getPassword();
        final String name = payload.getName();
        final String email = payload.getEmail();
        final String phone = payload.getPhone();
        final String experience = payload.getExperience();
        final String education = payload.getEducation();

        if (this.userRepository.existsByUsernameIgnoreCase(username)) {
            throw new UsernameAlreadyExistsException();
        }

        this.validateCreateFields(payload);

        final User user = new User(
            username,
            name,
            password,
            RoleKeyEnum.USER,
            email,
            phone,
            experience,
            education
        );

        this.userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(@NonNull @NotNull final Long id,
                       @NotNull @NotNull final UserUpdatePayload payload) throws ForbiddenException, UserNotFoundException, ValidationErrorException {
        final AppUserDetails currentUser = SecurityUtils.getCurrentUser();

        if (!currentUser.getId().equals(id)) {
            throw new ForbiddenException();
        }

        this.validateUpdateFields(payload);

        final User user = this.userRepository.findOneById(currentUser.getId())
            .orElseThrow(UserNotFoundException::new);

        user.setName(payload.getName());
        user.setEmail(payload.getEmail());
        user.setPassword(payload.getPassword());
        user.setPhone(payload.getPhone());
        user.setExperience(payload.getExperience());
        user.setEducation(payload.getEducation());

        this.userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NonNull @NotNull final Long id) throws ForbiddenException, UserNotFoundException {
        final AppUserDetails currentUser = SecurityUtils.getCurrentUser();

        if (!currentUser.getId().equals(id)) {
            throw new ForbiddenException();
        }

        final User user = this.userRepository.findOneById(currentUser.getId())
            .orElseThrow(UserNotFoundException::new);

        this.authService.logout();

        this.userRepository.delete(user);
    }

    private void validateCreateFields(final UserCreatePayload payload) throws ValidationErrorException {
        List<FieldErrorDto> errors = new ArrayList<>();

        final FieldErrorDto usernameError = ValidationUtils.validateUsername(payload.getUsername());
        if (usernameError != null) {
            errors.add(usernameError);
        }

        final FieldErrorDto nameError = ValidationUtils.validateName(payload.getName());
        if (nameError != null) {
            errors.add(nameError);
        }

        final FieldErrorDto passwordError = ValidationUtils.validatePassword(payload.getPassword());
        if (passwordError != null) {
            errors.add(passwordError);
        }

        final FieldErrorDto emailError = ValidationUtils.validateEmail(payload.getEmail());
        if (emailError != null) {
            errors.add(emailError);
        }

        final FieldErrorDto phoneError = ValidationUtils.validatePhone(payload.getPhone());
        if (phoneError != null) {
            errors.add(phoneError);
        }

        final FieldErrorDto experienceError = ValidationUtils.validateExperience(payload.getExperience());
        if (experienceError != null) {
            errors.add(experienceError);
        }

        final FieldErrorDto educationError = ValidationUtils.validateEducation(payload.getEducation());
        if (educationError != null) {
            errors.add(educationError);
        }

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }
    }

    private void validateUpdateFields(final UserUpdatePayload payload) throws ValidationErrorException {
        List<FieldErrorDto> errors = new ArrayList<>();

        final FieldErrorDto nameError = ValidationUtils.validateName(payload.getName());
        if (nameError != null) {
            errors.add(nameError);
        }

        final FieldErrorDto emailError = ValidationUtils.validateEmail(payload.getEmail());
        if (emailError != null) {
            errors.add(emailError);
        }

        final FieldErrorDto passwordError = ValidationUtils.validatePassword(payload.getPassword());
        if (passwordError != null) {
            errors.add(passwordError);
        }

        final FieldErrorDto phoneError = ValidationUtils.validatePhone(payload.getPhone());
        if (phoneError != null) {
            errors.add(phoneError);
        }

        final FieldErrorDto experienceError = ValidationUtils.validateExperience(payload.getExperience());
        if (experienceError != null) {
            errors.add(experienceError);
        }

        final FieldErrorDto educationError = ValidationUtils.validateEducation(payload.getEducation());
        if (educationError != null) {
            errors.add(educationError);
        }

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }
    }
}
