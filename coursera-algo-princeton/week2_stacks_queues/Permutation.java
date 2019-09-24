import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = args.length > 0 ? Integer.parseInt(args[0]) : 0;
        if (k > 0) {
            doPermutationRandomizedQueue(k);
        }
    }

    /*
    private static void doPermutationDequeue(int k) {
        Deque<String> deque = new Deque<>();

        while (!StdIn.isEmpty()) {
            String line = StdIn.readString();
            int rnd = StdRandom.uniform(2);
            if (deque.size() == k) {
                excludeRandom(deque, rnd);
            }
            if (deque.size() < k) {
                if (rnd == 0) {
                    deque.addFirst(line);
                } else {
                    deque.addLast(line);
                }
            }
        }
        for (String element : deque) {
            StdOut.println(element);
        }
    }

    private static void excludeRandom(Deque<String> deque, int rnd) {
        if (rnd == 0) {
            deque.removeLast();
        } else {
            deque.removeFirst();
        }
    }*/

    private static void doPermutationRandomizedQueue(int k) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String line = StdIn.readString();
            if (queue.size() == k) {
               queue.dequeue();
            }
            if (queue.size() < k) {
                queue.enqueue(line);
            }
        }
        for (String element : queue) {
            StdOut.println(element);
        }
    }
}