package w2.stacks_queues.generics;

/**
 * Created by tomasizo on 9/11/16.
 */
public class Stack<Item> {
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty()
    { return first == null; }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(1);

    }
}
