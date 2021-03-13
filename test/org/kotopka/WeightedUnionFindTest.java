package org.kotopka;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightedUnionFindTest {

    @Test
    public void countCorrectWUFCreation() {
        WeightedUnionFind wuf = new WeightedUnionFind(10);
        assertEquals(10, wuf.count());
    }

    @Test
    public void unionJoinsTwoSites() {
        WeightedUnionFind wuf = new WeightedUnionFind(10);
        assertFalse(wuf.connected(0, 5));
        wuf.union(0, 5);
        assertTrue(wuf.connected(0, 5));
    }

    @Test
    public void findSelectsCorrectRoot() {
        WeightedUnionFind wuf = new WeightedUnionFind(10);
        assertEquals(1, wuf.find(1));
        wuf.union(1, 2);
        wuf.union(3, 2);
        assertEquals(1, wuf.find(3));
    }

    @Test
    public void countCorrectWUFAfterThreeUnions() {
        WeightedUnionFind wuf = new WeightedUnionFind(10);
        wuf.union(0, 1);
        wuf.union(2, 3);
        wuf.union(4, 5);
        wuf.union(6, 7);
        wuf.union(8, 9);

        assertEquals(5, wuf.count());
    }

}