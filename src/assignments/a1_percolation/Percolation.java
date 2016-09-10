package assignments.a1_percolation;

/**
 * Created by tomasizo on 9/10/16.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
        private WeightedQuickUnionUF sites;
        private int[][] open;
        private int N;

        // create n-by-n grid, with all sites blocked
        public Percolation(int n) {
            if (n <= 0) throw new IllegalArgumentException();
            N = n * n + 2;
            sites = new WeightedQuickUnionUF(N);
            open = new int[n][n];

            int top = 1;
            int bot = N - 2;
            for (int i = 0; i < n ; i++) {
                //connect virtual sites (head & tail) to corresponding rows
                sites.union(0    , top++);
                sites.union(N - 1, bot--);
            }
        }

        // open site (row i, column j) if it is not open already
        public void open(int i, int j) {
            if (i > open.length || j > open.length || i <= 0 || j <= 0) throw new IndexOutOfBoundsException();
            int n = open.length;
            open[i - 1][j - 1] = 1;                 // open site
            int loc = (open.length * (i - 1)) + j;  // id/location in sites[]

            if (j != 1 && isOpen(i, j - 1)) {       // left neighbour connection
                sites.union(loc, loc - 1);
            }
            if (j != n && isOpen(i, j + 1)) {       // right neighbour connection
                sites.union(loc, loc + 1);
            }
            if (i != 1 && isOpen(i - 1, j)) {       // top neighbour connection
                sites.union(loc, loc - n);
            }
            if (i != n && isOpen(i + 1, j)) {       // bottom neighbour connection
                 sites.union(loc, loc + n);
            }
        }

        // is site (row i, column j) open?
        public boolean isOpen(int i, int j) {
            if (i > open.length || j > open.length || i <= 0 || j <= 0) throw new IndexOutOfBoundsException();
            return open[i - 1][j - 1] == 1;
        }

        // is site (row i, column j) full?
        public boolean isFull(int i, int j) {
            if (i > open.length || j > open.length || i <= 0 || j <= 0) throw new IndexOutOfBoundsException();
            return sites.connected(0,(open.length * (i - 1)) + j) && isOpen(i,j); // is site connected to HEAD??
        }


        // does the system percolate?
        public boolean percolates() {
            if (open.length == 1) return sites.connected(0, N - 1) && isOpen(1,1);
            else return sites.connected(0, N - 1);
        }

        public static void main(String[] args){
            Percolation perc = new Percolation(4);

            System.out.println("Percolates: " + perc.percolates());
            System.out.println("IS (1,1) full?: " + perc.isFull(1,1));
            perc.open(1,1);
            perc.open(2,1);
            perc.open(3,2);
            perc.open(3,3);
            perc.open(3,4);
            perc.open(4,4);
            perc.open(4,1);
            System.out.println("full[3,3]?: " + perc.isFull(3,3));
            perc.open(2,2);
            System.out.println("full[3,3]?: " + perc.isFull(3,3));
            System.out.println("full[2,1]?: " + perc.isFull(2,1));
            System.out.println("Percolates: " + perc.percolates());
        }
    }

