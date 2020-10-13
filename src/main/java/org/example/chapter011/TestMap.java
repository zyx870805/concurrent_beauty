package org.example.chapter011;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestMap {
    private static Map<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list1 = new CopyOnWriteArrayList<>();
                list1.add("device1");
                list1.add("device2");
                List<String> oldList = map.putIfAbsent("topic1", list1);
                if (null == oldList) {
                    map.put("topic1", list1);
                } else {
                    oldList.addAll(list1);
                }

            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list1 = new CopyOnWriteArrayList<>();
                list1.add("device11");
                list1.add("device22");
                List<String> oldList = map.putIfAbsent("topic1", list1);
                if (null == oldList) {
                    map.put("topic1", list1);
                } else {
                    oldList.addAll(list1);
                }
            }
        });

        Thread threadThree = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list2 = new CopyOnWriteArrayList<>();
                list2.add("device111");
                list2.add("device222");
                List<String> oldList = map.put("topic2", list2);
                if (null == oldList) {
                    map.put("topic2", list2);
                } else {
                    oldList.addAll(list2);
                }
            }
        });

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        threadOne.join();
        threadTwo.join();
        threadThree.join();
        System.out.println(map);
    }
}
