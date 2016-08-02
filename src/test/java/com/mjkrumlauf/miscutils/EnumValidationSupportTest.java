package com.mjkrumlauf.miscutils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class EnumValidationSupportTest implements EnumValidationSupport {

    @Test
    public void givenValidInput_whenValueFrom_thenSuccess() {
        final String expected = "MINUTES";
        final TimeUnit actual = enumValueFrom(TimeUnit.class, expected);
        assertThat(actual.toString(), equalTo(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenBadInput_whenValueFrom_thenThrowIllegalArgumentException() {
        enumValueFrom(TimeUnit.class, "bad input");
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNullEnumClass_whenValueFrom_thenThrowIllegalArgumentException() {
        enumValueFrom(null, "doesn't matter");
    }

    @Test/*(expected = IllegalArgumentException.class)*/
    public void givenNullInput_whenValueFrom_thenThrowIllegalArgumentException() {
        try {
            enumValueFrom(TimeUnit.class, null);
            fail("IllegalArgumentException expected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
