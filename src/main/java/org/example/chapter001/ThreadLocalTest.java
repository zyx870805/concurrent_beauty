package org.example.chapter001;

public class ThreadLocalTest {
    static ThreadLocal<String> localVar = new ThreadLocal<>();

    static void print(String str) {
        System.out.println(str + localVar.get());
        localVar.remove();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                localVar.set("t1 var");
                print("t1");
                System.out.println("t1 remove after:" + localVar.get());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                localVar.set("v2 var");
                print("t2");
                System.out.println("t2 remove after: " + localVar.get());
            }
        });

        t1.start();
        t2.start();
    }
}
