package w2.elementary_sorts.shell;

/**
 * Created by tomasizo on 9/14/16.
 */
public class ShellSort {
    public static void sort(Comparable[] a)
    {
        int N = a.length;

        int h = 1;
        while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, ...

        while (h >= 1) {  // h-sort the array.
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exch(a, j, j-h);
            }
            h = h/3;
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

