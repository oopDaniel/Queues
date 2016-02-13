import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;         // array of items
    private int N;            // number of elements

    public RandomizedQueue() {                // construct an empty randomized queue
        a = (Item[]) new Object[2];
        N = 0;
    }

    public boolean isEmpty() {                // is the queue empty?
        return N == 0;
    }

    public int size() {                       // return the number of items on the queue
        return N;
    }

    public void enqueue(Item item) {          // add the item
        if (item == null) throw new java.lang.NullPointerException();
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }

    public Item dequeue() {                   // remove and return a random item
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        StdRandom.shuffle(a, 0, N-1);
        Item item = a[N-1];
        a[N-1] = null;                              // to avoid loitering
        N--;
        // shrink size of array if necessary
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    public Item sample() {                    // return (but do not remove) a random item
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        StdRandom.shuffle(a, 0, N-1);
        return a[N-1];
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public Iterator<Item> iterator() { return new ReverseArrayIterator(); }        // return an independent iterator over items in random order

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

    public static void main(String[] args) {  // unit testing
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
