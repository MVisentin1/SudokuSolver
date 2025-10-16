package com.visentin.sudoku.util;

/**
 * Utility class for safely testing Java assertions in unit tests.
 * <p>
 * Provides methods to detect whether JVM assertions are enabled and
 * to conditionally test assertion behavior so that tests can pass
 * regardless of the JVM assertion flag (-ea).
 * </p>
 * <p>
 * This class is intended for use only in test code.
 * </p>
 */
public final class AssertionTestUtils {

    /** Prevent instantiation of this utility class. */
    private AssertionTestUtils() {}

    /**
     * Checks whether JVM assertions are enabled.
     *
     * @return {@code true} if assertions are enabled (-ea), {@code false} otherwise
     */
    public static boolean assertionsEnabled() {
        boolean enabled = false;
        assert enabled = true; // will set enabled to true only if assertions are enabled
        return enabled;
    }

    /**
     * Executes the given {@link Runnable} and expects it to throw an {@link AssertionError}
     * if JVM assertions are enabled. If assertions are disabled, the block is simply executed.
     *
     * <p>
     * This allows unit tests to safely test assertion behavior without depending
     * on JVM flags.
     * </p>
     *
     * @param block the code block to run and optionally check for AssertionError
     */
    public static void assertErrorIfEnabled(Runnable block) {
        if (assertionsEnabled()) {
            org.junit.jupiter.api.Assertions.assertThrows(AssertionError.class, block::run);
        } else {
            block.run(); // assertions disabled, just execute
        }
    }
}
