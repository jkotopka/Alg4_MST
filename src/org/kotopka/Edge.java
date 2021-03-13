package org.kotopka;

/**
 * <code>Edge</code> - Used to represent an edge in a weighted graph.
 */
public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    private final double weight;

    /**
     * Edge data type constructor.
     * @param v one vertex incident to this edge
     * @param w the other vertex incident to this edge
     * @param weight the weight of the edge
     */
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * either() - Arbitrarily returns one vertex incident to this edge
     * @return an integer representing a vertex incident to this edge
     */
    public int either() {
        return v;
    }

    /**
     * other() - Returns the other vertex of the edge incident to the vertex represented by the argument supplied.
     * @param vertex one of the vertices incident to the edge
     * @return the other vertex incident to this edge
     */
    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Invalid vertex");
    }

    /**
     * compareTo() - Implementation of the compareTo() method that is required by the Comparable interface.
     * Compares the weight of this edge to another edge supplied as a method argument.
     * @param other the other edge to compare to
     * @return integer:<br>
     * -1 if this edge's weight is less than the "other" edge's weight.<br>
     * 0 if both edges have the same weight<br>
     * +1 if this edge's weight is greater than the "other" edge's weight.
     */
    public int compareTo(Edge other) {
        if (other == null) throw new IllegalArgumentException("null argument to compareTo() method");

        if      (this.weight > other.weight) return 1;
        else if (this.weight < other.weight) return -1;

        return 0;
    }

    /**
     * weight() - The weight of this edge
     * @return a double of the weight of this edge
     */
    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
