package org.kobyshev.util;

import javafx.util.Pair;

public class Helper {

    private static double DELTA = 1e-10;

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

    public static Pair<Integer, Integer> convertTo(Double a, Double b) {
        Double key = a;
        Double value = b;
        while (Math.floor(key) - key > DELTA && Math.floor(value) - value > DELTA) {
            key *= 10;
            value *= 10;
        }
        return new Pair<>((int) Math.round(Math.floor(key)), (int) Math.round(Math.floor(value)));
    }

    public static Pair<Integer, Integer> convertTo(Double a, Long b) {
        Double key = a;
        Long value = b;
        while (Math.floor(key) - key > DELTA) {
            key *= 10;
            value *= 10;
        }
        return new Pair<>((int) Math.round(Math.floor(key)), value.intValue());
    }

    public static Pair<Integer, Integer> simplify(Pair<Integer, Integer> pair) {
        Integer key = pair.getKey();
        Integer value = pair.getValue();
        if (value < 0) {
            key *= -1;
            value *= -1;
        }
        int min = Math.min(Math.abs(key), Math.abs(value));
        for (int i = 2; i <= min; i++) {
            while (key % i == 0 && value % i == 0) {
                key /= i;
                value /= i;
            }
        }
        return new Pair<>(key, value);
    }

}
