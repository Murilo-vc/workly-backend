package com.murilovc.workly.util;

import com.murilovc.workly.domain.constant.FieldErrorConstants;
import com.murilovc.workly.domain.dto.FieldErrorDto;
import com.murilovc.workly.domain.enumeration.field.UserFieldEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidationUtils {

    public static FieldErrorDto validateUsername(final String text) {
        if (text == null || text.isBlank()) {
            return FieldErrorDto.toDto(UserFieldEnum.USERNAME.getId(), FieldErrorConstants.REQUIRED);
        }

        final String username = text.strip();

        if (username.length() < 3) {
            return FieldErrorDto.toDto(UserFieldEnum.USERNAME.getId(), FieldErrorConstants.TOO_SHORT);
        }

        if (username.length() > 20) {
            return FieldErrorDto.toDto(UserFieldEnum.USERNAME.getId(), FieldErrorConstants.TOO_LONG);
        }

        if (username.contains(" ") || ValidationUtils.containsSpecialCharacter(username)) {
            return FieldErrorDto.toDto(UserFieldEnum.USERNAME.getId(), FieldErrorConstants.INVALID_FORMAT);
        }

        return null;
    }

    public static FieldErrorDto validateName(final String text) {
        if (text == null || text.isBlank()) {
            return FieldErrorDto.toDto(UserFieldEnum.NAME.getId(), FieldErrorConstants.REQUIRED);
        }

        final String name = text.strip();

        if (name.length() < 4) {
            return FieldErrorDto.toDto(UserFieldEnum.NAME.getId(), FieldErrorConstants.TOO_SHORT);
        }

        if (name.length() > 150) {
            return FieldErrorDto.toDto(UserFieldEnum.NAME.getId(), FieldErrorConstants.TOO_LONG);
        }

        return null;
    }

    public static FieldErrorDto validatePassword(final String text) {
        if (text == null || text.isBlank()) {
            return FieldErrorDto.toDto(UserFieldEnum.PASSWORD.getId(), FieldErrorConstants.REQUIRED);
        }

        final String password = text.strip();

        if (password.length() < 3) {
            return FieldErrorDto.toDto(UserFieldEnum.PASSWORD.getId(), FieldErrorConstants.TOO_SHORT);
        }

        if (password.length() > 20) {
            return FieldErrorDto.toDto(UserFieldEnum.PASSWORD.getId(), FieldErrorConstants.TOO_LONG);
        }

        if (password.contains(" ") || ValidationUtils.containsSpecialCharacter(password)) {
            return FieldErrorDto.toDto(UserFieldEnum.PASSWORD.getId(), FieldErrorConstants.INVALID_FORMAT);
        }

        return null;
    }

    public static FieldErrorDto validateEmail(final String text) {
        return null;
    }

    public static FieldErrorDto validatePhone(final String text) {
        if (text == null) {
            return null;
        }

        final String phone = text.strip();

        if (phone.length() < 10) {
            return FieldErrorDto.toDto(UserFieldEnum.PHONE.getId(), FieldErrorConstants.TOO_SHORT);
        }

        if (phone.length() > 14) {
            return FieldErrorDto.toDto(UserFieldEnum.PHONE.getId(), FieldErrorConstants.TOO_LONG);
        }

        return null;
    }

    public static FieldErrorDto validateExperience(final String text) {
        if (text == null) {
            return null;
        }

        final String experience = text.strip();

        if (experience.length() < 10) {
            return FieldErrorDto.toDto(UserFieldEnum.EXPERIENCE.getId(), FieldErrorConstants.TOO_SHORT);
        }

        if (experience.length() > 600) {
            return FieldErrorDto.toDto(UserFieldEnum.EXPERIENCE.getId(), FieldErrorConstants.TOO_LONG);
        }

        return null;
    }

    public static FieldErrorDto validateEducation(final String text) {
        if (text == null) {
            return null;
        }

        final String education = text.strip();

        if (education.length() < 10) {
            return FieldErrorDto.toDto(UserFieldEnum.EDUCATION.getId(), FieldErrorConstants.TOO_SHORT);
        }

        if (education.length() > 600) {
            return FieldErrorDto.toDto(UserFieldEnum.EDUCATION.getId(), FieldErrorConstants.TOO_LONG);
        }

        return null;
    }

    public static boolean containsSpecialCharacter(final String string) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        return m.find();
    }
}
