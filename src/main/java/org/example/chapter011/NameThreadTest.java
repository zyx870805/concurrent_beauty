package org.example.chapter011;

public class NameThreadTest {

    private static final String THREAD_SAVE_ORDER = "THREAD_SAVE_ORDER";
    private static final String THREAD_SAVE_ADDR = "THREAD_SAVE_ADDR";

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("保持订单线程");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new NullPointerException();
            }
        }, THREAD_SAVE_ORDER);

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("保存地址线程");
            }
        }, THREAD_SAVE_ADDR);

        threadOne.start();
        threadTwo.start();
    }
}
