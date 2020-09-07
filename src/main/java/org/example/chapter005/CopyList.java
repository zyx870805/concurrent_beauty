package org.example.chapter005;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyList {
    private static volatile CopyOnWriteArrayList list = new CopyOnWriteArrayList();

    public static void main(String[] args) throws InterruptedException {
        list.add("i ");
        list.add("am ");
        list.add("zhangyingxuan");
        list.add(" the world");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                list.set(1, "are");
                list.remove(2);
            }
        });

        Iterator iterator = list.iterator();

        thread.start();

        thread.join();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
