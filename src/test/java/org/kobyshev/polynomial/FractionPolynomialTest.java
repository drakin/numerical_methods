package org.kobyshev.polynomial;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class FractionPolynomialTest {

    @Test
    public void polinomAddTest() {
        FractionPolynomial result = new FractionPolynomial(Collections.singletonList(new Pair<>(0, 1))).add(new FractionPolynomial(Collections.singletonList(new Pair<>(0, 1))));
        Assert.assertEquals(result, new FractionPolynomial(Collections.singletonList(new Pair<>(0, 1))));
    }

    @Test
    public void polinomAdd2Test() {
        FractionPolynomial result = new FractionPolynomial(Arrays.asList(new Pair<>(0, 1), new Pair<>(1, 1)))
                .add(new FractionPolynomial(Collections.singletonList(new Pair<>(0, 1))));
        Assert.assertEquals(result, new FractionPolynomial(Arrays.asList(new Pair<>(0, 1), new Pair<>(1, 1))));
    }

    @Test
    public void polinomAdd3Test() {
        FractionPolynomial result = new FractionPolynomial(Arrays.asList(new Pair<>(1, 1), new Pair<>(2, 1), new Pair<>(3, 1))).add(
                new FractionPolynomial(Arrays.asList(new Pair<>(3, 1), new Pair<>(2, 1), new Pair<>(1, 1))));
        Assert.assertEquals(result, new FractionPolynomial(Arrays.asList(new Pair<>(4, 1), new Pair<>(4, 1), new Pair<>(4, 1))));
    }

    @Test
    public void polinomAdd4Test() {
        FractionPolynomial result = new FractionPolynomial(Arrays.asList(new Pair<>(1, 4), new Pair<>(1, 3), new Pair<>(1, 2))).add(
                new FractionPolynomial(Arrays.asList(new Pair<>(1, 7), new Pair<>(1, 6), new Pair<>(1, 5))));
        Assert.assertEquals(result, new FractionPolynomial(Arrays.asList(new Pair<>(11, 28), new Pair<>(1, 2), new Pair<>(7, 10))));
    }

    @Test
    public void polinomMultiplyTest() {
        FractionPolynomial result = new FractionPolynomial(Collections.singletonList(new Pair<>(0, 1))).multiply(new FractionPolynomial(Collections.singletonList(new Pair<>(0, 1))));
        Assert.assertEquals(result, new FractionPolynomial(Collections.singletonList(new Pair<>(0, 1))));
    }

    @Test
    public void polinomMultiply2Test() {
        FractionPolynomial result = new FractionPolynomial(Arrays.asList(new Pair<>(0, 1), new Pair<>(1, 1))).multiply(new FractionPolynomial(Collections.singletonList(new Pair<>(0, 1))));
        Assert.assertEquals(result, new FractionPolynomial(Arrays.asList(new Pair<>(0, 1), new Pair<>(0, 1))));
    }

    @Test
    public void polinomMultiply3Test() {
        FractionPolynomial result = new FractionPolynomial(Arrays.asList(new Pair<>(1, 1), new Pair<>(2, 1))).multiply(new FractionPolynomial(Arrays.asList(new Pair<>(3, 1), new Pair<>(2, 1))));
        Assert.assertEquals(result, new FractionPolynomial(Arrays.asList(new Pair<>(3, 1), new Pair<>(8, 1), new Pair<>(4, 1))));
    }

    @Test
    public void polinomMultiply4Test() {
        FractionPolynomial result = new FractionPolynomial(Arrays.asList(new Pair<>(1, 1), new Pair<>(2, 1), new Pair<>(3, 1))).multiply(new FractionPolynomial(Arrays.asList(new Pair<>(3, 1), new Pair<>(2, 1))));
        Assert.assertEquals(result, new FractionPolynomial(Arrays.asList(new Pair<>(3, 1), new Pair<>(8, 1), new Pair<>(13, 1), new Pair<>(6, 1))));
    }

    @Test
    public void polinomStringTest() {
        String result = new FractionPolynomial(Arrays.asList(new Pair<>(1, 1), new Pair<>(2, 1), new Pair<>(3, 1))).toString();
        Assert.assertEquals("3 x^2 + 2 x + 1", result);
    }


}
