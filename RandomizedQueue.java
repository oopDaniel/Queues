/*----------------------------------------------------------------
*  Author:        Daniel Chiang
*  Written:       1/17/2017
*  Last updated:  1/17/2017
*
*  Compilation:   javac -cp $CLASSPATH RandomizedQueue.java
*  Execution:     java RandomizedQueue
*  
*
*  Basic data structure implementation: RandomizedQueue
*
*----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int    size;
    private Item[] queue;

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int    curr;
        private int    end;
        private Item[] items;

        public RandomizedQueueIterator(int len) {
            curr = 0;
            end  = len;
            items = (Item[]) new Object[len];
            
            for (int i = 0; i < len; ++i) {
                items[i] = queue[i];
            }
            StdRandom.shuffle(items);
        }

        public boolean hasNext() { return curr < end; }
        public void    remove()  { throw new java.lang.UnsupportedOperationException(); }
        public Item    next()    { 
            if (curr == end) throw new java.util.NoSuchElementException();
            return items[curr++];
        }
    }

    public RandomizedQueue() {
        size  = 0;
        queue = (Item[]) new Object[2];
    }

    public boolean isEmpty() { return size == 0; }
    public int     size()    { return size; }

    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        if (queue.length == size) resize(size * 2);
        queue[size++] = item;
    }
    
    public Item dequeue() {
        if (size == 0) throw new java.util.NoSuchElementException();
        
        int index = StdRandom.uniform(size);

        Item item    = queue[index];
        queue[index] = queue[--size];
        queue[size]   = null;

        if (size > 0 && size == queue.length / 4) resize(queue.length / 2);

        return item;
    }

    public Item sample() {
        if (size == 0) throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(size);
        return queue[index];
    }

    public Iterator<Item> iterator() { return new RandomizedQueueIterator(size); }

    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];

        for (int i = 0; i < size; ++i) copy[i] = queue[i];
        queue = copy;
    }

    public static void main(String[] args) { }
}