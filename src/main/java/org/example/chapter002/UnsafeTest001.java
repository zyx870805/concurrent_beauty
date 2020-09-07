package org.example.chapter002;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicLong;

public class UnsafeTest001 {
    public static void main(String[] args) {
        Unsafe unsafe = Unsafe.getUnsafe();
        try {
            long value = unsafe.objectFieldOffset(AtomicLong.class.getDeclaredField("value"));
            System.out.println(value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
