package org.example.chapter001;

public class WaitNotifyInterrupt {

    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("---begin---");
                    synchronized (obj) {
                        obj.wait();
                    }
                    System.out.println("---end---");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        Thread.sleep(1000);

        System.out.println("---begin interrupt threadA---");
        t.interrupt();
        System.out.println("---end interrupt threadA---");
    }
}
