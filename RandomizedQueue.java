/******************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue
 *  Dependencies: java.util.Iterator java.util.NoSuchElementException StdRandom
 *
 *   A randomized queue is similar to a stack or queue,
 *   except that the item removed is chosen uniformly at random from items in the data structure.
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;         // array of items
    private int N;            // number of elements

    /**
     * construct an empty randomized queue
     *
     */
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        N = 0;
    }

    /**
     * is the queue empty?
     *
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * return the number of items on the queue
     *
     */
    public int size() {
        return N;
    }

    /**
     * add the item
     *
     * @param the Item needs to be added
     *
     */
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }

    /**
     * remove and return a random item
     *
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        StdRandom.shuffle(a, 0, N-1);
        Item item = a[N-1];
        a[N-1] = null;                          // to avoid loitering
        N--;
        // shrink size of array if necessary
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    /**
     * return (but do not remove) a random item
     *
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        StdRandom.shuffle(a, 0, N-1);
        return a[N-1];
    }

    /**
     * resize the underlying array holding the elements
     *
     * @param the size for new array
     *
     */
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    /**
     * return an independent iterator over items in random order
     *
     */
    public Iterator<Item> iterator() { return new ReverseArrayIterator(); }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = N-1;
            StdRandom.shuffle(a, 0, i);
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }
    }

    /**
     * unit testing
     *
     */
    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        r.enqueue("test1");
        r.enqueue("test2");
        r.enqueue("test3");
        r.enqueue("test4");
        r.enqueue("test5");
        // System.out.println(r.sample());
        // System.out.println(r.dequeue());
        for (String s : r) {
            System.out.println(s);
        }
        for (String s : r) {
            System.out.println(s);
        }
    }
}
