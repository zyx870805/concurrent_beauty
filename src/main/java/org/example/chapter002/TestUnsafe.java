package org.example.chapter002;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestUnsafe {
    static Unsafe unsafe;// = Unsafe.getUnsafe();

    private static long stateOffset;

    private volatile long state = 0;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            stateOffset = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestUnsafe testUnsafe = new TestUnsafe();
        boolean b = unsafe.compareAndSwapInt(testUnsafe, stateOffset, 0, 1);
        System.out.println(b);
    }
}
