package org.example.chapter001;

import java.util.*;

public class ProducerConsumerTest {

    private static Queue<String> queue = new ArrayDeque();

    public static void main(String[] args) {

        //生产者
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (queue) {
                        if (queue.size() == 10) {
                            try {
                                queue.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        String s = "str" + new Random().nextInt();
                        queue.add(s);
                        System.out.println("produce " + s);
                        queue.notifyAll();

                    }
                }
            }
        }).start();

        //消费者
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    synchronized (queue) {
                        if (queue.size() == 0) {
                            try {
                                queue.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        String str = queue.poll();
                        System.out.println("consumer consume " + str);
                        queue.notifyAll();
                    }
                }
            }
        }).start();

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
