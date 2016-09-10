package w1.quick_union.weighted;

/**
 * Created by tomasizo on 9/8/16.
 */
public class WeightedQuickUnionUF {
    private int[] sz;
    private int[] id;

    public WeightedQuickUnionUF(int N){
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N ; i++) {
            sz[i] = 1;
            id[i] = i;
        }
    }

    private int root(int i){
        while (i != id[i]) {
            id[i] = id[id[i]];      // adding path compression
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if (i == j) return;         // already connected
        if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else               { id[j] = i; sz[i] += sz[j]; }
    }

}

