package org.kobyshev.solver;

import org.junit.Assert;
import org.junit.Test;
import org.kobyshev.polynomial.FractionPolynomial;
import org.kobyshev.polynomial.NumericalPolynomial;

import java.util.Arrays;

public class HermitSolverTest {

    @Test
    public void commonTest() {
        HermitSolver hermit2Solver = new HermitSolver(
                Arrays.asList(
                        Arrays.asList(-1., 2., -8., 56.),
                        Arrays.asList(0., 1., 0., 0.),
                        Arrays.asList(1., 2., 8., 56.)
                )
        );
        Assert.assertEquals(
                Arrays.asList(
                        Arrays.asList(-1., 2., -8., 28., -21., 15., -10., 4., -1., 1.),
                        Arrays.asList(-1., 2., -8., 7., -6., 5., -2., 2., 1.),
                        Arrays.asList(-1., 2., -1., 1., -1., 1., 2., 4.),
                        Arrays.asList(0., 1., 0., 0., 1., 5., 10.),
                        Arrays.asList(0., 1., 0., 1., 6., 15.),
                        Arrays.asList(0., 1., 1., 7., 21.),
                        Arrays.asList(1., 2., 8., 28.),
                        Arrays.asList(1., 2., 8.),
                        Arrays.asList(1., 2.)
                ), hermit2Solver.getDividedDifferenceTable()
        );
    }

    @Test
    public void lessonExampleTest() {
        HermitSolver hermit2Solver = new HermitSolver(
                Arrays.asList(
                        Arrays.asList(0., 2., 3., -4.),
                        Arrays.asList(1., 4., 3.)
                )
        );
        Assert.assertEquals(
                Arrays.asList(
                        Arrays.asList(0., 2., 3., -2., 1., 1.),
                        Arrays.asList(0., 2., 3., -1., 2.),
                        Arrays.asList(0., 2., 2., 1.),
                        Arrays.asList(1., 4., 3.),
                        Arrays.asList(1., 4.)
                ), hermit2Solver.getDividedDifferenceTable()
        );
    }


    @Test
    public void homeworkExamplePrintTest() {
        HermitSolver hermit2Solver = new HermitSolver(
                Arrays.asList(
                        Arrays.asList(0., 2., 3., -4.),
                        Arrays.asList(1., 4., 3.)
                )
        );
        String result = "" +
                "   0.0   2.0      \n" +
                "               3.0\n" +
                "   0.0   2.0        -2.0      \n" +
                "               3.0         1.0\n" +
                "   0.0   2.0        -1.0         1.0\n" +
                "               2.0         2.0      \n" +
                "   1.0   4.0         1.0\n" +
                "               3.0      \n" +
                "   1.0   4.0\n" +
                "            ";
        Assert.assertEquals(result, hermit2Solver.toPrettyString());
    }

    @Test
    public void numericalPolynomialTest() {
        HermitSolver hermit2Solver = new HermitSolver(
                Arrays.asList(
                        Arrays.asList(-1., 2., -8., 56.),
                        Arrays.asList(0., 1., 0., 0.),
                        Arrays.asList(1., 2., 8., 56.)
                )
        );
        NumericalPolynomial numericalPolynomial = hermit2Solver.getNumericalPolynomial();
        Assert.assertEquals("x^8 + 1.0", numericalPolynomial.toString());
    }

    @Test
    public void fractionPolynomialTest() {
        HermitSolver hermit2Solver = new HermitSolver(
                Arrays.asList(
                        Arrays.asList(-1., 2., -8., 56.),
                        Arrays.asList(0., 1., 0., 0.),
                        Arrays.asList(1., 2., 8., 56.)
                )
        );
        FractionPolynomial fractionPolynomial = hermit2Solver.getFractionPolynomial();
        Assert.assertEquals("x^8 + 1", fractionPolynomial.toString());
    }
}
