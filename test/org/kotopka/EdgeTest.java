package org.kotopka;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    @Test
    public void eitherReturnsVertex() {
        Edge e = new Edge(2, 3, 0.5);
        assertEquals(2, e.either());
    }

    @Test
    public void otherReturnsOtherVertex() {
        Edge e = new Edge(2, 3, 0.5);
        assertEquals(3, e.other(2));
    }

    @Test
    public void weightReturnsCorrectWeight() {
        Edge e = new Edge(2, 3, 0.5);
        assertEquals(0.5, e.weight());
    }
}