package org.example.chapter011;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestSimpleDateFormat {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (dateFormat) {
                            dateFormat.parse("2020-09-30 21:15:00");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
