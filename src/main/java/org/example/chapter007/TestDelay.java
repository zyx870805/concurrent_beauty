package org.example.chapter007;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class TestDelay {
    static class DelayEle implements Delayed {

        private String taskName;

        private final long delayTime;

        private final long expire;

        public DelayEle(long delayTime, String taskName) {
            this.delayTime = delayTime;
            this.taskName = taskName;
            expire = System.currentTimeMillis() + delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "DelayEle{" +
                    "taskName='" + taskName + '\'' +
                    ", delayTime=" + delayTime +
                    ", expire=" + expire +
                    '}';
        }
    }

    public static void main(String[] args) {
        DelayQueue<DelayEle> queue = new DelayQueue<>();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            DelayEle ele = new DelayEle(random.nextInt(500), "taskName" + i);
            queue.offer(ele);
        }

        DelayEle ele = null;
        try {
            for (; ; ) {
                while ((ele = queue.take()) != null) {
                    System.out.println(ele.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
