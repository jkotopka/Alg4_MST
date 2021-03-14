package org.kotopka;

public interface MST {
    Iterable<Edge> edges();

    double totalWeight();

    int vertexCount();

    int edgeCount();
}
