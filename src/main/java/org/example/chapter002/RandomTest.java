package org.example.chapter002;

import java.util.Random;

public class RandomTest {
    private static Random random = new Random();



    public static void main(String[] args) throws InterruptedException {
//        for(int i = 0; i < 10; i ++) {
//            System.out.println("main thread " + random.nextInt(5));
//        }

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i ++) {
                    System.out.println("thread1 " + random.nextInt(5));
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i ++) {
                    System.out.println("thread2 " + random.nextInt(5));
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
