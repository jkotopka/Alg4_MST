package org.kotopka;

/**
 * <code>EdgeWeightedGraph</code> - An abstract data type implementing an edge-weighted undirected graph.<br>
 * Largely copied from the Algorithms II course on Coursera.
 */
public class EdgeWeightedGraph {

    private final int vertexCount;
    private final Bag<Edge>[] adj;    // vertex-indexed array of Edge lists
    private int edgeCount;

    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int v) {
        this.vertexCount = v;
        this.adj = (Bag<Edge>[]) new Bag[v];

        for (int i = 0; i < vertexCount; i++) {
            adj[i] = new Bag<>();
        }
    }

    /**
     * addEdgedgeCount() - Adds an edge to this edge-weighted undirected graph
     * @param e the edge to be added
     */
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);

        adj[v].add(e);
        adj[w].add(e);
        edgeCount++;
    }

    /**
     * adj() - Returns an object of type Iterable of all edges incident to vertex v.
     * @param v The vertex incident to the desired edges
     * @return An object of type Iterable. Order should be considered arbitrary.
     */
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    /**
     * edges() - Returns an object of type Iterable of all edges in the edge-weighted undirected graph.
     * @return An object of type Iterable containing all of the edges in this graph. Order should be considered arbitrary.
     */
    public Iterable<Edge> edges() {
        Bag<Edge> allEdges = new Bag<>();

        for (int v = 0; v < vertexCount; v++) {
            for (Edge e : adj(v)) {
                if (e.other(v) > v) allEdges.add(e);
            }
        }

        return allEdges;
    }

    /**
     * vertexCount() - The number of vertices in this edge-weighted undirected graph.
     * @return an integer of the number of vertices
     */
    public int vertexCount() { return vertexCount; }

    /**
     * edgeCount() - The number of edges in this edge-weighted undirected graph
     * @return an integer of the number of edges
     */
    public int edgeCount() { return edgeCount; }

    /**
     * <code>toString()</code> - String representation of this graph
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (Edge e : edges()) {
            sb.append("(").append(e).append("), ");
        }

        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }

        sb.append("]");

        return sb.toString();
    }
}
