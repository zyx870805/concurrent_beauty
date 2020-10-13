package org.example.chapter011;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestSimpleDateFormat2 {
    static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(dateFormatThreadLocal.get().parse("2020-09-30 21:25:00"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } finally {
                        dateFormatThreadLocal.remove();
                    }
                }
            }).start();
        }
    }
}
