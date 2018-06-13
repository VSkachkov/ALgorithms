import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int trialsNumber;
    private double[] threshold;
    private double mean;
    private double stddev;
    private double confLo;
    private double confHi;

    public PercolationStats(int n, int trials) {
        if (n<1 || trials<1){
            throw new IllegalArgumentException();
        }
        trialsNumber = trials;

        threshold = new double[trials];

        for (int attempt = 0; attempt < trials; attempt++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }
            double opeSites = (double) p.numberOfOpenSites();
            double thresholdValue = opeSites / (double) (n * n);
            threshold[attempt] = thresholdValue;
        }   // perform trials independent experiments on an n-by-n grid
        mean = StdStats.mean(threshold);
        stddev = StdStats.stddev(threshold);
        confLo = mean - 1.96 * stddev / sqrt(trialsNumber);
        confHi = mean + 1.96 * stddev / sqrt(trialsNumber);
    }

    public double mean() {
        return mean;
    }             // sample mean of percolation threshold

    public double stddev() {
        return stddev;
    }           // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return confLo;
    }         // low  endpoint of 95% confidence interval

    private static double sqrt(int number) {
        double t;

        double squareRoot = number / 2;

        do {
            t = squareRoot;
            squareRoot = (t + (number / t)) / 2;
        } while ((t - squareRoot) != 0);

        return squareRoot;
    }

    public double confidenceHi() {
        return confHi;
    }       // high endpoint of 95% confidence interval

    public static void main(String[] args) {     // test client (described below)
        for (String s : args) {
            System.out.println(s);

            int gridSize = Integer.parseInt(args[0]);
            int trials = Integer.parseInt(args[1]);
            if (gridSize<1 || trials<1){
                throw new IllegalArgumentException();
            }
            PercolationStats stats = new PercolationStats(gridSize, trials);
            System.out.println("mean = " + stats.mean());
            System.out.println("dev = " + stats.stddev());
            System.out.println("confLo = " + stats.confidenceLo());
            System.out.println("confHi = " + stats.confidenceHi());

        }
    }
}