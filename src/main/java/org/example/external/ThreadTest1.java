package org.example.external;

public class ThreadTest1 {
    public static void main(String[] args) throws InterruptedException {
        NThread thread = new NThread();
        thread.start();

        Thread.sleep(3000);

        System.out.println("cancel执行前"+System.currentTimeMillis());
        thread.cancel();
    }

    static class NThread extends Thread {

        private boolean isCancel;

        @Override
        public void run() {
            while (!isCancel) {
                System.out.println("依然存活");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("while结束"+System.currentTimeMillis());
        }

        public void cancel() {
            this.isCancel = true;
        }
    }

}
