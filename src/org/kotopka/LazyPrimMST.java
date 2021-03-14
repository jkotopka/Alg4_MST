package org.kotopka;

/**
 * <code>LazyPrimMST</code> - Implements a lazy version of Prim's Minimum-Spanning Tree algorithm.
 */
public class LazyPrimMST implements MST {

    private int vertexCount;
    private final boolean[] marked;
    private final Queue<Edge> mst;
    private final MinPQ<Edge> edgeMinPQ;
    private int edgeCount;
    private double totalWeight;

    public LazyPrimMST(EdgeWeightedGraph edgeWeightedGraph) {
        this.vertexCount = edgeWeightedGraph.vertexCount();
        this.marked = new boolean[vertexCount];
        this.mst = new Queue<>();
        this.edgeMinPQ = new MinPQ<>();

        visit(edgeWeightedGraph, 0);

        while (!edgeMinPQ.isEmpty()) {
            Edge current = edgeMinPQ.delMin();
            int v = current.either();
            int w = current.other(v);

            if (marked[v] && marked[w]) continue;

            // one of these vertices will be marked but we don't know which yet
            if (!marked[v]) visit(edgeWeightedGraph, v);
            if (!marked[w]) visit(edgeWeightedGraph, w);

            mst.enqueue(current);
            edgeCount++;
            totalWeight += current.weight();
        }
    }

    private void visit(EdgeWeightedGraph edgeWeightedGraph, int vertex) {
        marked[vertex] = true;

        // add all incident edges to this vertex if the other end is not already marked
        for (Edge e : edgeWeightedGraph.adj(vertex)) {
            if (!marked[e.other(vertex)]) edgeMinPQ.insert(e);
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public int vertexCount() {
        return vertexCount;
    }

    @Override
    public int edgeCount() {
        return edgeCount;
    }

    @Override
    public double totalWeight() {
        return totalWeight;
    }

    // test client
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Error: missing commandline argument.");
            System.exit(-1);
        }

        TestClient tc = new TestClient(new LazyPrimMST(GraphLoader.load(args[0])));
        tc.printMst();
    }

}
