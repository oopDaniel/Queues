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
    private Item[] itemArray;    // array of items
    private int size;            // number of elements

    /**
     * construct an empty randomized queue
     *
     */
    // @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        itemArray = (Item[]) new Object[2];
        size = 0;
    }

    /**
     * is the queue empty?
     *
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the queue
     *
     */
    public int size() {
        return size;
    }

    /**
     * add the item
     *
     * @param the Item needs to be added
     *
     */
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        if (size == itemArray.length) resize(2 * itemArray.length);
        itemArray[size++] = item;
    }

    /**
     * remove and return a random item
     *
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int randomIndex = StdRandom.uniform(size);
        Item item = itemArray[randomIndex];
        itemArray[randomIndex] = itemArray[size - 1];
        itemArray[size - 1] = null;                          // to avoid loitering
        size--;
        // shrink size of array if necessary
        if (size > 0 && size == itemArray.length / 4) resize(itemArray.length / 2);
        return item;
    }

    /**
     * return (but do not remove) a random item
     *
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return itemArray[ StdRandom.uniform(size) ];
    }

    /**
     * resize the underlying array holding the elements
     *
     * @param the size for new array
     *
     */
    // @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity >= size;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = itemArray[i];
        }
        itemArray = temp;
    }

    /**
     * return an independent iterator over items in random order
     *
     */
    public Iterator<Item> iterator() { return new ArrayIterator(); }

    private class ArrayIterator implements Iterator<Item> {
        private int index;
        private Item[] iterArr;

        public ArrayIterator() {
            index = 0;
            iterArr = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                iterArr[i] = itemArray[i];
            }
            StdRandom.shuffle(iterArr);
        }

        public boolean hasNext() {
            return index < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return iterArr[index++];
        }
    }

    /**
     * testing
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
