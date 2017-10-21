package org.kobyshev.polynomial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericalPolynomial extends Polynomial<Double> {
    private List<Double> coefficients;

    public NumericalPolynomial(List<Double> coefficients) {
        this.coefficients = new ArrayList<>(coefficients);
    }

    private static List<Double> multiply(List<Double> mn1, List<Double> mn2) {
        if (mn1.stream().allMatch(aDouble -> aDouble == null || aDouble == 0) ||
                mn2.stream().allMatch(aDouble -> aDouble == null || aDouble == 0))
            return Collections.singletonList(0.);
        int newSize = mn1.size() + mn2.size() - 1;
        List<Double> result = new ArrayList<>(newSize);
        for (int i = 0; i < newSize; i++) {
            result.add(null);
        }
        for (int i = 0; i < mn1.size(); i++) {
            for (int j = 0; j < mn2.size(); j++) {
                Double currentIterationResult;
                if (result.get(i + j) == null) {
                    currentIterationResult = mn1.get(i) * mn2.get(j);
                } else {
                    currentIterationResult = mn1.get(i) * mn2.get(j) + result.get(i + j);
                }
                result.set(i + j, currentIterationResult);
            }
        }
        return result;
    }

    private static List<Double> add(List<Double> ad1, List<Double> ad2) {
        int newSize = Math.max(ad1.size(), ad2.size());
        List<Double> result = new ArrayList<>(newSize);
        for (int i = 0; i < newSize; i++) {
            result.add(null);
        }

        int min = Math.min(ad1.size(), ad2.size());
        Collections.copy(result, ad1.size() == min ? ad2 : ad1);
        for (int i = 0; i < min; i++) {
            result.set(i, ad1.get(i) + ad2.get(i));
        }
        return result;
    }

    public NumericalPolynomial add(NumericalPolynomial add1) {
        return new NumericalPolynomial(NumericalPolynomial.add(this.getCoefficients(), add1.getCoefficients()));
    }

    public NumericalPolynomial multiply(NumericalPolynomial mn1) {
        return new NumericalPolynomial(NumericalPolynomial.multiply(this.getCoefficients(), mn1.getCoefficients()));
    }

    public List<Double> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(List<Double> coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumericalPolynomial that = (NumericalPolynomial) o;

        return coefficients != null ? coefficients.equals(that.coefficients) : that.coefficients == null;
    }

    @Override
    public int hashCode() {
        return coefficients != null ? coefficients.hashCode() : 0;
    }
//
//    @Override
//    public String toString() {
//        return NumericalPolynomial.toString(getCoefficients());
//    }

    @Override
    protected String getString(Double o) {
        return o.toString();
    }
}
