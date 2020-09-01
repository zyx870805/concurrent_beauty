package org.example.chapter001;

public class NotifyAllTest {

    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (obj) {
                        System.out.println("threadA get obj lock");
                        System.out.println("threadA begin release lock");
                        obj.wait();
                        System.out.println("threadA end release lock");
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (obj) {
                        System.out.println("threadB get obj lock");
                        System.out.println("threadB begin release lock");
                        obj.wait();
                        System.out.println("threadB end release lock");
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
//                    obj.notify();
                    obj.notifyAll();
                }
            }
        });

        threadA.start();
        threadB.start();

        Thread.sleep(1000);

        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main over");
    }
}
