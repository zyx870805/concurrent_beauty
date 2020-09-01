package org.example.chapter001;

public class InterruptTest {

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA begin run!");
                for(;;) {

                }
            }
        });

        final Thread mainThread = Thread.currentThread();

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mainThread.interrupt();
            }
        });

        threadA.start();
        threadB.start();


        try {
            threadA.join();
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("main over");
    }
}
