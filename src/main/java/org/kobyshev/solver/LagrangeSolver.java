package org.kobyshev.solver;

import javafx.util.Pair;
import org.kobyshev.polynomial.FractionPolynomial;
import org.kobyshev.polynomial.NumericalPolynomial;
import org.kobyshev.util.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LagrangeSolver {
    List<Double> xi;
    List<Double> yi;

    public LagrangeSolver(List<Double> xi, List<Double> yi) {
        Helper.checkArgument("Массив xi не передан.", xi == null);
        Helper.checkArgument("Массив yi не передан.", yi == null);
        Helper.checkArgument("Количество точек x и y не совпадают.", xi.size() != yi.size());
        Helper.checkArgument("Есть повторяющиеся точки x", xi.size() != xi.stream().distinct().count());
        this.xi = new ArrayList<>(xi);
        this.yi = new ArrayList<>(yi);
    }

    public Double solve(Double x) {
        Double sum = 0.;
        for (int i = 0; i < xi.size(); i++) {
            Double produсt = 1.;
            for (int j = 0; j < xi.size(); j++) {
                if (i != j) {
                    produсt *= (x - xi.get(j)) / (xi.get(i) - xi.get(j));
                }
            }
            sum += yi.get(i) * produсt;
        }
        return sum;
    }

    public FractionPolynomial getFractionPolynomial() {
        List<Pair<Integer, Integer>> sum = new ArrayList<>();
        sum.add(new Pair<>(0, 1));
        FractionPolynomial sumPolynomial = new FractionPolynomial(sum);
        for (int i = 0; i < xi.size(); i++) {
            List<Pair<Integer, Integer>> produсt = new ArrayList<>();
            produсt.add(new Pair<>(1, 1));
            FractionPolynomial prodPolynomial = new FractionPolynomial(produсt);
            for (int j = 0; j < xi.size(); j++) {
                if (i != j) {
                    List<Pair<Integer, Integer>> mn1 = new ArrayList<>();
                    mn1.add(Helper.convertTo(-xi.get(j), xi.get(i) - xi.get(j)));
                    mn1.add(Helper.convertTo(1., xi.get(i) - xi.get(j)));
                    FractionPolynomial mn = new FractionPolynomial(mn1);
                    prodPolynomial = prodPolynomial.multiply(mn);
                }
            }
            sumPolynomial = sumPolynomial.add(prodPolynomial.multiply(new FractionPolynomial(Collections.singletonList(Helper.convertTo(yi.get(i), 1.)))));
        }
        return sumPolynomial;

    }

    public NumericalPolynomial getNumericalPolynomial() {
        List<Double> sum = new ArrayList<>();
        sum.add(0.);
        NumericalPolynomial sumPolynomial = new NumericalPolynomial(sum);
        for (int i = 0; i < xi.size(); i++) {
            List<Double> produсt = new ArrayList<>();
            produсt.add(1.);
            NumericalPolynomial prodPolynomial = new NumericalPolynomial(produсt);
            for (int j = 0; j < xi.size(); j++) {
                if (i != j) {
                    prodPolynomial = prodPolynomial.multiply(new NumericalPolynomial(Arrays.asList(-xi.get(j) / (xi.get(i) - xi.get(j)), 1. / (xi.get(i) - xi.get(j)))));
                }
            }
            sumPolynomial = sumPolynomial.add(prodPolynomial.multiply(new NumericalPolynomial(Collections.singletonList(yi.get(i)))));
        }
        return sumPolynomial;
    }

}
