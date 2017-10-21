package org.kobyshev.polynomial;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class NumericalPolynomialTest {

    @Test
    public void polinomAddTest() {
        NumericalPolynomial result = new NumericalPolynomial(Collections.singletonList(0.)).add(new NumericalPolynomial(Collections.singletonList(0.)));
        Assert.assertEquals(result, new NumericalPolynomial(Collections.singletonList(0.)));
    }

    @Test
    public void polinomAdd2Test() {
        NumericalPolynomial result = new NumericalPolynomial(Arrays.asList(0., 1.)).add(new NumericalPolynomial(Collections.singletonList(0.)));
        Assert.assertEquals(result, new NumericalPolynomial(Arrays.asList(0., 1.)));
    }

    @Test
    public void polinomAdd3Test() {
        NumericalPolynomial result = new NumericalPolynomial(Arrays.asList(1., 2., 3.)).add(new NumericalPolynomial(Arrays.asList(3., 2., 1.)));
        Assert.assertEquals(result, new NumericalPolynomial(Arrays.asList(4., 4., 4.)));
    }

    @Test
    public void polinomMultiplyTest() {
        NumericalPolynomial result = new NumericalPolynomial(Collections.singletonList(0.)).multiply(new NumericalPolynomial(Collections.singletonList(0.)));
        Assert.assertEquals(result, new NumericalPolynomial(Collections.singletonList(0.)));
    }

    @Test
    public void polinomMultiply2Test() {
        NumericalPolynomial result = new NumericalPolynomial(Arrays.asList(0., 1.)).multiply(new NumericalPolynomial(Collections.singletonList(0.)));
        Assert.assertEquals(result, new NumericalPolynomial(Collections.singletonList(0.)));
    }

    @Test
    public void polinomMultiply3Test() {
        NumericalPolynomial result = new NumericalPolynomial(Arrays.asList(1., 2.)).multiply(new NumericalPolynomial(Arrays.asList(3., 2.)));
        Assert.assertEquals(result, new NumericalPolynomial(Arrays.asList(3., 8., 4.)));
    }

    @Test
    public void polinomMultiply4Test() {
        NumericalPolynomial result = new NumericalPolynomial(Arrays.asList(1., 2., 3.)).multiply(new NumericalPolynomial(Arrays.asList(3., 2.)));
        Assert.assertEquals(result, new NumericalPolynomial(Arrays.asList(3., 8., 13., 6.)));
    }

    @Test
    public void polinomMultiply5Test() {
        NumericalPolynomial result = new NumericalPolynomial(Arrays.asList(1., 1., 2.)).multiply(new NumericalPolynomial(Arrays.asList(1., 1.)));
        Assert.assertEquals(result, new NumericalPolynomial(Arrays.asList(1., 2., 3., 2.)));
    }

    @Test
    public void polinomMultiply6Test() {
        NumericalPolynomial result = new NumericalPolynomial(Arrays.asList(1., 1., 2.)).multiply(new NumericalPolynomial(Arrays.asList(1., 1., 2.)));
        Assert.assertEquals(result, new NumericalPolynomial(Arrays.asList(1., 2., 5., 4., 4.)));
    }

    @Test
    public void polinomMultiply7Test() {
        NumericalPolynomial result = new NumericalPolynomial(Arrays.asList(0.01, 0.01, 0.02)).multiply(new NumericalPolynomial(Arrays.asList(0.01, 0.01, 0.02)));
        Assert.assertEquals(result, new NumericalPolynomial(Arrays.asList(0.0001, 0.0002, 0.0005, 0.0004, 0.0004)));
    }

    @Test
    public void polinomMultiply8Test() {
        NumericalPolynomial result = new NumericalPolynomial(Arrays.asList(100., 100., 200.)).multiply(new NumericalPolynomial(Arrays.asList(100., 100., 200.)));
        Assert.assertEquals(result, new NumericalPolynomial(Arrays.asList(10000., 20000., 50000., 40000., 40000.)));
    }

    @Test
    public void polinomStringTest() {
        String result = new NumericalPolynomial(Arrays.asList(2., 3., 4.)).toString();
        Assert.assertEquals("4.0 x^2 + 3.0 x + 2.0", result);
    }

    @Test
    public void polinomString2Test() {
        String result = new NumericalPolynomial(Collections.singletonList(0.)).toString();
        Assert.assertEquals("0.0", result);
    }

    @Test
    public void polinomString3Test() {
        String result = new NumericalPolynomial(Arrays.asList(2., -3.)).toString();
        Assert.assertEquals("-3.0 x + 2.0", result);
    }

    @Test
    public void polinomString4Test() {
        String result = new NumericalPolynomial(Arrays.asList(2., -3., 4.)).toString();
        Assert.assertEquals("4.0 x^2 - 3.0 x + 2.0", result);
    }

    @Test
    public void polinomString5Test() {
        String result = new NumericalPolynomial(Arrays.asList(-2., -3., -4.)).toString();
        Assert.assertEquals("-4.0 x^2 - 3.0 x - 2.0", result);
    }

    @Test
    public void polinomString6Test() {
        String result = new NumericalPolynomial(Arrays.asList(-2., 0., -4.)).toString();
        Assert.assertEquals("-4.0 x^2 - 2.0", result);
    }

    @Test
    public void polinomString7Test() {
        String result = new NumericalPolynomial(Arrays.asList(0., 0., -4.)).toString();
        Assert.assertEquals("-4.0 x^2", result);
    }
}
