package org.example.chapter011;

import javax.management.relation.RoleInfo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestShutdown {
    static void asynExecuteOne() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("one pool");
            }
        });
        executorService.shutdown();
    }

    static void asynExecuteTwo() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("two pool");
            }
        });
        executorService.shutdown();
    }

    public static void main(String[] args) {
        System.out.println("--- asyn execute ---");
        asynExecuteOne();
        asynExecuteTwo();
        System.out.println("--- asyn execute over ---");
    }
}
