package org.kotopka;

import java.util.NoSuchElementException;

/**
 * <code>MinPQ</code> - A min-value priority queue. Uses a dynamically-resizing array as a binary-heap.
 * This implementation uses an array indexed from 1 (one) for index arithmetic simplicity.
 */
public class MinPQ<Key extends Comparable<Key>>{

    private Key[] heap;
    private int size;

    /**
     * <code>MinPQ()</code> - Default constructor. Initializes the internal heap to 15 elements.
     */
    public MinPQ() {
        this(15);    // arbitrarily start with 15 elements, which will fill out the 4th rank of a binary heap
    }

    /**
     * <code>MinPO()</code> - Constructor with argument <code>int capacity</code> for the initial capacity of the internal heap.
     * @param capacity integer for the initial size of the internal heap
     */
    @SuppressWarnings("unchecked")
    public MinPQ(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Invalid capacity value");

        this.heap = (Key[]) new Comparable[capacity + 1];   // add one for the unused first heap element
    }

    /**
     * <code>resize()</code> Private method, resizes the internal heap array.
     * @param newSize integer representing the new size of the internal heap array.
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        if (newSize <= 0) throw new IllegalArgumentException("Invalid newSize value");  // should not throw this...

        Key[] newHeap = (Key[]) new Comparable[newSize];

        // copy from index 1 rather than 0 to accommodate unused zeroth heap element
        if (size >= 0) System.arraycopy(heap, 1, newHeap, 1, size);
        heap = newHeap;
    }

    /**
     * <code>swap()</code> Private method, swaps the position of two elements in the internal heap array.
     * @param a integer index of one item to be swapped
     * @param b integer index of the other item to be swapped
     */
    private void swap(int a, int b) {
        Key temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    /**
     * <code>isGreaterThan()</code> Private method, returns true if the element at heap[a] > heap[b]
     * @param a integer of the heap index for element a
     * @param b integer of the heap index for element b
     * @return boolean value, true if heap[a] > heap[b], false otherwise
     */
    private boolean isGreaterThan(int a, int b) {
        return heap[a].compareTo(heap[b]) > 0;
    }

    /**
     * <code>sink()</code> Private method, puts the element "k" into the correct place to maintain the heap property.
     * The <code>sink()</code> method is used to restore order after a deletion.
     * @param k integer of the index where the item to swim originates
     */
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;

            // find the smaller of the two children at nodes j and j + 1
            // "size" is the last VALID element in the heap
            if (j < size && isGreaterThan(j, j + 1)) { j++; }

            // if the heap property has been restored, break
            if (!isGreaterThan(k, j)) { break; }

            swap(j, k);
            k = j;
        }
    }

    /**
     * <code>swim()</code> - Private method, puts the element "k" into the correct place to maintain the heap property.
     * The <code>swim()</code> method is used to restore order after an insertion.
     * @param k integer of the index where the item to swim originates
     */
    private void swim(int k) {
        while(k > 1 && isGreaterThan(k/2, k)) {
            swap(k/2, k);
            k = k/2;
        }
    }

    /**
     * <code>insert()</code> - Inserts a <code>key</code> into this priority queue.
     * @param key Java generic, reference type specified during instantiation of this <code>MinPQ</code> object
     */
    public void insert(Key key) {
        if (key == null) throw new IllegalArgumentException("Method cannot accept null values");

        if (size + 1 == heap.length) {
            int newSize = heap.length - 1;  // temporarily remove the space for the unused first element...
            newSize *= 2;   // ..double the size of the actual usable space...
            newSize += 1;   // ...re-add space for the unused first element
            resize(newSize);
        }

        // insert the item then swim it up
        heap[++size] = key;
        swim(size);
    }

    /**
     * <code>min()</code> - Returns the minimum element of this priority queue without removing it.
     * @return Java generic, reference type specified during instantiation of this <code>MinPQ</code> object
     */
    public Key min() {
        return heap[1];
    }

    /**
     * <code>delMin()</code> - Removes and returns the minimum element of this priority queue.
     * @return Java generic, reference type specified during instantiation of this <code>MinPQ</code> object
     */
    public Key delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority Queue is empty");

        Key min = heap[1];

        swap(1, size--);
        sink(1);
        heap[size + 1] = null; // be kind to the JVM GC

        if (size >= 1 && size <= heap.length / 4) {
            int newSize = heap.length - 1;
            newSize /= 2;
            newSize += 1;
            resize(newSize);
        }

        return min;
    }

    /**
     * <code>isEmpty()</code> - Returns true if the priority queue is empty, false otherwise.
     * @return boolean value, true if the priority queue is empty, false otherwise
     */
    public boolean isEmpty() { return size == 0; }

    /**
     * <code>size()</code> - Returns the number of elements currently in this priority queue
     * @return integer representing the number of elements contained herein
     */
    public int size() { return size; }

    /**
     * <code>heapLength()</code> - Package private method for unit testing, returns the length of the heap array.
     * @return integer of the heap length
     */
    int heapLength() { return heap.length; }

    /**
     * <code>getHeap()</code> - Package private method for unit testing, returns the heap array.
     * @return Comparable<Key>[] array
     */
    Comparable<Key>[] getHeap() { return heap; }

    public static void main(String[] args) {
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

        MinPQ<Edge> edges = new MinPQ<>();

        for (Edge e : ewg.edges()) {
            edges.insert(e);
        }

        while (!edges.isEmpty()) {
            System.out.println(edges.delMin());
        }
    }
}
