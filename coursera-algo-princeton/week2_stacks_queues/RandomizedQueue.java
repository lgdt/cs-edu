import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final Object[] EMPTY_ELEMENTS = new Object[0];

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private Item[] elements;

    public RandomizedQueue() {
        this(DEFAULT_CAPACITY);
    }

    private RandomizedQueue(int initialCap) {
        if (initialCap < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + initialCap);
        }
        elements = (Item[]) new Object[initialCap];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item must not be null");
        }
        if (size == elements.length) {
            resize(2 * elements.length);
        }
        elements[size] = item;
        size++;
    }

    public Item dequeue() {
        int index = randomElementIndex();
        Item item = elementAt(index);
        if (index < size - 1) {
            elements[index] = elements[size - 1];
        }
        size--;
        if (size > 0 && size == elements.length/4) {
            resize(elements.length/2);
        }
        return item;
    }

    public Item sample() {
        int index = randomElementIndex();
        return elementAt(index);
    }

    private int randomElementIndex() {
        return size == 0 ? -1 : StdRandom.uniform(size);
    }

    private Item elementAt(int index) {
        if (isEmpty() || index >= size) {
            throw new NoSuchElementException();
        }

        return elements[index];
    }

    public Iterator<Item> iterator() {
        return new QueueItr<Item>(elements, size);
    }

    private void resize(int capacity) {
        assert capacity >= size;

        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    private static class QueueItr<Item> implements Iterator<Item> {
        private final Item[] elements;
        
        private int next;

        QueueItr(Item[] elements, int size) {
            this.elements = size == 0 ? (Item[]) EMPTY_ELEMENTS : (Item[]) new Object[size];
            if (size > 0) {
                for (int i = 0; i < this.elements.length; i++) {
                    this.elements[i] = elements[i];
                }
                StdRandom.shuffle(this.elements);
            }
        }

        public boolean hasNext() {
            return next < elements.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elements[next++];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        StdOut.println("Initializing the queue with numbers from 0 to 4");
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
            StdOut.println("Sample number on " + i + "th iteration: " + queue.sample());
        }
        StdOut.println("Dequeued element: " + queue.dequeue() + ", new queue size: " + queue.size());

        RandomizedQueue<String> queueOfStrings = new RandomizedQueue<>();
        queueOfStrings.enqueue("ABC");
        queueOfStrings.enqueue("HELLO");
        
        int len = queueOfStrings.size();
        for (int i = 0; i < len; i++) {
            StdOut.println("Dequeued a random element: " + queueOfStrings.dequeue());
        }
        try {
            queueOfStrings.dequeue();
        } catch (NoSuchElementException e) {
            StdOut.println("Expected exception whem queue has size = " + queueOfStrings.size());
        }
    }
}