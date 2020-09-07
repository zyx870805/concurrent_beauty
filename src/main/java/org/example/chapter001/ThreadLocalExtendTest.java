package org.example.chapter001;

public class ThreadLocalExtendTest {
//    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("aaaa");
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String s = threadLocal.get();
                System.out.println("child: " + s);
            }
        });
        thread.start();

        System.out.println("parent : " + threadLocal.get());

    }
}
