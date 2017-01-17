/*----------------------------------------------------------------
*  Author:        Daniel Chiang
*  Written:       1/17/2017
*  Last updated:  1/17/2017
*
*  Compilation:   javac -cp $CLASSPATH Deque.java
*  Execution:     java Deque
*  
*
*  Basic data structure implementation: Deque (Double-ended queue)
*
*----------------------------------------------------------------*/

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item value;
        private Node next;
        private Node prev;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node curr = head;

        public boolean hasNext() { return curr != null; }
        public void    remove()  { throw new java.lang.UnsupportedOperationException(); }
        public Item    next() {
            if (curr == null) throw new java.util.NoSuchElementException();
            Item item = curr.value;
            curr      = curr.next;
            return item;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() { return size == 0; }
    public int     size()    { return size; }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException();

        Node newNode  = new Node();
        newNode.value = item;

        if (size == 0) {
            tail      = newNode;
        } else {
            head.prev = newNode;
        }

        newNode.next  = head;
        head          = newNode;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException();

        Node newNode  = new Node();
        newNode.value = item;

        if (size == 0) {
            head      = newNode;
        } else {
            tail.next = newNode;
        }

        newNode.prev  = tail;
        tail          = newNode;
        size++;
    }

    public Item removeFirst() {
        if (size == 0) throw new java.util.NoSuchElementException();

        Item item = head.value;
        size--;

        if (size == 0) {
            head = null;
            tail = null;
        } else {
            head.next.prev = null;
            head = head.next;
        }
        return item;
    }

    public Item removeLast() {
        if (size == 0) throw new java.util.NoSuchElementException();

        Item item = tail.value;
        size--;

        if (size == 0) {
            head = null;
            tail = null;
        } else {
            tail.prev.next = null;
            tail = tail.prev;
        }
        return item;
    }

    public Iterator<Item> iterator() { return new DequeIterator(); }

    public static void main(String[] args) { }
}
