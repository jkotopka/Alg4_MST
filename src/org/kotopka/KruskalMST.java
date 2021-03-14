package org.kotopka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <code>KruskalMST</code> - Implements the Kruskal Minimum-Spanning Tree algorithm.
 */
public class KruskalMST {

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

    public Iterable<Edge> edges() {
        return mst;
    }

    public double totalWeight() { return totalWeight; }

    public int vertexCount() { return vertexCount; }

    public int edgeCount() { return edgeCount; }

    // test client, to correspond with the trace on pg. 627 of Algorithms 4th 3d.
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Error: missing commandline argument.");
            System.exit(-1);
        }

        try (Scanner reader = new Scanner(new File(args[0]))) {
            int vertexCount = reader.nextInt();
            int edgeCount = reader.nextInt();

            System.out.println("Edge-weighted Graph Vertices: " + vertexCount);
            System.out.println("Edge-weighted Graph Edges: " + edgeCount);
            System.out.println();

            EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertexCount);

            while (reader.hasNext()) {
                ewg.addEdge(new Edge(reader.nextInt(), reader.nextInt(), reader.nextDouble()));
            }

            KruskalMST mst = new KruskalMST(ewg);

            System.out.println("MST Vertices: " + mst.vertexCount());
            System.out.println("MST Edges: " + mst.edgeCount());
            System.out.println("MST Total Weight: " + mst.totalWeight());
            System.out.println("MST:");

            for (Edge e : mst.edges()) {
                System.out.println(e);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
