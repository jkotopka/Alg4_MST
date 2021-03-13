package org.kotopka;

/**
 * <code>KruskalMST</code> - Implements the Kruskal Minimum-Spanning Tree algorithm.
 */
public class KruskalMST {

    private final Queue<Edge> mst;
    private double totalWeight;
    private int edgeCount;
    private int vertexCount;

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
        while (!edgeMinPQ.isEmpty() && mst.size() < vertexCount) {
            Edge currentEdge = edgeMinPQ.delMin();

            System.out.println("current edge: " + currentEdge);

            int v = currentEdge.either();
            int w = currentEdge.other(v);

//            System.out.println(cycleDetector);

            // Don't add this edge if it creates a cycle in the MST
            if (!cycleDetector.connected(v, w)) {
                mst.enqueue(currentEdge);
                cycleDetector.union(v, w);
                totalWeight += currentEdge.weight();
                edgeCount++;

            }
        }

        System.out.println("MST size: " + mst.size() + " vertex count: " + vertexCount + " pq empty: " + edgeMinPQ.isEmpty());
        System.out.println("connected 6 2: " + cycleDetector.connected(6, 2));
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double totalWeight() { return totalWeight; }

    public int vertexCount() { return vertexCount; }

    public int edgeCount() { return edgeCount; }

    public static void main(String[] args) {

        EdgeWeightedGraph ewg = new EdgeWeightedGraph(8);

        // graph from Algorithms 4th ed. pg. 604
        ewg.addEdge(new Edge(4, 5, 0.35));
        ewg.addEdge(new Edge(4, 7, 0.37));
        ewg.addEdge(new Edge(5, 7, 0.28));
        ewg.addEdge(new Edge(0, 7, 0.16));
        ewg.addEdge(new Edge(1, 5, 0.32));
        ewg.addEdge(new Edge(0, 4, 0.38));
        ewg.addEdge(new Edge(2, 3, 0.17));
        ewg.addEdge(new Edge(1, 7, 0.19));
        ewg.addEdge(new Edge(0, 2, 0.26));
        ewg.addEdge(new Edge(1, 2, 0.36));
        ewg.addEdge(new Edge(1, 3, 0.29));
        ewg.addEdge(new Edge(2, 7, 0.34));
        ewg.addEdge(new Edge(6, 2, 0.40));
        ewg.addEdge(new Edge(3, 6, 0.52));
        ewg.addEdge(new Edge(6, 0, 0.58));
        ewg.addEdge(new Edge(6, 4, 0.93));

        KruskalMST mst = new KruskalMST(ewg);

        System.out.println("Vertex count: " + mst.vertexCount());
        System.out.println("Edge count: " + mst.edgeCount());

        for(Edge e : mst.edges()) {
            System.out.println(e);
        }

        System.out.println("Total weight: " + mst.totalWeight());
    }
}
