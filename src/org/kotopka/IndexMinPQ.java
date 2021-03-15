package org.kotopka;

/**
 * <code>IndexMinPQ</code> - Implements the indexed minimum priority queue.
 * This implementation uses fixed-size internal arrays which are constructed during object instantiation.
 * Internal arrays are indexed from 1 (one) for index arithmetic simplicity.
 * @param <Key>
 */
public class IndexMinPQ<Key extends Comparable<Key>>{

    private final Key[] keys;
    private final int[] pq;
    private final int[] qp;
    private int size;

    @SuppressWarnings("unchecked")
    public IndexMinPQ(int capacity) {
        this.keys = (Key[]) new Comparable[capacity + 1];
        this.pq = new int[capacity + 1];
        this.qp = new int[capacity + 1];
    }

    public void insert(int i, Key key) {

    }

    public void decreaseKey(int i, Key key) {

    }

    public boolean contains(int i) {

    }

    public int delMin() {

    }

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

}
