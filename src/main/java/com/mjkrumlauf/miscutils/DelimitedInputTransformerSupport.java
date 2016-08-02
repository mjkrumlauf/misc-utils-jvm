package com.mjkrumlauf.miscutils;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang.Validate;

/**
 * {@code DelimitedInputTransformerSupport}
 *
 * @author mjkrumlauf
 */
public interface DelimitedInputTransformerSupport {

    /**
     * Transform a comma-delimited series of inputs to a {@link List} of type {@code T} using the passed-in
     * transformation function.
     *
     * @param input
     * @param mapFn Transformation function, e.g. <b>{@code Long::valueOf}</b>, or <b>{@code s -> s}</b> (String input, String list items)
     * @param <T> Type of element in list
     * @return {@link List} of items of type T
     *
     * @throws IllegalArgumentException when parameters are empty or null
     */
    default <T> List<T> toListFrom(final String input, final Function<String, T> mapFn) {
        Validate.notEmpty(input, "input must be non-empty and non-null");
        Validate.notNull(mapFn, "mapFn mapping function must be provided");

        return Pattern.compile(",")
            .splitAsStream(input)
            .map(mapFn)
            .collect(Collectors.toList());
    }

    /**
     * Transform a comma-delimited series of inputs to a {@link List} of type {@code String}.
     *
     * @param input
     * @return {@link List} of items of type {@link String}
     *
     * @throws IllegalArgumentException when parameters are empty or null
     */
    default List<String> toListFrom(final String input) {
        return toListFrom(input, s -> s);
    }
}
