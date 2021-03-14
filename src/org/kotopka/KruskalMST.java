package org.kotopka;

/**
 * <code>KruskalMST</code> - Implements the Kruskal Minimum-Spanning Tree algorithm.
 */
public class KruskalMST implements MST {

    private final Queue<Edge> mst;
    private double totalWeight;
    private int edgeCount;
    private final int vertexCount;

    /**
     * <code>KruskalMST</code> - Constructor. Takes an object of type <code>EdgeWeightedGraph</code> and creates an MST
     * using the Kruskal algorithm. This constructor is responsible for all of the object's internal logic.
     * @param edgeWeightedGraph EdgeWeightedGraph object from which to build the MST
     */
    public KruskalMST(EdgeWeightedGraph edgeWeightedGraph) {
        this.mst = new Queue<>();
        this.vertexCount = edgeWeightedGraph.vertexCount();
        MinPQ<Edge> edgeMinPQ = new MinPQ<>(edgeWeightedGraph.edgeCount());

        for (Edge e : edgeWeightedGraph.edges()) {
            edgeMinPQ.insert(e);
        }

        WeightedUnionFind cycleDetector = new WeightedUnionFind(vertexCount);

        // The MST will have vertexCount - 1 edges. Fencepost counting,
        // where each vertex is the "fencepost" and each edge is the "panel" in-between them
        while (!edgeMinPQ.isEmpty() && mst.size() < vertexCount - 1) {
            Edge currentEdge = edgeMinPQ.delMin();
            int v = currentEdge.either();
            int w = currentEdge.other(v);

            // Don't add this edge if it creates a cycle in the MST
            if (!cycleDetector.connected(v, w)) {
                mst.enqueue(currentEdge);
                cycleDetector.union(v, w);
                totalWeight += currentEdge.weight();
                edgeCount++;
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double totalWeight() { return totalWeight; }

    @Override
    public int vertexCount() { return vertexCount; }

    @Override
    public int edgeCount() { return edgeCount; }

    // test client
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Error: missing commandline argument.");
            System.exit(-1);
        }

        TestClient tc = new TestClient(new KruskalMST(GraphLoader.load(args[0])));
        tc.printMst();
    }

}
