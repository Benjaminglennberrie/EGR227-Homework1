/* 1. Write a method interleave that accepts a queue of integers as a parameter
 * and rearranges the elements by alternating the elements from the first half
 * of the queue with those from the second half of the queue. Your method
 * should throw an IllegalArgumentException if the queue does not have even
 * size. You may use one stack as auxiliary storage to solve this problem.
 * Your solution must run in O(n) time, where n represents the size of the
 * queue.
 */





public static void interleavemethod(Queue<Integer> x) {
        if (x.size() % 2 == 1) {
        throw new IllegalArgumentException();
        }

        Stack<Integer> s = new Stack<Integer>();
        int size = x.size();

        for (int i = 0; i < size / 2; i++) {
        s.push(x.remove());
        }

        while (!s.isEmpty()) {
        x.add(s.pop());
        }

        for (int i = 0; i < size; i++) {
        if (i < size / 2) {
        x.add(x.remove());
        }
        else {
            s.add(x.remove());
        }
        }

        while (!s.isEmpty()) {
        x.add(s.pop());
        x.add(x.remove());
        }

}