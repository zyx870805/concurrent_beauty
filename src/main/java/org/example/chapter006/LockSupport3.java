package org.example.chapter006;

import java.util.concurrent.locks.LockSupport;

public class LockSupport3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread begin park");
                LockSupport.park();
                System.out.println("child thread end park");
            }
        });

        thread.start();

        Thread.sleep(1000);

        System.out.println("main thread begin interrupt thread");

        thread.interrupt();
    }
}
