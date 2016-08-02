package com.mjkrumlauf.miscutils;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

public class DelimitedInputTransformerSupportTest implements DelimitedInputTransformerSupport {

    @Test
    public void givenValidInput_whenTransforming_thenReturnValidListOfLongs() {
        final List<Long> longList = toListFrom("1,2,3", Long::valueOf);
        assertArrayEquals(longList.toArray(), new Long[] { 1L, 2L, 3L });
    }

    @Test
    public void givenValidInput_whenTransforming_thenReturnValidListOfStrings() {
        assertArrayEquals(toListFrom("1,2,3").toArray(), new String[] { "1", "2", "3" });
    }

    @Test(expected = NumberFormatException.class)
    public void givenInvalidInput_whenTransforming_thenThrowNumberFormatException() {
        toListFrom("x,y,z", Integer::valueOf);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenEmptyInput_whenTransforming_thenThrowNumberFormatException() {
        toListFrom("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNullInput_whenTransforming_thenThrowIllegalArgumentException() {
        toListFrom(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNullMappingFunction_whenTransforming_thenThrowIllegalArgumentException() {
        toListFrom("1,2,3", null);
    }
}
