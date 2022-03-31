package main.test;

import main.java.BoundedSafeQueue;

import java.util.stream.IntStream;

public class BoundedSafeQueueTest {
    public static void main(String[] args) throws InterruptedException {
        var queue = new BoundedSafeQueue<String>(10);
        IntStream.range(0, 3).forEach(e -> {
            new Thread(() -> {
                try {
                    for(;;) {
                        Thread.sleep(3000);
                        queue.put(Thread.currentThread().getName());
                    }
                } catch (InterruptedException ex) {
                    throw  new AssertionError(ex.getMessage());
                }
            }).start();
        });

        for(;;) {
            System.out.println(queue.take());
        }
    }
}
