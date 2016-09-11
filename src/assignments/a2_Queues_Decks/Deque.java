package assignments.a2_Queues_Decks;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by tomasizo on 9/11/16.
 */

public class Deque<Item> implements Iterable<Item> {

    private int N = 0;
    private Node first;
    private Node last;

    private class Node{
        Item item;
        Node next;
        Node prev;

        Node(Node prev, Item item, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    // construct an empty deque
    public Deque() { }

    // is the deque empty?
    public boolean isEmpty() { return N == 0; }

    // return the number of items on the deque
    public int size() { return N; }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        if (isEmpty()) {
            first   = new Node(null, item, null);
            last    = first;
        }
        else if (size() == 1) {
            first       = new Node(null, item, last);
            last.prev   = first;
        }
        else {
            Node oldfirst   = first;
            first           = new Node(null, item, oldfirst);
            oldfirst.prev   = first;
        }
        N++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        if (isEmpty()) {
            first = new Node(null, item, null);
            last  = first;
        }
        else if (size() == 1) {
            last        = new Node(first, item, null);
            first.next  = last;
        }
        else {
            Node oldlast    = last;
            last            = new Node(oldlast, item, null);
            oldlast.next    = last;
        }
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if      (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        if      (size() == 1) { first = null; last = null; }
        else if (size() == 2) {
            last.prev   = null;
            first       = last;
        }
        else {
            first       = first.next;
            first.prev  = null;
        }
        N--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if      (isEmpty()) throw new NoSuchElementException();
        if      (size() == 1) { return removeFirst(); }
        Item item = last.item;
        if (size() == 2) {
            first.next = null;
            last = first;
        }
        else {
            last        = last.prev;
            last.next   = null;
        }
        N--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{

        private Node current = first;

        @Override
        public boolean hasNext() { return current != null; }

        @Override
        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current   = current.next;
            return item;
        }

        @Override
        public void remove() { throw new UnsupportedOperationException(); }
    }

    // unit testing
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        System.out.println("SIZE: " + d.size());
        d.addLast(2);
        d.addFirst(1);
        for (Integer i: d){
            System.out.println(i);
        }
        System.out.println("SIZE: " + d.size());
    }
}
