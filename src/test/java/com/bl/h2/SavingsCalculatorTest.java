package com.bl.h2;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.platform.commons.util.ReflectionUtils.*;

@SuppressWarnings("unused")
public class SavingsCalculatorTest {
    private final String classToFind = "com.bl.h2.SavingsCalculator";

    public Optional<Class<?>> getAppClass() {
        final Try<Class<?>> aClass = tryToLoadClass(classToFind);
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
        assertTrue(maybeSavingsCalculator.isPresent());
        final Class<?> savingsCalculator = maybeSavingsCalculator.get();
        final Set<String> fieldNames = new HashSet<>(Arrays.asList("credits", "debits"));

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
        final Optional<Class<?>> maybeSavingsCalculator = getAppClass();
        assertTrue(maybeSavingsCalculator.isPresent());
        final Class<?> savingsCalculator = maybeSavingsCalculator.get();
        final Constructor<?>[] constructors = savingsCalculator.getDeclaredConstructors();

        assertEquals(1, constructors.length, classToFind + " should have 1 constructor");

        final Constructor<?> constructor = constructors[0];
        assertTrue(isPublic(constructor), "constructor must be declared 'public'");
        assertEquals(2, constructor.getParameterCount(), "Constructor should have 2 parameters");

        for (final Parameter parameter : constructor.getParameters()) {
            assertEquals(float[].class, parameter.getType(), "Constructor parameter should be of type 'float[]'");
        }
    }

    @Test
    public void testFieldsValueSetWhenConstructorCalled() throws IllegalAccessException {
        float[] credits = new float[]{10.0f, 20.0f};
        float[] debits = new float[]{5.0f};
        final SavingsCalculator calculator = new SavingsCalculator(credits, debits);

        final Class<?> clazz = calculator.getClass();
        final Field[] fields = clazz.getDeclaredFields();

        for (final Field field : fields) {
            field.setAccessible(true);
            float[] fieldValues = (float[]) field.get(calculator);
            if (field.getName().equals("credits")) {
                assertArrayEquals(credits, fieldValues, "credits parameter should set the value in class field name 'credits'");
            } else if (field.getName().equals("debits")) {
                assertArrayEquals(debits, fieldValues, "debits parameter should set the value in class field name 'debits'");
            }
        }
    }

    @Test
    public void testCalculateExists() {
        final String methodName = "calculate";
        final Optional<Class<?>> maybeSavingsCalculator = getAppClass();
        assertTrue(maybeSavingsCalculator.isPresent());
        final Class<?> savingsCalculator = maybeSavingsCalculator.get();

        final Method[] methods = savingsCalculator.getDeclaredMethods();
        final List<Method> filteredMethod = Arrays.stream(methods).filter(method -> method.getName().equals(methodName)).collect(Collectors.toList());

        assertEquals(1, filteredMethod.size(), classToFind + " should contain a method called '" + methodName + "'");

        final Method calculate = filteredMethod.get(0);
        assertTrue(isPublic(calculate), methodName + " must be declared as 'public'");
        assertEquals(float.class, calculate.getReturnType(), methodName + " method must return a value of type 'float'");
    }

    @Test
    public void testSumOfCreditsExists() {
        final String methodName = "sumOfCredits";

        final Optional<Class<?>> maybeSavingsCalculator = getAppClass();
        assertTrue(maybeSavingsCalculator.isPresent());
        final Class<?> savingsCalculator = maybeSavingsCalculator.get();

        final Method[] methods = savingsCalculator.getDeclaredMethods();
        final List<Method> filteredMethod = Arrays.stream(methods).filter(method -> method.getName().equals(methodName)).collect(Collectors.toList());

        assertEquals(1, filteredMethod.size(), classToFind + " should contain a method called '" + methodName + "'");

        final Method sumOfCredits = filteredMethod.get(0);
        assertTrue(isPrivate(sumOfCredits), methodName + " must be declared as 'private'");
        assertEquals(float.class, sumOfCredits.getReturnType(), methodName + " method must return a value of type 'float'");
    }

    @Test
    public void testSumOfCreditsWorksCorrectly() {
        final Optional<Class<?>> maybeSavingsCalculator = getAppClass();
        assertTrue(maybeSavingsCalculator.isPresent());
        final Class<?> savingsCalculator = maybeSavingsCalculator.get();

        float[] credits = new float[]{10.0f, 20.0f};
        float[] debits = new float[]{5.0f};

        final SavingsCalculator calculator = newInstance(SavingsCalculator.class, credits, debits);

        final String methodName = "sumOfCredits";
        final Optional<Method> maybeMethod = findMethod(SavingsCalculator.class, methodName);
        assertTrue(maybeMethod.isPresent());
        final Method sumOfCredits = maybeMethod.get();
        final float result = (float) invokeMethod(sumOfCredits, calculator);

        assertEquals(30.0f, result, methodName + " is not returning sum of credits.");
    }

    @Disabled
    @Test
    public void testSumOfDebitsExists() {
    }

    @Disabled
    @Test
    public void testSumOfDebitsWorksCorrectly() {
    }

    @Disabled
    @Test
    public void testCalculateWorksCorrectly() {
    }

    @Disabled
    @Test
    public void testMainMethodPrintsCorrectOutput() {
    }
}
