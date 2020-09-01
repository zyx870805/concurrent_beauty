package org.example.chapter001;

public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadA over");
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadB over");
            }
        });

        threadA.start();
        threadB.start();

        System.out.println("wait all thread over");
        threadA.join();
        threadB.join();
        System.out.println("all thread over");
    }
}
