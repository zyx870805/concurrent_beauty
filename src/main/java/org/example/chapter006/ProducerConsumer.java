package org.example.chapter006;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

public class ProducerConsumer {

    private static final Queue<String> queue = new LinkedBlockingQueue<>();

    private static NonReentrantLock lock = new NonReentrantLock();
    private static Condition notFull = lock.newCondition();
    private static Condition notEmpty = lock.newCondition();

    private static final int queueSize = 10;

    public static void main(String[] args) {
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        notEmpty.await();
                    }
                    queue.add("aaaa" + System.currentTimeMillis());
                    notFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while(queue.isEmpty()) {
                        notFull.await();
                    }
                    String poll = queue.poll();
                    System.out.println("consumer consume " + poll);
                    notEmpty.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        consumer.start();
        producer.start();
    }

}
