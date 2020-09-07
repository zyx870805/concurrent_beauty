package org.example.chapter006;

import java.util.concurrent.locks.LockSupport;

public class LockSupport2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child begin park");
                LockSupport.park();
                System.out.println("child end park");
            }
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread over");
        LockSupport.unpark(thread);
    }
}
