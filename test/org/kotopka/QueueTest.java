package org.kotopka;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void isEmpty() {
        Queue<Integer> q = new Queue<>();
        assertTrue(q.isEmpty());
        q.enqueue(1);
        assertFalse(q.isEmpty());
    }

    @Test
    void correctSizeReported() {
        Queue<Integer> q = new Queue<>();
        assertEquals(0, q.size());
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        assertEquals(5, q.size());
    }

    @Test
    void sizeIsZeroAfterDequeueingEverything() {
        Queue<Integer> q = new Queue<>();
        assertEquals(0, q.size());
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        assertEquals(0, q.size());
    }

    @Test
    void dequeueReturnsItemsInCorrectOrder() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
        assertEquals(4, q.dequeue());
        assertEquals(5, q.dequeue());

        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
    }

    @Test
    void peekFirstWorks() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(7);
        q.enqueue(17);
        assertEquals(7, q.peekFirst());
    }

    @Test
    void peekLastWorks() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(7);
        q.enqueue(17);
        assertEquals(17, q.peekLast());
    }

    @Test
    void enqueue() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(7);
        q.enqueue(17);
        assertFalse(q.isEmpty());
        assertEquals(2, q.size());
        assertEquals(7, q.peekFirst());
    }

    @Test
    void correctItemDequeued() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
        assertEquals(4, q.dequeue());
        assertEquals(5, q.dequeue());
    }

    @Test
    void throwExceptionDequeueEmptyQueue() {
        Queue<Integer> q = new Queue<>();
        assertThrows(IllegalArgumentException.class, q::dequeue);
    }

    @Test
    void throwExceptionAfterEmptyingQueue() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.dequeue();
        assertThrows(IllegalArgumentException.class, q::dequeue);
    }

    @Test
    void iteratorHasNextThrowsExceptionModified() {
        Queue<Integer> q = new Queue<>();
        Iterator<Integer> itor = q.iterator();
        q.enqueue(1);
        assertThrows(ConcurrentModificationException.class, itor::hasNext);
    }

    @Test
    void iteratorHasNextReturnsFalseWithEmptyQueue() {
        Queue<Integer> q = new Queue<>();
        Iterator<Integer> itor = q.iterator();
        assertFalse(itor.hasNext());
    }

    @Test
    void iteratorHasNextReturnsFalseAfterDepletion() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(11);
        q.enqueue(32);
        q.enqueue(53);
        Iterator<Integer> itor = q.iterator();

        while (itor.hasNext()) {
            itor.next();
        }

        assertFalse(itor.hasNext());
    }

    @Test
    void iteratorHasNextReturnsTrueWithItemsRemaining() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(11);
        q.enqueue(32);
        q.enqueue(53);
        Iterator<Integer> itor = q.iterator();

        assertTrue(itor.hasNext());

        itor.next();
        assertTrue(itor.hasNext());

        itor.next();
        assertTrue(itor.hasNext());
    }

    @Test
    void iteratorNextThrowsExceptionModified() {
        Queue<Integer> q = new Queue<>();
        Iterator<Integer> itor = q.iterator();
        q.enqueue(1);
        assertThrows(ConcurrentModificationException.class, itor::next);
    }


    @Test
    void iteratorNextThrowsExceptionEmptyQueue() {
        Queue<Integer> q = new Queue<>();
        Iterator<Integer> itor = q.iterator();
        assertThrows(IllegalArgumentException.class, itor::next);
    }

    @Test
    void iteratorNextThrowsExceptionAfterEnd() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        Iterator<Integer> itor = q.iterator();
        while (itor.hasNext()) {
            // run iterator to the end
            itor.next();
        }
        assertThrows(IllegalArgumentException.class, itor::next);
    }

    @Test
    void iteratorNextReturnsCorrectOrderOfElements() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        Iterator<Integer> itor = q.iterator();
        while (itor.hasNext()) {
            assertEquals(1, itor.next());
            assertEquals(2, itor.next());
            assertEquals(3, itor.next());
        }
    }

}
