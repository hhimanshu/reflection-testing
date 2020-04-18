package com.bl.h2;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.platform.commons.util.ReflectionUtils.isPrivate;
import static org.junit.platform.commons.util.ReflectionUtils.tryToLoadClass;

public class SavingsCalculatorTest {
    private final String classToFind = "com.bl.h2.SavingsCalculator";

    public Optional<Class<?>> getAppClass() {
        Try<Class<?>> aClass = tryToLoadClass(classToFind);
        return aClass.toOptional();
    }

    @Test
    public void testSavingsCalculatorExists() {
        final Optional<Class<?>> maybeSavingsCalculator = getAppClass();
        assertTrue(maybeSavingsCalculator.isPresent(), classToFind + " must be created");
    }

    @Test
    public void testExistenceOfPrivateFields() {
        final Optional<Class<?>> maybeSavingsCalculator = getAppClass();
        final Class savingsCalculator = maybeSavingsCalculator.get();
        final Set<String> fieldNames = new HashSet<String>(Arrays.asList("credits", "debits"));

        final Field[] declaredFields = savingsCalculator.getDeclaredFields();
        assertEquals(2, declaredFields.length, "2 fields should be available in " + classToFind);

        for (final Field field : declaredFields) {
            assertTrue(isPrivate(field), field.getName() + " should be declared private");
            assertEquals(float[].class, field.getType(), field.getName() + " should be of type 'float[]'");
            assertTrue(fieldNames.contains(field.getName()), field.getName() + " is not a valid name. It should be either 'credits' or 'debits'");
        }
    }

    @Test
    public void testConstructor() {
    }

    @Test
    public void testFieldsValueSetWhenConstructorCalled() {
    }

    @Test
    public void testCalculateExists() {
    }

    @Test
    public void testSumOfCreditsExists() {
    }

    @Test
    public void testSumOfCreditsWorksCorrectly() {
    }

    @Test
    public void testSumOfDebitsExists() {
    }

    @Test
    public void testSumOfDebitsWorksCorrectly() {
    }

    @Test
    public void testCalculateWorksCorrectly() {
    }

    @Test
    public void testMainMethodPrintsCorrectOutput() {
    }
}
