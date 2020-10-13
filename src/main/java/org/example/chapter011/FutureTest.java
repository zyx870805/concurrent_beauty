package org.example.chapter011;

import java.util.concurrent.*;

public class FutureTest {

    private final static ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<?> futureOne = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runnable one");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Future<?> futureTwo = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runnable two");
            }
        });

        Future<?> futureThree = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runnable three");
            }
        });
        System.out.println("task one " + futureOne.get());
        System.out.println("task two " + futureTwo.get());
        System.out.println("task three " + futureThree.get());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(futureThree.isDone());

                }
            }
        });

        t.setDaemon(true);
        t.start();



        executorService.shutdown();

    }
}
