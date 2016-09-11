package w2.stacks_queues.stack_strings;

/**
 * Created by tomasizo on 9/11/16.
 */

public class StackLinked {
    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() { return first == null; }

    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

    public static void main(String[] args) {
        StackLinked st = new StackLinked();
        st.push("Hello");
        System.out.println(st.pop());
    }
}
