package assignments.a4_puzzle;

import java.util.ArrayList;

/**
 * Created by tomasizo on 9/18/16.
 */

public class Board {
    private final int[][] blocks;
    private final int N, hamming, manhattan;
    private int i, j; // indices of zero, i.e. blocks[i][j] == 0;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null) throw new NullPointerException("called Constructor with null args");
        N = blocks.length;
        this.blocks = new int[N][N];
        int ham = 0, man = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.blocks[i][j] = blocks[i][j];
                // check for manhattan and hamming
                int inplace = i*N + 1 + j;          // number which should be at blocks[i][j]
                int inblock = this.blocks[i][j];    // number which is at blocks[i][j]
                if      (inblock == 0) {this.i = i; this.j = j;}
                else if (inblock != inplace) {
                    ham++;
                    man += manhattan(inblock, i, j);
                }
            }
        }
        hamming = ham;
        manhattan = man;
    }

    // board dimension n
    public int dimension() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return manhattan;
    }

    private int manhattan(int n, int i, int j) {
        int row = (n-1) / N;
        int col = (n-1) % N;
        return Math.abs(i - row) + Math.abs(j - col);
    }

    // is this board the goal board?
    public boolean isGoal() {
        return manhattan == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] a = new int[N][N];
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                a[k][l] = blocks[k][l];
            }
        }
        int k = Math.abs(i - 1); // choose row above or below zero
        int l = Math.abs(j - 1); // choose col left or right from zero

        //swap them
        int t = a[k][l];
        a[k][l] = a[k][j];
        a[k][j] = t;
        return new Board(a);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this)             return true;
        if (!(y instanceof Board)) return false;
        else {
            Board that = (Board) y;
            if (this.N != that.N || this.i != that.i || this.j != that.j
                    ||this.hamming != that.hamming || this.manhattan != that.manhattan ) {
                return false;
            }
            for (int i = 0; i < that.N; i++) {
                for (int j = 0; j <that.N; j++) {
                    if (this.blocks[i][j] != that.blocks[i][j]) return false;
                }
            }
            return true;
        }
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<>();

        int[][] a = new int[N][N];
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                a[k][l] = blocks[k][l];
            }
        }

        if (i - 1 >= 0) {
            exch(a, i-1, j);
            neighbors.add(new Board(a));
            int t = a[i][j]; a[i-1][j] = t; a[i][j] = 0;
        }

        if (i + 1 < N) {
            exch(a, i+1, j);
            neighbors.add(new Board(a));
            int t = a[i][j]; a[i+1][j] = t; a[i][j] = 0;
        }

        if (j - 1 >= 0) {
            exch(a, i, j-1);
            neighbors.add(new Board(a));
            int t = a[i][j]; a[i][j-1] = t; a[i][j] = 0;
        }

        if (j + 1 < N) {
            exch(a, i, j+1);
            neighbors.add(new Board(a));
            int t = a[i][j]; a[i][j+1] = t; a[i][j] = 0;
        }
        return neighbors;
    }

    // swapping any value with zero
    private void exch(int[][] a, int row, int col) {
        int tmp = a[row][col];
        a[row][col] = 0;
        a[i][j] = tmp;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder sb = new StringBuilder(Integer.toString(N));
        for (int i = 0; i < N; i++) {
            sb.append("\n");
            for (int j = 0; j < N; j++) {
                sb.append(" " + blocks[i][j] + " ");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
