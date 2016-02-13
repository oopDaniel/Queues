public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int N;

    private class Node {
        Item item;
        Node next, previous;
    }

    public Deque() {                          // construct an empty deque
        first = null;
        last = null;
        hasData = false;
        N = 0;
        assert check();
    }

    public boolean isEmpty() {                // is the deque empty?
        return first == null && last == null;
    }

    public int size() {                       // return the number of items on the deque
        return N;
    }

    public void addFirst(Item item) {         // add the item to the front
        Node oldfirst = first;
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        if (first.next == first.previous) {
            last = first;
        }
        N++;
        assert check();
    }

    public void addLast(Item item) {          // add the item to the end
        Node oldlast = last;
        last.item = item;
        last.next = null;
        last.previous = oldlast;
        if (last.next == last.previous) {
            first = last;
        }
        N++;
        assert check();
    }

    public Item removeFirst() {               // remove and return the item from the front
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        first.previous = null;
        N--;
        assert check();
        return item;                   // return the saved item
    }

    public Item removeLast() {                // remove and return the item from the end
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = last.item;        // save item to return
        last = last.previous;            // delete first node
        last.next = null;
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

    public static void main(String[] args) {  // unit testing

    }
}
