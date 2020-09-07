package org.example.chapter001;

public class InterruptTest5 {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;) {

                }
            }
        });

        thread.start();

        thread.interrupt();

        System.out.println("isInterrupt: " + thread.isInterrupted());
        System.out.println("isInterrupt: " + thread.interrupted());
        System.out.println("isInterrupt: " + Thread.interrupted());
        System.out.println("isInterrupt: " + thread.isInterrupted());
    }

}
