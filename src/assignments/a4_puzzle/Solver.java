package assignments.a4_puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;

/**
 * Created by tomasizo on 9/18/16.
 */

public class Solver {

    private MinPQ<Node> orig, twin;
    private ArrayList<Board> solution;
    private boolean solved;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new NullPointerException("null argument");
        orig = new MinPQ<>();
        twin = new MinPQ<>();
        orig.insert(new Node(initial, 0, null));
        twin.insert(new Node(initial.twin(), 0, null));

        while (!solved) { // because "invalid" solution from twin is being saved too
            solvingStep(orig);
            solvingStep(twin);
        }

//        if (isSolvable()) {
//            System.out.println("Minimum number of moves = " + moves() + "\n");
//            for (Board step: solution()) System.out.println(step);
//        }
//       else{ System.out.println("No solution possible"); }
    }

    private void solvingStep(MinPQ<Node> heap) {
        Node current = heap.delMin();         // delMin first
        if (current.board.isGoal()) {         // end if goal and copy boards
            if (heap == orig){
                solution = new ArrayList<>();
                while (current != null) {
                    solution.add(0, current.board);
                    current = current.parent;
                }
            }
            solved = true;
            return;
        }

        for (Board neighbour: current.board.neighbors()) { // add each unique neighbour
            if (current.parent == null || !neighbour.equals(current.parent.board)){
                heap.insert(new Node(neighbour, current.moves + 1, current));
            }
        }
    }
    private class Node implements Comparable<Node> {
        final Board board;
        final int moves;
        final Node parent;

        Node(Board board, int moves, Node parent) {
            this.board = board;
            this.moves = moves;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node that) {
            return (this.board.manhattan() + moves) - (that.board.manhattan() + that.moves);
        }
    }


    // is the initial board solvable?
    public boolean isSolvable() {
        return solution != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) return -1;
        else               return solution.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() { return solution; }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        int a[][] = {{0,1,3},{4,2,5},{7,8,6}};
//        int a[][] = {{1,2,3},{4,5,6},{8,7,0}};
        new Solver(new Board(a));
    }
}
