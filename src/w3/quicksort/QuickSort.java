package w3.quicksort;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;
import w2.elementary_sorts.insertion.InsertionSort;

import java.util.Comparator;

/**
 * Created by tomasizo on 9/17/16.
 */
public class QuickSort {

    private final static int CUTOFF = 10;

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        //if (hi <= lo) return;
        // use  faster insertion sort for small subarrays, perf. improv. by ~20%
        if (hi <= lo + CUTOFF -1) {
            Insertion.sort(a, lo, hi);
            return;
        }

        // using median as partition key improves performance by ~10%
        int m = medianOf3(a, lo, lo + (hi - lo)/2, hi);
        exch(a, lo, m);

        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static int medianOf3(Comparable[] a, int i, int j, int k) {
        if      (a[i].compareTo(a[j]) > 0) {
            if  (a[i].compareTo(a[k]) > 0) return k;
            else                           return i;
        }
        else if (a[j].compareTo(a[k]) > 0) return i;
        else                               return j;
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

    private static void exch(Comparable[]a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
