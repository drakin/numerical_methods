package org.kobyshev.polynomial;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FractionPolynomial extends Polynomial<Pair<Integer, Integer>> {
    private List<Pair<Integer, Integer>> coefficients;

    public FractionPolynomial(List<Pair<Integer, Integer>> coefficients) {
        this.coefficients = new ArrayList<>(coefficients);
    }

    private static List<Pair<Integer, Integer>> multiply(List<Pair<Integer, Integer>> mn1, List<Pair<Integer, Integer>> mn2) {
        int newSize = mn1.size() + mn2.size() - 1;
        List<Pair<Integer, Integer>> result = new ArrayList<>(newSize);
        for (int i = 0; i < newSize; i++) {
            result.add(null);
        }
        for (int i = 0; i < mn1.size(); i++) {
            for (int j = 0; j < mn2.size(); j++) {
                Pair<Integer, Integer> currentIterationResult;
                currentIterationResult = new Pair<>(mn1.get(i).getKey() * mn2.get(j).getKey(), mn1.get(i).getValue() * mn2.get(j).getValue());
                if (result.get(i + j) != null) {
                    Pair<Integer, Integer> oldValue = result.get(i + j);
                    currentIterationResult = elementAddition(oldValue, currentIterationResult);
                }
                currentIterationResult = simplify(currentIterationResult);
                result.set(i + j, currentIterationResult);
            }
        }
        return result;
    }

    private static List<Pair<Integer, Integer>> add(List<Pair<Integer, Integer>> ad1, List<Pair<Integer, Integer>> ad2) {
        int newSize = Math.max(ad1.size(), ad2.size());
        List<Pair<Integer, Integer>> result = new ArrayList<>(newSize);
        for (int i = 0; i < newSize; i++) {
            result.add(null);
        }

        int min = Math.min(ad1.size(), ad2.size());
        Collections.copy(result, ad1.size() == min ? ad2 : ad1);
        for (int i = 0; i < min; i++) {
            result.set(i, simplify(elementAddition(ad1.get(i), ad2.get(i))));
        }
        return result;
    }

    private static Pair<Integer, Integer> elementAddition(Pair<Integer, Integer> ad1, Pair<Integer, Integer> ad2) {
        if (Objects.equals(ad1.getValue(), ad2.getValue())) {
            return new Pair<>(ad1.getKey() + ad2.getKey(), ad1.getValue());
        } else {
            return new Pair<>(
                    ad1.getKey() * ad2.getValue() + ad2.getKey() * ad1.getValue(),
                    ad1.getValue() * ad2.getValue()
            );
        }
    }


    private static Pair<Integer, Integer> simplify(Pair<Integer, Integer> pair) {
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

    public FractionPolynomial multiply(FractionPolynomial mn) {
        return new FractionPolynomial(FractionPolynomial.multiply(this.getCoefficients(), mn.getCoefficients()));
    }

    public FractionPolynomial add(FractionPolynomial mn) {
        return new FractionPolynomial(FractionPolynomial.add(this.getCoefficients(), mn.getCoefficients()));
    }

    public List<Pair<Integer, Integer>> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(List<Pair<Integer, Integer>> coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FractionPolynomial that = (FractionPolynomial) o;

        return coefficients != null ? coefficients.equals(that.coefficients) : that.coefficients == null;
    }

    @Override
    public int hashCode() {
        return coefficients != null ? coefficients.hashCode() : 0;
    }

    @Override
    protected String getString(Pair<Integer, Integer> o) {
        if (o.getKey() == 0) return "0";
        if (o.getValue() == 1) return o.getKey().toString();
        return o.getKey().toString() + "/" + o.getValue().toString();
    }
}
