import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;

    private Node<Item> last;

    private int size;

    public Deque() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        checkItem(item);
        Node<Item> oldFirst = first;
        Node<Item> newNode = new Node<>(null, item, oldFirst);
        first = newNode;
        if (oldFirst == null) {
            last = newNode;
        } else {
            oldFirst.prev = newNode;
        }
        size++;
    }

    public void addLast(Item item) {
        checkItem(item);
        Node<Item> oldLast = last;
        Node<Item> newNode = new Node<>(oldLast, item, null);
        last = newNode;
        if (oldLast == null) {
            first = newNode;
        } else {
            oldLast.next = newNode;
        }
        size++;
    }

    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        Node<Item> next = first.next;
        first.item = null;
        first.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;

        return item;
    }

    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        Node<Item> prev = last.prev;
        last.item = null;
        last.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;

        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
    }

    private void checkItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item must not be null");
        }
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;

        Node(Node<Item> prev, Item item, Node<Item> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        
        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        StdOut.println("Stack should be empty with size zero: " + deque.isEmpty() + ", size = " + deque.size());

        StdOut.println("Adding an item '5' to the head of deque");
        deque.addFirst(5);
        
        StdOut.println("Removing last item: " + deque.removeLast());

        try {
            deque.removeFirst();
        } catch (NoSuchElementException e) {
            StdOut.println("An exception occurred when trying to remove from an empty deque: " + e);
        }

        StdOut.println("Adding an item '7' to the tail of deque");
        deque.addLast(7);

        StdOut.println("Removing first item: " + deque.removeFirst());

        int[] numbers = { 1, 2, 3, 4, 5 };
        StdOut.println("Adding " + numbers.length + " new items to deque");
        for (int num : numbers) {
            deque.addLast(num);
        }
        StdOut.println("Deque contains " + deque.size() + " items: ");
        for (Integer elem : deque) {
            StdOut.println(elem);
        }
    }
}