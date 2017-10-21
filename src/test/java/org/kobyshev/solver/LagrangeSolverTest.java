package org.kobyshev.solver;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.kobyshev.polynomial.FractionPolynomial;
import org.kobyshev.polynomial.NumericalPolynomial;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LagrangeSolverTest {

    private double delta = 1e-14;

    @Test(expected = IllegalArgumentException.class)
    public void nullXTest() {
        new LagrangeSolver(null, Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullYTest() {
        new LagrangeSolver(Collections.emptyList(), null);
    }

    @Test
    public void emptyArgumentsTest() {
        new LagrangeSolver(Collections.emptyList(), Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void differentSizesTest() {
        new LagrangeSolver(Collections.singletonList(1.), Collections.emptyList());
    }

    @Test
    public void lessonExampleTest() {
        List<Double> x = Arrays.asList(1., 3., 4., 7., 8.);
        List<Double> y = Arrays.asList(2., 4., 1., 5., 10.);
        LagrangeSolver lagrangeSolver = new LagrangeSolver(x, y);
        List<Double> yResult = x.stream().map(lagrangeSolver::solve).collect(Collectors.toList());
        Assert.assertEquals("Значения полинома Лагранжа отличается от входных значений.", y, yResult);
        Assert.assertEquals("Значение в точке 0 отличается больше чем на 1/10^14", -196. / 15., lagrangeSolver.solve(0.), delta);
    }

    @Test
    public void homeworkExampleTest() {
        List<Double> x = Arrays.asList(-9., -6., -1., 0., 1.);
        List<Double> y = Arrays.asList(-4., 7., -5., -2., 7.);
        LagrangeSolver lagrangeSolver = new LagrangeSolver(x, y);
        List<Double> yResult = x.stream().map(lagrangeSolver::solve).collect(Collectors.toList());
        Assert.assertEquals("Значения полинома Лагранжа отличается от входных значений.", y, yResult);
    }

    @Test
    public void testCoeff11() {
        List<Double> x = Arrays.asList(1., 3., 4., 7., 8.);
        List<Double> y = Arrays.asList(2., 4., 1., 5., 10.);
        LagrangeSolver lagrangeSolver = new LagrangeSolver(x, y);
        FractionPolynomial fractionPolynomial = lagrangeSolver.getFractionPolynomial();
        Assert.assertEquals(new FractionPolynomial(Arrays.asList(new Pair<>(-196, 15), new Pair<>(5801, 252), new Pair<>(-4651, 504), new Pair<>(337, 252), new Pair<>(-157, 2520))), fractionPolynomial);
    }

    @Test
    public void testCoeff12() {
        List<Double> x = Arrays.asList(-9., -6., -1., 0., 1.);
        List<Double> y = Arrays.asList(-4., 7., -5., -2., 7.);
        LagrangeSolver lagrangeSolver = new LagrangeSolver(x, y);
        FractionPolynomial fractionPolynomial = lagrangeSolver.getFractionPolynomial();
        Assert.assertEquals(new FractionPolynomial(Arrays.asList(new Pair<>(-2, 1), new Pair<>(2027, 360), new Pair<>(1291, 432), new Pair<>(133, 360), new Pair<>(5, 432))), fractionPolynomial);
    }

    @Test
    public void testCoeff21() {
        List<Double> x = Arrays.asList(1., 3., 4., 7., 8.);
        List<Double> y = Arrays.asList(2., 4., 1., 5., 10.);
        LagrangeSolver lagrangeSolver = new LagrangeSolver(x, y);
        NumericalPolynomial numericalPolynomial = lagrangeSolver.getNumericalPolynomial();
        double[] doubles = numericalPolynomial.getCoefficients().stream().mapToDouble(d -> d).toArray();
        Assert.assertArrayEquals(new double[]{-196. / 15., 5801. / 252., -4651. / 504., 337. / 252., -157. / 2520}, doubles, delta);
    }

    @Test
    public void testCoeff22() {
        List<Double> x = Arrays.asList(-9., -6., -1., 0., 1.);
        List<Double> y = Arrays.asList(-4., 7., -5., -2., 7.);
        LagrangeSolver lagrangeSolver = new LagrangeSolver(x, y);
        NumericalPolynomial numericalPolynomial = lagrangeSolver.getNumericalPolynomial();
        double[] doubles = numericalPolynomial.getCoefficients().stream().mapToDouble(d -> d).toArray();
        Assert.assertArrayEquals(new double[]{-2., 2027. / 360., 1291. / 432., 133. / 360., 5. / 432.}, doubles, delta);
    }

}
