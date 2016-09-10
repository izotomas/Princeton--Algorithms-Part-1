package assignments.a1_percolation;

/**
 * Created by tomasizo on 9/10/16.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] fractions; // storage for results

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        fractions = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            double opens = 0;
            while (!perc.percolates()) {
                int j = StdRandom.uniform(n) + 1;
                int k = StdRandom.uniform(n) + 1;
                if (!perc.isOpen(j,k)){
                    perc.open(j,k);
                    opens++;
                }
            }
            fractions[i] = opens / ((double)n * n);
        }
    }
    // sample mean of percolation threshold
    public double mean() { return StdStats.mean(fractions); }

    // sample standard deviation of percolation threshold
    public double stddev() { return StdStats.stddev(fractions); }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
       return mean() - ((1.96 * stddev()) / Math.sqrt(fractions.length));
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + ((1.96 * stddev()) / Math.sqrt(fractions.length));
    }
    // test client (described below)
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200,100);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
