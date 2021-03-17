package org.kotopka;

import java.util.Arrays;

/**
 * <code>EagerPrimMST</code> - Implements an eager version of Prim's Minimum-Spanning Tree algorithm.
 * This version will remove unneeded edges from the internal priority queue when it's determined they are
 * no longer a valid shortest path.
 * <br><br>
 * Adapted from <a href="https://algs4.cs.princeton.edu/home/">Altorithms 4th ed.</a> by Robert Sedgewick and Kevin Wayne
 * <br><br>
 * Note: This version produces slightly different results from the Lazy Prim algorithm from <code>LazyPrimMST</code>:
 * The totalWeight may be a different value due to the way the <code>double</code> values are added and the order
 * of the <code>Edge</code> objects differs.
 */
public class EagerPrimMST implements MST {

    private final int vertexCount;
    private final Edge[] edgeTo;
    private final double[] distTo;
    private final boolean[] marked;
    private IndexMinPQ<Double> pq;
    private double totalWeight;
    private int edgeCount;

    public EagerPrimMST(EdgeWeightedGraph edgeWeightedGraph) {
        this.vertexCount = edgeWeightedGraph.vertexCount();

        this.edgeTo = new Edge[vertexCount];
        this.distTo = new double[vertexCount];
        this.marked = new boolean[vertexCount];
        this.pq = new IndexMinPQ<>(vertexCount);

        this.totalWeight = Double.NEGATIVE_INFINITY;
        this.edgeCount = Integer.MIN_VALUE;

        Arrays.fill(distTo, Double.POSITIVE_INFINITY);

        // does not compute minimum-spanning-forest
//        distTo[0] = 0.0;
//        pq.insert(0, 0.0);
//
//        while (!pq.isEmpty()) {
//            visit(edgeWeightedGraph, pq.delMin());
//        }

        // computes minimum-spanning-forest
        for (int i = 0; i < vertexCount; i++) {
            if (marked[i]) continue;

            distTo[i] = 0.0;
            pq.insert(i, 0.0);

            while (!pq.isEmpty()) {
                visit(edgeWeightedGraph, pq.delMin());
            }
        }
    }

    private void visit(EdgeWeightedGraph edgeWeightedGraph, int vertex) {
        marked[vertex] =  true;

        for (Edge e : edgeWeightedGraph.adj(vertex)) {
            int w = e.other(vertex);

            if (marked[w]) continue;

            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();

                if (pq.contains(w)) {
                    pq.decreaseKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<>();

        for (int i = 1; i < edgeTo.length; i++) {
            if (edgeTo[i] != null) {
                mst.enqueue(edgeTo[i]);
            }
        }

        return mst;
    }

    @Override
    public double totalWeight() {
        if (totalWeight == Double.NEGATIVE_INFINITY) {
            totalWeight = 0.0;
            for (double d : distTo) totalWeight += d;
        }
        return totalWeight;
    }

    @Override
    public int vertexCount() { return vertexCount; }

    @Override
    public int edgeCount() {
        if (edgeCount == Integer.MIN_VALUE) {
            edgeCount = 0;
            for (Edge e : edgeTo) {
                if (e != null) edgeCount++;
            }
        }

        return edgeCount;
    }

    // test client
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: missing commandline argument.");
            System.exit(-1);
        }

        TestClient tc = new TestClient(new EagerPrimMST(GraphLoader.load(args[0])));
        tc.printMst();
    }
}
