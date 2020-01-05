package net.lgdt.csedu.ds.playground.std;

import java.util.Objects;
import java.util.PriorityQueue;

public class PQUsage {

    public static void main(String[] args) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(0, 2));
        pq.offer(new Point(3, 7));
        pq.offer(new Point(0, 1));

        Point min = pq.poll();

        System.out.println(min + ", PQ size = " + pq.size());
    }

    private static class Point implements Comparable<Point> {
        private final int x;
        private final int y;
        private final double d;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.d = Math.sqrt(x*x + y*y);
        }

        @Override
        public int compareTo(Point point) {
            return Double.compare(d, point.d);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", d=" + d +
                    '}';
        }
    }
}
