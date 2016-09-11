package assignments.a2_Queues_Decks;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by tomasizo on 9/11/16.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue() { q = (Item[]) new Object[1]; }

    // is the queue empty?
    public boolean isEmpty() { return N == 0; }

    // return the number of items on the queue
    public int size() { return N; }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (N == q.length) resize(2 * q.length );
        q[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int i = StdRandom.uniform(N);
        Item rand   = q[i];
        Item last   = q[--N];
        q[N]        = null;
        q[i]        = last;
        if (N > 0 && N == q.length/4) resize(q.length/2);
        return rand;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
           copy[i] = q[i];
        }
        q = copy;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int i = StdRandom.uniform(N);
        return q[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandIterator();
    }

    private class RandIterator implements Iterator<Item> {

        Item[] items = (Item[]) new Object[N];
        int M = N;

        RandIterator() {
            for (int i = 0; i < N; i++) items[i] = q[i];
        }

        @Override
        public boolean hasNext() { return M > 0; }

        @Override
        public Item next() {
            if (M == 0) throw new NoSuchElementException();
            int i       = StdRandom.uniform(M);
            Item last   = items[--M];
            Item rand   = items[i];
            items[i]    = last;
            items[M]    = null;
            return rand;
        }

        @Override
        public void remove() { throw new UnsupportedOperationException(); }
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            rq.enqueue(i);
        }

        for (Integer i: rq) System.out.println(i);

        for (int i = 0; i < 10; i++) {
            System.out.println("DEQUEUE: " + rq.dequeue());
        }

        int k = 3;
        while (k-- >0) System.out.println("Count " + k);
    }
}
