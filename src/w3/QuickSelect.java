package w3;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by tomasizo on 9/17/16.
 */
public class QuickSelect {
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if      (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else           return a[k];
        }
        return a[k];
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo])) // find item on left to swap
            {    if (i == hi) break; }

            while (less(a[lo], a[--j])) // find item on right to swap
            {    if (j == lo) break; }

            if (i >= j) break; //check if pointers cross
            exch(a, i, j);
        }

        exch(a, lo, j); // swap with partitioning item
        return j;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) == -1;
    }

    private static void exch(Comparable[]a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


}
