// for coursera course exercises computation

//import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Exercises {

    public static void main(String[] args) {
/*        int N = 10;
        int [] array = new int[] {5, 6, 5, 8, 3, 1, 9, 4, 6, 3, 4, 0} ;
        array = {5 6 5 8 3 1 9 4 6 3 4 0};

        QuickFindUF uf = new QuickFindUF(N);
        for*/
        
        int N = StdIn.readInt();
        
        //QuickFindUF uf = new QuickFindUF(N);
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (p == N + 1) break;
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
        
        for (int i = 0; i < N; i++)
            StdOut.print(uf.find(i) + " ");

    }

}
