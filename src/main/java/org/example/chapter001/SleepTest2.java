package org.example.chapter001;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepTest2 {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("ThreadA begin sleep");
                    Thread.sleep(10000);
//                    Thread.yield();
                    System.out.println("ThreadA begin awake");
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("ThreadB begin sleep");
                    Thread.sleep(10000);
//                    Thread.yield();
                    System.out.println("ThreadB begin awake");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
            }
        });

        threadA.start();
        threadB.start();


    }
}
