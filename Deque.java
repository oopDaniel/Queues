/******************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque
 *  Dependencies: java.util.Iterator java.util.NoSuchElementException
 *
 *  A double-ended queue or deque is a generalization of a stack and a queue that
 *  supports adding and removing items from either the front or the back of the data structure.
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int N;

    private class Node {
        private Item item;
        private Node next, previous;
    }

    /**
     * construct an empty deque
     *
     */
    public Deque() {
        first = null;
        last = null;
        N = 0;
        assert check();
    }

    /**
     * is the deque empty?
     *
     */
    public boolean isEmpty() {
        return first == null || last == null;
    }

    /**
     * return the number of items on the deque
     *
     */
    public int size() {
        return N;
    }

    /**
     * add the item to the front
     *
     * @param the Item needs to be added
     *
     */
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        if (isEmpty()) {
            last = first;
        } else {
            oldfirst.previous = first;
        }
        N++;
        assert check();
    }

    /**
     * add the item to the end
     *
     * @param the Item needs to be added
     *
     */
    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldlast;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        N++;
        assert check();
    }

    /**
     * remove and return the item from the front
     *
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Underflow");
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        if (isEmpty()) {
            last = null;
        } else {
            first.previous = null;
        }
        N--;
        assert check();
        return item;                   // return the saved item
    }

    /**
     * remove and return the item from the end
     *
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Underflow");
        Item item = last.item;        // save item to return
        last = last.previous;         // delete first node
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        N--;
        assert check();
        return item;                  // return the saved item
    }

    /**
     * return an iterator over items in order from front to end
     *
     */
    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * check if the operation is valid
     *
     */
    private boolean check() {
        if (N < 0) {
            return false;
        }
        if (N == 0) {
            if (first != null) return false;
        }
        else if (N == 1) {
            if (first == null) return false;
            if (first.next != null || first.previous != null ||
                last.next != null  || last.previous != null) return false;
        }
        else {
            if (first == null || last == null) return false;
            if (first.next == null || last.previous == null) return false;
        }
        return true;
    }

    /**
     * unit testing
     *
     */
    public static void main(String[] args) {
        Deque<String> d = new Deque<String>();
        System.out.println("Size: "+d.size()+"\nAdd 'abc'");
        d.addFirst("abc");
        System.out.println("dequeue: "+d.removeLast());
        d.addFirst("a1");
        d.addLast("c3");
        d.addFirst("b2");
        d.addLast("Last!!");

        // System.out.println("pop: "+d.removeFirst());
        // System.out.println("pop: "+d.removeLast());
        // System.out.println("pop: "+d.removeFirst());
        // System.out.println("pop: "+d.removeFirst());
        for (String s : d) {
            System.out.print(s);
        }
    }
}
