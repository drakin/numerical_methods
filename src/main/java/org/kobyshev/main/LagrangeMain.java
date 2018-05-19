package org.kobyshev.main;

import org.kobyshev.solver.LagrangeSolver;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LagrangeMain {
    public static void main(String[] args) {
        List<Double> x = Arrays.asList(-9., -6., -1., 0., 1.);
        List<Double> y = Arrays.asList(-4., 7., -5., -2., 7.);
        LagrangeSolver lagrangeSolver = new LagrangeSolver(x, y);
        System.out.println("Fraction Polynomial: " + lagrangeSolver.getFractionPolynomial().toString());
        System.out.println("Numerical Polynomial: " + lagrangeSolver.getNumericalPolynomial().toString());
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter x");
        System.out.print("> ");
        double xi = Double.parseDouble(scan.nextLine());
        System.out.println("f(" + xi + ") = " + lagrangeSolver.solve(xi));
    }
}
