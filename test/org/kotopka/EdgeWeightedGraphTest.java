package org.kotopka;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeWeightedGraphTest {

    @Test
    public void constructorThrowsExceptionInvalidVertices() {
        assertThrows(IllegalArgumentException.class, () -> new EdgeWeightedGraph(0));
        assertThrows(IllegalArgumentException.class, () -> new EdgeWeightedGraph(-3));
    }

    @Test
    public void vertexCountCorrectWhenConstructed() {
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(10);
        assertEquals(10, ewg.vertexCount());
    }

    @Test
    public void edgeCountZeroWhenConstructed() {
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(10);
        assertEquals(0, ewg.edgeCount());
    }

    @Test
    public void addEdgeAddsEdge() {
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(10);
        ewg.addEdge(new Edge(2, 5, 3.3));
        assertEquals(1, ewg.edgeCount());
    }

    @Test
    public void adjReturnsAdjacencyList() {
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(10);
        ewg.addEdge(new Edge(2, 5, 3.3));

        Iterable<Edge> edgeIterable = ewg.adj(2);   // edge for vertex "v"
        int edgeCount = 0;

        for (Edge e : edgeIterable){
//            System.out.println(e);
            edgeCount++;
        }

        assertEquals(1, edgeCount);

        Iterable<Edge> otherEdgeIterable = ewg.adj(5);  // edge for vertex "w"
        edgeCount = 0;
        for (Edge e : edgeIterable){
//            System.out.println(e);
            edgeCount++;
        }

        assertEquals(1, edgeCount);
    }

    @Test
    public void edgesReturnsAllEdges() {
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

        Iterable<Edge> edges = ewg.edges();
        int edgeCount = 0;

        for (Edge e : edges) {
//            System.out.println(e);
            edgeCount++;
        }

        assertEquals(16, edgeCount);
    }

    @Disabled
    @Test
    public void toStringReturnsString() {
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(8);

        System.out.println(ewg);

        // graph from Algorithms 4th ed. pg. 604
        ewg.addEdge(new Edge(4, 5, 0.35));

        System.out.println(ewg);

        ewg.addEdge(new Edge(4, 7, 0.37));
        ewg.addEdge(new Edge(5, 7, 0.28));
        ewg.addEdge(new Edge(0, 7, 0.16));
        ewg.addEdge(new Edge(1, 5, 0.32));
        ewg.addEdge(new Edge(0, 4, 0.38));
        ewg.addEdge(new Edge(2, 3, 0.17));
        ewg.addEdge(new Edge(1, 7, 0.19));
        ewg.addEdge(new Edge(0, 2, 0.26));

        System.out.println(ewg);

        ewg.addEdge(new Edge(1, 2, 0.36));
        ewg.addEdge(new Edge(1, 3, 0.29));
        ewg.addEdge(new Edge(2, 7, 0.34));
        ewg.addEdge(new Edge(6, 2, 0.40));
        ewg.addEdge(new Edge(3, 6, 0.52));
        ewg.addEdge(new Edge(6, 0, 0.58));
        ewg.addEdge(new Edge(6, 4, 0.93));

        System.out.println(ewg);
    }

}