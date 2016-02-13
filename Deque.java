import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int N;

    private class Node {
        private Item item;
        private Node next, previous;
    }

    public Deque() {                          // construct an empty deque
        first = null;
        last = null;
        N = 0;
        assert check();
    }

    public boolean isEmpty() {                // is the deque empty?
        return first == null || last == null;
    }

    public int size() {                       // return the number of items on the deque
        return N;
    }

    public void addFirst(Item item) {         // add the item to the front
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

    public void addLast(Item item) {          // add the item to the end
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

    public Item removeFirst() {               // remove and return the item from the front
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

    public Item removeLast() {                // remove and return the item from the end
        if (isEmpty()) throw new NoSuchElementException("Underflow");
        Item item = last.item;        // save item to return
        last = last.previous;            // delete first node
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        N--;
        assert check();
        return item;                   // return the saved item
    }

    // return an iterator over items in order from front to end
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

    private boolean check() {
        // check a few properties of instance variable 'first'
        if (N < 0) {
            return false;
        }
        if (N == 0) {
            if (first != null) return false;
        }
        else if (N == 1) {
            if (first == null) return false;
            if (first.next != null || first.previous != null ||
                last.next != null  || last.previous != null ) return false;
        }
        else {
            if (first == null || last == null) return false;
            if (first.next == null || last.previous == null) return false;
        }
        return true;
    }

    public static void main(String[] args) {  // unit testing
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
