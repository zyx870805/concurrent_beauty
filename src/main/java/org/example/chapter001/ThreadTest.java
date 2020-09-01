package org.example.chapter001;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }

    public static void main(String[] args) {
//        MyThread myThread = new MyThread();
//        myThread.start();

//        RunnableTask task = new RunnableTask();
//        new Thread(task).start();
//        new Thread(task).start();

        CallerTask task = new CallerTask();
        FutureTask<String> futureTask = new FutureTask<>(task);
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static class RunnableTask implements Runnable {

        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }

    public static class CallerTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "hello";
        }
    }
}
