package w2.elementary_sorts.insertion;

/**
 * Created by tomasizo on 9/14/16.
 */
public class InsertionSort {
    public static void sort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                }
                else break;
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) == -1;
    }

    private static void exch(Comparable[] a, int j, int k) {
        Comparable temp = a[j];
        a[j] = a[k];
        a[k] = temp;
    }
}
