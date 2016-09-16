package w3.comparator;

import java.util.Comparator;

/**
 * Created by tomasizo on 9/14/16.
 */

public class InsertionSort {
    public static void sort(Object[] a, Comparator comparator){
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(comparator, a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                }
                else break;
            }
        }
    }

    private static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int j, int k) {
        Object swap = a[j];
        a[j] = a[k];
        a[k] = swap;
    }
}
