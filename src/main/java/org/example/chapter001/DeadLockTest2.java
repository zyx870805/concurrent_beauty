package org.example.chapter001;

public class DeadLockTest2 {

    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadA obtain resourceA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("threadA wait obtain resourceB");
                    synchronized (resourceB) {
                        System.out.println("threadA obtain resourceB");
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB) {
                    System.out.println("threadB obtain resourceB");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("threadB wait obtain resourceA");
                    synchronized (resourceA) {
                        System.out.println("threadB obtain resourceA ");
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
    }

}
