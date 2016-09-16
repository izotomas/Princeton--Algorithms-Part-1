package w2.elementary_sorts.selection_sort;

/**
 * Created by tomasizo on 9/14/16.
 */
public class SelectionSort<Item> {

    public static void sort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
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
