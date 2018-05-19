package org.kobyshev.main;

import org.kobyshev.solver.HermitSolver;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hermit3Main {
    public static void main(String[] args) {
        List<List<Double>> table = Arrays.asList(
                Arrays.asList(6., 10., 16., -4.,12.),
                Arrays.asList(10., 6., 4., 20.),
                Arrays.asList(16., 10., 36., 100., 56.)
        );
        HermitSolver hermit2Solver = new HermitSolver(table);
        System.out.println("Divided Difference Table:");
        System.out.println(hermit2Solver.toPrettyString());
        System.out.println("Fraction Polynomial: " +
                hermit2Solver.getFractionPolynomial().toString());
        System.out.println("Numerical Polynomial: " +
                hermit2Solver.getNumericalPolynomial().toString());
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter x");
        System.out.print("> ");
        double xi = Double.parseDouble(scan.nextLine());
        System.out.println("f(" + xi + ") = " +
                hermit2Solver.solve(xi));
    }
}
