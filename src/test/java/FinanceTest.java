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
        /**
         * 1. Existence of field
         * 2. isPublic, isFinal, isStatic, isMap (type)
         * 3. Has 3 entries
         * 4. Test all entries
         */
    }
}
