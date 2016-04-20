/* 
 * by Sun Zhicheng 
 * for coursera course Algorithms 
 * week 1 Assignment: Percolation Monte Carlo
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private final int len; //length of the grid
    
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        len = N;        
        double[] frac = new double[T]; 
        int[] count = new int[T];
        
        
        for (int i = 0; i < T; i++) {
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
            count[i] = cnt;
            frac[i] = (double) (cnt) / len / len;
        }      
        StdOut.println(StdStats.mean(frac));
        //StdOut.println(StdStats.stddev(frac));
        
    }

    // sample mean of percolation threshold
    //public double mean()                      

    // sample standard deviation of percolation threshold
    //public double stddev()                    
    
    // low  endpoint of 95% confidence interval
   // public double confidenceLo()              

    // high endpoint of 95% confidence interval
    //public double confidenceHi()             
        
    


    public static void main(String[] args) {
        //PercolationStats tst = new PercolationStats(200,100); 
        //for(int i=0; i<8; i++)
        //  StdOut.println(StdRandom.uniform(8));

    }

}
