package org.example.chapter006;

import java.util.concurrent.locks.LockSupport;

public class LockSupport1 {
    public static void main(String[] args) {
        System.out.println("begin park!");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.println("end park");
    }
}
