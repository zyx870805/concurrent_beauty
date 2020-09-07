package org.example.chapter001;

public class InterruptTest4 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("thread begin wait ");
                    Thread.sleep(100000);
                    System.out.println("thread awake");
                } catch (Exception e) {
                    System.out.println("thread is interrupt");
                    return;
                }
                System.out.println("thread begin run again");
            }
        });

        thread.start();

        Thread.sleep(1000);

        thread.interrupt();

        thread.join();

        System.out.println("main thread is over");
    }
}
