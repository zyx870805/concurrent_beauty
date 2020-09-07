package org.example.chapter001;

public class InterruptTest2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("thread begin sleep");
                    Thread.sleep(10000);
                    System.out.println("thread end sleep");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        Thread.sleep(2000);

        thread.interrupt();
    }
}
