package w2.stacks_queues.evaluate;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by tomasizo on 9/11/16.
 */

public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty()) {
            System.out.println(";)");
            String s = StdIn.readString();
            if (s.equals("(")) ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                if (op.equals("+")) vals.push(vals.pop() + vals.pop());
                else if (op.equals("*")) vals.push(vals.pop() * vals.pop());
            } else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
