package com.bl.h2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.platform.commons.util.ReflectionUtils.*;

public class CommandLineAppTest {
    private final String classToFind = "com.bl.h2.CommandLineApp";

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    public Optional<Class<?>> getAppClass() {
        Try<Class<?>> aClass = tryToLoadClass(classToFind);
        return aClass.toOptional();
    }

    @Test
    public void assertClassExistence() {
        final Optional<Class<?>> maybeClass = getAppClass();
        assertTrue(maybeClass.isPresent(), classToFind + " should be present");
        assertEquals(classToFind, maybeClass.get().getCanonicalName());
    }

    @Test
    public void testName() {
        final String name = "H2";
        final int age = 32;
        final String testString = name + "\n" + age;
        provideInput(testString);

        CommandLineApp.main(new String[0]);

        List<String> outputList = Arrays.stream(getOutput().split("\n")).collect(Collectors.toList());
        assertEquals("Enter your name", outputList.get(0));
        assertEquals("Hello " + name, outputList.get(1));
    }

    @Test
    public void testGetRatesMethod() {
        String getRates = "getRates";
        final Optional<Class<?>> maybeClass = getAppClass();
        assertTrue(maybeClass.isPresent(), classToFind + " should be present");
        Class<?> c = maybeClass.get();
        List<Method> methods = Arrays.stream(c.getDeclaredMethods())
                .filter(m -> m.getName().equals(getRates))
                .collect(Collectors.toList());

        assertEquals(1, methods.size(), getRates + " must be defined as a method in CommandLineApp");

        final Method method = methods.get(0);
        assertEquals(float.class, method.getReturnType(), getRates + " must return 'float' as the return type");
        assertTrue(isStatic(method), getRates + " must be a static method");
        assertTrue(isPublic(method), getRates + " must be a public method");
    }

    @Disabled
    @Test
    public void testGetRatesExistence() {

    }

    @Test
    public void testNameAndValidLoanTerm() {
        final String name = "H2";
        final int loanTermInYears = 2;
        final String testString = name + "\n" + loanTermInYears;
        provideInput(testString);

        CommandLineApp.main(new String[0]);

        List<String> outputList = Arrays.stream(getOutput().split("\n")).collect(Collectors.toList());
        assertEquals("Enter your name", outputList.get(0));
        assertEquals("Hello " + name, outputList.get(1));

        assertEquals("Enter the loan term (in years)", outputList.get(2));
        assertEquals("Best Available Rate: " + CommandLineApp.bestRates.get(loanTermInYears), outputList.get(3));
        assertEquals(4, outputList.size());
    }

    @Test
    public void testNameAndInValidLoanTerm() {
        final String name = "H2";
        final int loanTermInYears = 20;
        final String testString = name + "\n" + loanTermInYears;
        provideInput(testString);

        CommandLineApp.main(new String[0]);

        List<String> outputList = Arrays.stream(getOutput().split("\n")).collect(Collectors.toList());
        assertEquals("Enter your name", outputList.get(0));
        assertEquals("Hello " + name, outputList.get(1));

        assertEquals("Enter the loan term (in years)", outputList.get(2));
        assertEquals("No available rates for term: " + loanTermInYears + " years", outputList.get(3));
        assertEquals(4, outputList.size());
    }

}
