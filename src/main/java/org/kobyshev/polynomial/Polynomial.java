package org.kobyshev.polynomial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class Polynomial<T> {

    public abstract List<T> getCoefficients();

    protected String toString(List<T> polynomial) {
        if (polynomial == null) return "";
        List<T> reversed = new ArrayList<>(polynomial);
        Collections.reverse(reversed);

        List<String> xPowList = Stream.concat(Stream.of("", " x"), IntStream.range(2, reversed.size()).mapToObj(i -> " x^" + i)).collect(Collectors.toList());
        Collections.reverse(xPowList);
        Iterator<String> xPowIterator = xPowList.iterator();
        if (reversed.size() == 1) {
            xPowIterator.next();
        }
        return reversed.stream().map(p -> getString(p) + xPowIterator.next()).reduce((p1, p2) -> {
            String sign = " + ";
            if (p2.startsWith("-")) {
                sign = " - ";
                p2 = p2.substring(1);
            }
            if (p2.startsWith("0.0")) return p1;
            return p1 + sign + p2;
        }).orElse("");
    }

    abstract protected String getString(T t);

    @Override
    public String toString() {
        return toString(getCoefficients());
    }
}
