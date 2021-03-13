package org.kotopka;

/**
 * <code>MinPQ</code> - A min-value priority queue. Uses a dynamic-resizing array as a binary-heap.
 */
public class MinPQ<Key extends Comparable<Key>>{

    private Key[] heap;

    public MinPQ() {
        this(15);    // arbitrarily start with 15 elements, which will fill out the 4th rank of a binary heap
    }

    @SuppressWarnings("unchecked")
    public MinPQ(int capacity) {
        this.heap = (Key[]) new Comparable[capacity];
    }

    private void resize(int newSize) {
        // TODO:
    }

    private void swap(int a, int b) {
        // TODO:
    }

    private void sink() {
        // TODO:
    }

    private void swim() {
        // TODO:
    }

    void insert(Key key) {
        // TODO:
    }

    public Key min() {
        // TODO:
        return null;
    }

    public Key delMin() {
        // TODO:
        return null;
    }

    public boolean isEmpty() {
        // TODO:
        return true;
    }

    public int size() {
        // TODO:
        return 0;
    }
}
