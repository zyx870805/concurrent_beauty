package org.example.chapter007;

import java.util.PrimitiveIterator;
import java.util.PriorityQueue;
import java.util.Random;

public class TestPriorityBlockingQueue {

    static class Task implements Comparable<Task> {
        private int priority;

        private String taskName;

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }


        @Override
        public int compareTo(Task o) {
            if (this.priority >= o.getPriority()) {
                return 1;
            }
            return -1;
        }

        public void doSomeThing() {
            System.out.println(taskName + ":" + priority);
        }
    }

    public static void main(String[] args) {

        PriorityQueue<Task> queue = new PriorityQueue<>();

        Random random = new Random();
        for (int i = 0; i < 10; i ++) {
            Task task = new Task();
            task.setPriority(random.nextInt(10));
            task.setTaskName("taskName"+i);
            queue.offer(task);
        }

        while (!queue.isEmpty()) {
            Task task = queue.poll();
            if (null != task) {
                task.doSomeThing();
            }
        }
    }
}
