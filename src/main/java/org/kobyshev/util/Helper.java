package org.kobyshev.util;

public class Helper {

    private static void fail(String message) {
        if (message == null) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException(message);
    }

    static public void checkArgument(String message, boolean condition) {
        if (condition) {
            fail(message);
        }
    }

}
