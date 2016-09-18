package w4.priority_queues;

/**
 * Created by tomasizo on 9/18/16.
 */

public class MaxPQ <Key extends Comparable<Key>>{

    private Key[] pq;
    private int N;

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity+1];
        N = 0;
    }

    public void insert(Key x) {
        pq[++N] = x;
        swim(N);
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    public Key delMax() {
        if (N == 0) throw new NullPointerException("PQ is empty.");
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N+1] = null; //prevent loitering
        return max;
    }

    private void sink(int k) {
        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++; //pick larger child
            if (!less(k, j)) break; //stop if order holds
            exch(k, j);
            k = j;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key max(){
        return pq[1];
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }
}
