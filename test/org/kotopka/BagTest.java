package org.kotopka;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @Test
    public void newBagIsEmpty() {
        Bag<String> b = new Bag<>();
        assertTrue(b.isEmpty());
    }

    @Test
    public void newBagSizeZero() {
        Bag<String> b = new Bag<>();
        assertEquals(0, b.size());
    }

    @Test
    public void bagWithOneItemNotEmpty() {
        Bag<String> b = new Bag<>();
        b.add("testing, 1-2-3");
        assertFalse(b.isEmpty());
    }

    @Test
    public void bagWithOneItemSizeOne() {
        Bag<String> b = new Bag<>();
        b.add("testing, 1-2-3");
        assertEquals(1, b.size());
    }

    @Test
    public void bagWithThreeItemsSizeThree() {
        Bag<String> b = new Bag<>();
        b.add("one");
        b.add("two");
        b.add("three");
        assertEquals(3, b.size());
    }

    @Test
    public void iteratorHasNextReturnsFalseNewBag() {
        Bag<String> b = new Bag<>();
        Iterator<String> itor = b.iterator();
        assertFalse(itor.hasNext());
    }

    @Test
    public void iteratorHasNextReturnsTrueOneItem() {
        Bag<String> b = new Bag<>();
        b.add("testing");
        Iterator<String> itor = b.iterator();
        assertTrue(itor.hasNext());
    }

    @Test
    public void iteratorHasNextReturnsFalseAfterRunToEnd() {
        Bag<String> b = new Bag<>();
        b.add("testing");
        b.add("one");
        b.add("two");
        b.add("three");

        Iterator<String> itor = b.iterator();
        itor.next();
        itor.next();
        itor.next();
        itor.next();

        assertFalse(itor.hasNext());

    }

    @Test
    public void iteratorNextThrowsExceptionWhenEmpty() {
        Bag<String> b = new Bag<>();
        Iterator<String> itor = b.iterator();
        assertThrows(NoSuchElementException.class, itor::next);
    }

    @Test
    public void iteratorNextReturnsCorrectValueOneItem() {
        Bag<String> b = new Bag<>();
        b.add("testing");
        Iterator<String> itor = b.iterator();
        assertEquals("testing", itor.next());
    }

    @Test
    public void iteratorNextReturnsCorrectValueFourItems() {
        Bag<String> b = new Bag<>();
        b.add("testing");
        b.add("one");
        b.add("two");
        b.add("three");
        Iterator<String> itor = b.iterator();
        assertEquals("three", itor.next()); // Bag inserts items like a stack, iterator should respond top-down
        assertEquals("two", itor.next());
        assertEquals("one", itor.next());
        assertEquals("testing", itor.next());
    }

    @Test
    public void iteratorHasNextThrowsConcurrentModificationException() {
        Bag<String> b = new Bag<>();
        Iterator<String> itor = b.iterator();
        b.add("testing");
        assertThrows(ConcurrentModificationException.class, itor::hasNext);
    }

    @Test
    public void iteratorNextThrowsConcurrentModificationException() {
        Bag<String> b = new Bag<>();
        Iterator<String> itor = b.iterator();
        b.add("testing");
        assertThrows(ConcurrentModificationException.class, itor::next);
    }

}