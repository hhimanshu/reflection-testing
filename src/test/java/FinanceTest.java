import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.platform.commons.util.ReflectionUtils.tryToLoadClass;

public class FinanceTest {

    private final String classToFind = "Finance";

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

    @Disabled
    @Test
    public void testCommandsToUsage() {
        /*
         * 1. Existence of field
         * 2. isPublic, isFinal, isStatic, isMap (type)
         * 3. Has 3 entries
         * 4. Test all entries
         */
    }

    @Disabled
    @Test
    public void testMainCommandInput() {
        /*
         * 1. Pass command from the commandsToUsage
         * 2. Test invalid command
         */
    }

    @Disabled
    @Test
    public void testValidateCommandArgumentsExistence() {
        /*
         * 1. Method exists
         * 2. isStatic, isPrivate, returns boolean
         * 3. accepts String[] parameter
         */
    }

    @Disabled
    @Test
    public void testValidateCommandArgumentsCorrectness() {
        /*
         * 1. Send all 3 valid argument[0] with different rest of argument length
         * 2. Send invalid args[0] and assert that it should return false;
         */
    }

    @Disabled
    @Test
    public void testMainWithValidCommandInvalidUsage() {
        /*
         * 1. test all valid command names with invalid arguments lengths
         */
    }

    @Disabled
    @Test
    public void testCommandConstantFields() {
        /*
         * 1. Existence of BEST_LOAN_RATES, SAVINGS_CALCULATOR, MORTGAGE_CALCULATOR
         * 2. isPublic, isStatic, isFinal
         * 3. Right values for each field
         */
    }
}
