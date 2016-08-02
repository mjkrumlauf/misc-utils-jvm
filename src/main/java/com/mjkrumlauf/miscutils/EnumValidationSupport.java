package com.mjkrumlauf.miscutils;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang.Validate;

/**
 * {@code EnumValidationSupport}
 *
 * @author mjkrumlauf
 */
public interface EnumValidationSupport {

    /**
     * Provides validation of {@code Enum.valueOf} that throws an
     * {@link IllegalArgumentException} instead of a {@link NullPointerException}
     * when the {@code value} is null.
     *
     * @param enumClass Class of enum
     * @param value String representation of enum value
     * @param <T> Type of the enum
     * @return
     */
    default <T extends Enum<T>> T enumValueFrom(final Class<T> enumClass, final String value) {
        Validate.notNull(enumClass, "Enum type must not be null");

        Validate.notEmpty(value,
            "String representation of enum must not be empty or null, valid values are " +
                validValuesFrom(enumClass));

        return Enum.valueOf(enumClass, value);
    }

    default <T extends Enum<T>> String validValuesFrom(final Class<T> enumClass) {
        return
            Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::toString)
                .collect(Collectors.joining(","));
    }
}
