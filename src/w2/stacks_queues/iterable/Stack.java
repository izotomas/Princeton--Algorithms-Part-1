package w2.stacks_queues.iterable;

import java.util.Iterator;

/**
 * Created by tomasizo on 9/11/16.
 */
public class Stack<Item> implements Iterable<Item> {
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

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{

        private Node current = first;

        @Override
        public boolean hasNext() { return current != null; }

        @Override
        public void remove(){ /* not supported */}

        @Override
        public Item next() {
            Item item = current.item;
            current   = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        for (int i = 5; i > 0 ; i--) {
            st.push(i);
        }

        // one way of using iterator
        for(Iterator<Integer> it = st.iterator(); it.hasNext();){
            System.out.println("For loop iterator: " + it.next());
        }

        // other way of using iterator
        Iterator<Integer> it = st.iterator();
        while (it.hasNext()) System.out.println("While Iterator: " + it.next());

        // yet another printing option
        for (Integer i: st){
            System.out.println("Elegant iteration: " + i);
        }
    }
}

