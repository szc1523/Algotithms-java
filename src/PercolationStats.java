/* 
 * by Sun Zhicheng 
 * for coursera course Algorithms 
 * week 1 Assignment: Percolation Monte Carlo
 */
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
//import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private final int len, time; //length of the grid, repeated time
    private final double miu, sigm;
    private double[] frac; 
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("both input should be"
                    + " integers greater than zero");
        len = N;  
        time = T;
        frac = new double[T]; 
        
        for (int i = 0; i < time; i++) {
            int cnt = 0;
            Percolation tst = new Percolation(len);
            while (!tst.percolates()) {
                int row, col;
                do {
                    row = StdRandom.uniform(len) + 1;
                    col = StdRandom.uniform(len) + 1;
                } while (tst.isOpen(row, col));
                tst.open(row, col);
                cnt++;
            } 
            frac[i] = (double) (cnt) / len / len;
        }      
        miu = StdStats.mean(frac);
        sigm = StdStats.stddev(frac);
    }

    // sample mean of percolation threshold
    public double mean() {
        return miu;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return sigm;
    }
    
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return miu - 1.96 * sigm / Math.sqrt(time);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return miu + 1.96 * sigm / Math.sqrt(time);
    }

    public static void main(String[] args) {
        PercolationStats tst = new PercolationStats(2, 10000);
        StdOut.println("mean    " + tst.mean());
        StdOut.println("stddev  " + tst.stddev());
        StdOut.println("low high" + tst.confidenceLo() + "   " + tst.confidenceHi());

    }

}
