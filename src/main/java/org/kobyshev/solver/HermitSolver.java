package org.kobyshev.solver;

import javafx.util.Pair;
import org.kobyshev.polynomial.FractionPolynomial;
import org.kobyshev.polynomial.NumericalPolynomial;
import org.kobyshev.util.Helper;

import java.math.BigDecimal;
import java.util.*;

public class HermitSolver {
    private List<List<Double>> initValues;

    private List<List<Double>> dividedDifferenceTable;
    private List<List<Pair<Integer, Integer>>> dividedDifferenceTable2;


    public HermitSolver(List<List<Double>> initValues) {
        this.initValues = new ArrayList<>(initValues.size());
        for (List<Double> i : initValues) {
            this.initValues.add(new ArrayList<>(i));
        }
        dividedDifferenceTable = computeDividedDifferenceTable();
        dividedDifferenceTable2 = computeDividedDifferenceTable2();
    }

    private List<List<Double>> computeDividedDifferenceTable() {
        List<List<Double>> result = new ArrayList<>();
        int maxColumns = 0;
        for (List<Double> i : initValues) {
            for (int j = 0; j < i.size() - 1; j++) {
                List<Double> currentLine = new ArrayList<>();
                currentLine.add(i.get(0));
                currentLine.add(i.get(1));
                result.add(currentLine);
            }
            maxColumns += i.size() - 1;
        }
        maxColumns += 1;
        int iDec = 0;
        for (int j = 2; j < maxColumns; j++) {
            for (int i = 0; i < maxColumns - 2 - iDec; i++) {
                List<Double> currentLine = result.get(i);
                currentLine.add(getValue(result, i, j));
            }
            iDec++;
        }
        return result;
    }

    private List<List<Pair<Integer, Integer>>> computeDividedDifferenceTable2() {
        List<List<Pair<Integer, Integer>>> result = new ArrayList<>();
        int maxColumns = 0;
        for (List<Double> i : initValues) {
            for (int j = 0; j < i.size() - 1; j++) {
                List<Pair<Integer, Integer>> currentLine = new ArrayList<>();
                currentLine.add(Helper.convertTo(i.get(0), 1.));
                currentLine.add(Helper.convertTo(i.get(1), 1.));
                result.add(currentLine);
            }
            maxColumns += i.size() - 1;
        }
        maxColumns += 1;
        int iDec = 0;
        for (int j = 2; j < maxColumns; j++) {
            for (int i = 0; i < maxColumns - 2 - iDec; i++) {
                List<Pair<Integer, Integer>> currentLine = result.get(i);
                currentLine.add(Helper.simplify(getValue2(getDividedDifferenceTable(), i, j)));
            }
            iDec++;
        }
        return result;
    }

    private Double getValue(List<List<Double>> result, int i, int j) {
        if (Objects.equals(result.get(i).get(0), result.get(i + j - 1).get(0))) {
            List<Double> lineFromInitVal = initValues.stream().filter(v -> Objects.equals(v.get(0), result.get(i).get(0))).findFirst().orElse(null);
            if (lineFromInitVal == null) throw new IllegalArgumentException();
            return lineFromInitVal.get(j) / factorial(j - 1);
        } else {
            return (result.get(i + 1).get(j - 1) - result.get(i).get(j - 1)) / (result.get(i + j - 1).get(0) - result.get(i).get(0));
        }
    }

    private Pair<Integer, Integer> getValue2(List<List<Double>> result, int i, int j) {
        if (Objects.equals(result.get(i).get(0), result.get(i + j - 1).get(0))) {
            List<Double> lineFromInitVal = initValues.stream().filter(v -> Objects.equals(v.get(0), result.get(i).get(0))).findFirst().orElse(null);
            if (lineFromInitVal == null) throw new IllegalArgumentException();
            return Helper.convertTo(lineFromInitVal.get(j), factorial(j - 1));
        } else {
            return Helper.convertTo(result.get(i + 1).get(j - 1) - result.get(i).get(j - 1), result.get(i + j - 1).get(0) - result.get(i).get(0));
        }
    }

    private Long factorial(int num) {
        Long result = 1L;
        if (num < 2) return result;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public List<List<Double>> getDividedDifferenceTable() {
        return dividedDifferenceTable;
    }

    public String toPrettyString() {
        return toPrettyString(6);
    }

    public String toPrettyString(int ceilSize) {
        String[] result = new String[getDividedDifferenceTable().size() * 2];
        for (int i = 0; i < getDividedDifferenceTable().size(); i++) {
            result[2 * i] = String.format("%1$" + ceilSize + "s", round(getDividedDifferenceTable().get(i).get(0), 3));
            result[2 * i + 1] = String.format("%1$" + ceilSize + "s", "");
        }
        boolean odd = true;
        int start = 0;
        int index1;
        int index2;
        for (int j = 1; j < getDividedDifferenceTable().get(0).size(); j++) {
            for (int i = 0; i < getDividedDifferenceTable().size(); i++) {
                if (j >= getDividedDifferenceTable().get(i).size()) continue;
                index1 = 2 * i + start;
                index2 = 2 * i + start;
                if (odd) {
                    index2++;
                } else {
                    index1++;
                }
                result[index1] += String.format("%1$" + ceilSize + "s", round(getDividedDifferenceTable().get(i).get(j), 3));
                result[index2] += String.format("%1$" + ceilSize + "s", "");
            }
            odd = !odd;
            if (odd) {
                start += 2;
            }
        }
        return String.join("\n", result);
    }

    public NumericalPolynomial getNumericalPolynomial() {
        NumericalPolynomial sumPolynomial = new NumericalPolynomial(Collections.singletonList(getDividedDifferenceTable().get(0).get(1)));
        NumericalPolynomial prodPolynomial = new NumericalPolynomial(Collections.singletonList(1.));
        for (int j = 1; j < getDividedDifferenceTable().size(); j++) {
            prodPolynomial = prodPolynomial.multiply(new NumericalPolynomial(Arrays.asList(-getDividedDifferenceTable().get(j - 1).get(0), 1.)));
            sumPolynomial = sumPolynomial.add(new NumericalPolynomial(Collections.singletonList(getDividedDifferenceTable().get(0).get(j + 1))).multiply(prodPolynomial));
        }
        return sumPolynomial;
    }

    public FractionPolynomial getFractionPolynomial() {
        FractionPolynomial sumPolynomial = new FractionPolynomial(Collections.singletonList(getDividedDifferenceTable2().get(0).get(1)));
        FractionPolynomial prodPolynomial = new FractionPolynomial(Collections.singletonList(new Pair<>(1, 1)));
        for (int j = 1; j < getDividedDifferenceTable2().size(); j++) {
            prodPolynomial = prodPolynomial.multiply(
                    new FractionPolynomial(
                            Arrays.asList(
                                    new Pair<>(-getDividedDifferenceTable2().get(j - 1).get(0).getKey(), getDividedDifferenceTable2().get(j - 1).get(0).getValue()),
                                    new Pair<>(1, 1)
                            )));
            sumPolynomial = sumPolynomial.add(new FractionPolynomial(Collections.singletonList(getDividedDifferenceTable2().get(0).get(j + 1))).multiply(prodPolynomial));
        }
        return sumPolynomial;
    }

    public List<List<Pair<Integer, Integer>>> getDividedDifferenceTable2() {
        return dividedDifferenceTable2;
    }

    public Double solve(Double x) {
        Double sum = getDividedDifferenceTable().get(0).get(1);
        Double prod = 1.;
        for (int j = 1; j < getDividedDifferenceTable().size(); j++) {
            prod *= -getDividedDifferenceTable().get(j - 1).get(0) + x;
            sum += getDividedDifferenceTable().get(0).get(j + 1) * prod;
        }
        return sum;
    }

    private double round(double x, int scale){
        return (new BigDecimal(Double.toString(x))
                .setScale(scale, BigDecimal.ROUND_HALF_UP))
                .doubleValue();
    }
}
