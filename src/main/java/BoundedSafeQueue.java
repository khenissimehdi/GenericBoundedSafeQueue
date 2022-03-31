package main.java;

import java.util.ArrayList;

public class BoundedSafeQueue<T> {
    private final ArrayList<T> queue = new ArrayList<>();
    private final int size;

    public BoundedSafeQueue(int size) {
        this.size = size;
    }

    public void put(T value) throws InterruptedException {
        synchronized (queue) {
            while(queue.size() == size) {
                queue.wait();
            }
            queue.add(value);
            queue.notifyAll();
        }
    }

    public T take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }
            queue.notifyAll();
            return queue.remove(0);

        }
    }
}
