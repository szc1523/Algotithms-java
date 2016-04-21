/* 
 * by Sun Zhicheng 
 * for coursera course Algorithms 
 * week 1 Assignment: Percolation
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
//import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF gri2;
    private final int len; //length of the grid
    private final int top;   //the virtual top site
    private final int bot;   //the virtual bottom site
    
    private boolean[] opened;   //is true if a site is open
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {   
        if (N <= 0)
            throw new IllegalArgumentException("input should be"
                    + " an integer greater than zero");
  
        len = N;
        top = 0;
        bot = len * len + 1;
        opened = new boolean[len * len + 2];        
        grid = new WeightedQuickUnionUF(len * len + 2);
        gri2 = new WeightedQuickUnionUF(len * len + 1); 
        
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {  
        if (!isOpen(i, j)) {
            opened[mat2ind(i, j)] = true;
            if (i == 1) {
                grid.union(top, mat2ind(i, j));
                gri2.union(top, mat2ind(i, j));
                if (isOpen(i + 1, j)) { 
                    grid.union(mat2ind(i, j), mat2ind(i + 1, j));
                    gri2.union(mat2ind(i, j), mat2ind(i + 1, j));
                }
            }
            else if (i == len) {
                grid.union(bot, mat2ind(i, j));
                if (isOpen(i - 1, j)) {
                    grid.union(mat2ind(i, j), mat2ind(i - 1, j));
                    gri2.union(mat2ind(i, j), mat2ind(i - 1, j));
                }
            }
            else {
                if (isOpen(i + 1, j)) {
                    grid.union(mat2ind(i, j), mat2ind(i + 1, j)); 
                    gri2.union(mat2ind(i, j), mat2ind(i + 1, j)); 
                }
                if (isOpen(i - 1, j)) {
                    grid.union(mat2ind(i, j), mat2ind(i - 1, j));
                    gri2.union(mat2ind(i, j), mat2ind(i - 1, j));
                }
            }
            if (j == 1) {
                if (isOpen(i, j + 1)) {
                    grid.union(mat2ind(i, j), mat2ind(i, j + 1));
                    gri2.union(mat2ind(i, j), mat2ind(i, j + 1));
                }
            }
            else if (j == len) {
                if (isOpen(i, j - 1)) {
                    grid.union(mat2ind(i, j), mat2ind(i, j - 1));
                    gri2.union(mat2ind(i, j), mat2ind(i, j - 1));
                }
            }
            else {
                if (isOpen(i, j + 1)) {
                    grid.union(mat2ind(i, j), mat2ind(i, j + 1)); 
                    gri2.union(mat2ind(i, j), mat2ind(i, j + 1)); 
                }
                if (isOpen(i, j - 1)) {
                    grid.union(mat2ind(i, j), mat2ind(i, j - 1));
                    gri2.union(mat2ind(i, j), mat2ind(i, j - 1));
                }
            }            
        }       
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        validate(i, j);
        return opened[mat2ind(i, j)];  
    }
    
    private void validate(int i, int j) {
        if (i <= 0 || i > len || j <= 0 || j > len)
            throw new IndexOutOfBoundsException("either index i or "
                    + "j is not between 1 to " + len);
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        validate(i, j);
        return gri2.connected(mat2ind(i, j), top);        
    }
    
    // does the system percolate?
    public boolean percolates() {
        return grid.connected(top, bot);
    }
    
    //change coordinates from matrix to array index
    //mind that the API starts from 0, while this class starts from 1
    private int mat2ind(int i, int j) {
        return (i - 1) * len + j;   
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        /*int dim = 4;
        Percolation tst = new Percolation(dim);
        StdOut.println(tst.grid.connected(tst.mat2ind(3, 2), tst.mat2ind(3, 3)));

        tst.open(1, 1);
        tst.open(1, 2);
        tst.open(2, 2);
        tst.open(2, 3);
        tst.open(3, 3);
        tst.open(3, 4);
        tst.open(4, 3);
        StdOut.println(tst.percolates());
        //StdOut.println(tst.isFull(1, 2));

        //StdOut.println(tst.isOpen(3, 4));
        StdOut.println(tst.grid.connected(tst.mat2ind(2, 2), tst.mat2ind(2, 3)));
        StdOut.println(tst.grid.connected(tst.mat2ind(1, 1), tst.mat2ind(3, 4)));
        
        StdOut.println();
        //for(int i = 0; i <= 3 * 3 + 1; i ++)
            //StdOut.println(tst.opened[i]);
        
        for (int i = 1; i <= dim; i++) {
            for (int j = 1; j <= dim; j++) {
                StdOut.print(tst.isFull(i, j) + "  ");
            }
            StdOut.println();
        }*/
    }

}
