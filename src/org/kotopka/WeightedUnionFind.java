package org.kotopka;

import java.util.ArrayList;

/**
 * <code>WeightedUnionFind</code> data type. Implements the weighted union-find data type from <em>Algorithms 4th ed.</em>
 * This data type can be used to join elements into the same connected component, and to query if elements are in the same component.
 */
public class WeightedUnionFind {

    private final int n;    // number of sites
    private final int[] id; // root of each component
    private final int[] sz; // size of each component
    private int count;

    /**
     * <code>WeightedUnionFind()</code> - Constructor. Initializes the union-find instance with <code>n</code> sites.
     * @param n integer for the number of sites in this instance
     */
    public WeightedUnionFind(int n) {
        this.n = n;
        this.id = new int[n];
        this.sz = new int[n];
        this.count = n; // each site starts as its own component

        for (int i = 0; i < n; i++) {
            this.id[i] = i;
            this.sz[i] = 1;
        }
    }

    private void validatePoint(int p) {
        if (p < 0 || p >= n) throw new IllegalArgumentException("Argument out of range");
    }

    /**
     * <code>union()</code> - Joins two sites by joining the smaller subtree to the larger subtree.
     * Arbitrarily joins "q" to "p" if both connected components are of the same size.
     * @param p integer for site "p"
     * @param q integer for site "q"
     */
    public void union(int p, int q) {
        validatePoint(p);
        validatePoint(q);

        int i = find(p);
        int j = find(q);

        if (i == j) return;

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }

        count--;
    }

    /**
     * <code>find()</code> - Finds the root of the connected component containing site "p". Performs path compression in the process
     * @param p integer for the site to be queried
     * @return integer of the root of the connected component containing site "p"
     */
    public int find(int p) {
        validatePoint(p);

//        return id[p];
        return root(p);
    }

    /**
     * <code>root()</code> - Private helper method to find the root of site "p". Also performs path compression.
     * @param p integer of the site to be queried
     * @return integer of the root of site "p"
     */
    private int root(int p) {
        while (id[p] != p) {
            id[p] = id[id[p]];  // path compression
            p = id[p];
        }

        return p;
    }

    /**
     * <code>connected()</code> - Query the union-find instance to see if sites "p" and "q" are connected.
     * @param p integer for one of the sites to be queried
     * @param q integer for the other site
     * @return <code>true</code> if sites "p" and "q" are connected, <code>false</code> otherwise
     */
    public boolean connected(int p, int q) {
        validatePoint(p);
        validatePoint(q);

        return root(p) == root(q);  // here the path compression is actually invoked
    }

    /**
     * <code>count()</code> - Number of connected components in this union-find instance.
     * @return integer of the number of connected components
     */
    public int count() { return count; }

    /**
     * <code>toString()</code> - Returns string representation. NOTE: each value is the immediate parent node.
     * NOTE 2: these values can change due to path compression when <code>connected()</code> is called.
     * @return String representation of this WeightedUnionFind instance
     */
    @Override
    public String toString() {
        ArrayList<String> al = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            al.add(String.valueOf(id[i]));
        }

        return "[" + String.join(", ", al) + "]";
    }

    // low-effort testing
    public static void main(String[] args) {
        WeightedUnionFind wuf = new WeightedUnionFind(10);

        System.out.println("New WeightedUnionFind");
        System.out.println(wuf);
        System.out.println("Connected components: " + wuf.count());

        wuf.union(4, 3);
        System.out.println(wuf);
        System.out.println("Connected components: " + wuf.count());

        wuf.union(3, 8);
        System.out.println(wuf);
        System.out.println("Connected components: " + wuf.count());

        wuf.union(6, 5);
        System.out.println(wuf);
        System.out.println("Connected components: " + wuf.count());

        wuf.union(9, 4);
        System.out.println(wuf);
        System.out.println("Connected components: " + wuf.count());

        wuf.union(8, 9);
        System.out.println(wuf);
        System.out.println("Connected components: " + wuf.count());

        wuf.union(2, 1);
        System.out.println(wuf);
        System.out.println("Connected components: " + wuf.count());

        wuf.union(5, 0);
        System.out.println(wuf);
        System.out.println("Connected components: " + wuf.count());

        wuf.union(7, 2);
        System.out.println(wuf);
        System.out.println("Connected components: " + wuf.count());

        wuf.union(6, 1);
        System.out.println(wuf);
        System.out.println("Connected components: " + wuf.count());

        System.out.println("Find 1: " + wuf.find(1));
        System.out.println("Connected 1, 6? " + wuf.connected(1, 6));
        System.out.println(wuf);

        System.out.println("Connected 7, 5? " + wuf.connected(7, 5));
        System.out.println(wuf);


    }
}
