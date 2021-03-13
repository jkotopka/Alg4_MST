package org.kotopka;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MinPQTest {

    @Test
    public void defaultConstructorSize16Heap() {
        MinPQ<String> pq = new MinPQ<>();
        assertEquals(16, pq.heapLength());
    }

    @Test
    public void constructorSetCapacity3() {
        MinPQ<String> pq = new MinPQ<>(3);
        assertEquals(4, pq.heapLength());
    }

    @Test
    public void isEmptyNewPQ() {
        MinPQ<String> pq = new MinPQ<>();
        assertTrue(pq.isEmpty());
    }

    @Test
    public void notEmptyWhenItemsInserted() {
        MinPQ<String> pq = new MinPQ<>();
        pq.insert("test");
        assertFalse(pq.isEmpty());
    }

    @Test
    public void sizeZeroWhenNew() {
        MinPQ<String> pq = new MinPQ<>();
        assertEquals(0, pq.size());
    }

    @Test
    public void sizeThreeAfterInsertsAndRemovals() {
        MinPQ<String> pq = new MinPQ<>();
        pq.insert("test1");
        pq.insert("test2");
        pq.insert("test3");
        pq.insert("test4");
        pq.insert("test5");
        pq.delMin();
        pq.delMin();
        assertEquals(3, pq.size());
    }

    @Test
    public void resizeDoublesHeapLength() {
        MinPQ<String> pq = new MinPQ<>(3);
        pq.insert("test1");
        pq.insert("test2");
        pq.insert("test3");
        pq.insert("test4");
        assertEquals(7, pq.heapLength());   // now six elements + one unused zeroth element
    }

    @Test
    public void resizeHalvesHeapLengthWhenLessThanOneQuarterFull() {
        MinPQ<String> pq = new MinPQ<>(1);
        pq.insert("test1");
        pq.insert("test2");
        pq.insert("test3");
        pq.insert("test4");
        pq.insert("test5");
        pq.insert("test6");
        pq.insert("test7");
        pq.insert("test8");

        assertEquals(9, pq.heapLength());

        pq.delMin();
        pq.delMin();
        pq.delMin();
        pq.delMin();
        pq.delMin();
        pq.delMin();

        assertEquals(5, pq.heapLength());
    }

    @Test
    public void correctSizeReported() {
        MinPQ<String> pq = new MinPQ<>();
        pq.insert("test1");
        assertEquals(1, pq.size());
        pq.insert("test2");
        assertEquals(2, pq.size());
        pq.insert("test3");
        assertEquals(3, pq.size());
        pq.insert("test4");
        assertEquals(4, pq.size());
        pq.insert("test5");
        assertEquals(5, pq.size());
    }

    @Test
    public void minReturnsMinimumElement() {
        MinPQ<Integer> pq = new MinPQ<>();

        pq.insert(2);
        pq.insert(3);
        pq.insert(1);
        pq.insert(5);
        pq.insert(4);

        assertEquals(1, pq.min());
    }

    @Test
    public void delMinReturnsMinElementAndReducesSize() {
        MinPQ<Integer> pq = new MinPQ<>();

        pq.insert(2);
        pq.insert(3);
        pq.insert(1);
        pq.insert(5);
        pq.insert(4);

        assertEquals(1, pq.delMin());
        assertEquals(4, pq.size());

        assertEquals(2, pq.delMin());
        assertEquals(3, pq.size());

        assertEquals(3, pq.delMin());
        assertEquals(2, pq.size());
    }

    @Test
    public void constructorThrowsExceptionInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new MinPQ<>(0));
    }

    @Test
    public void insertThrowsExceptionNullItem() {
        MinPQ<Integer> pq = new MinPQ<>();
        assertThrows(IllegalArgumentException.class, () -> pq.insert(null));
    }

    @Test
    public void delMinThrowsExceptionWhenEmpty() {
        MinPQ<Integer> pq = new MinPQ<>();
        assertThrows(NoSuchElementException.class, pq::delMin);
    }

}