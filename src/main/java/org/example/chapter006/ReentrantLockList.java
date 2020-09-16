package org.example.chapter006;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockList {

    private ArrayList<String> array = new ArrayList<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public void add(String str) {
        writeLock.lock();
        try {
            array.add(str);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(String str) {
        writeLock.lock();
        try {
            array.remove(str);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(int index) {
        readLock.lock();
        try {
            return array.get(index);
        } finally {
            readLock.unlock();
        }
    }
}
