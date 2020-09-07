package org.example.chapter001;

public class InterruptTest6 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {

                }
                System.out.println("thread isInterrupted: " + Thread.currentThread().isInterrupted());
            }
        });

        thread.start();

        thread.interrupt();

        thread.join();

        System.out.println("main over");
    }

}
