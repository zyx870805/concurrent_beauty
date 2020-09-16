package org.example.chapter006;

import java.util.concurrent.locks.StampedLock;

public class Point {

    double x, y;

    private final StampedLock lock = new StampedLock();

    void move(double deltaX, double deltaY) {
        long writeLock = lock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            lock.unlockWrite(writeLock);
        }
    }

    double distanceFromOrigin() {
        long stamp = lock.tryOptimisticRead();
        double currentX = x , currentY = y;
        if(!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    void moveIfAtOrigin(double newX, double newY) {
        long stamp = lock.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = lock.tryConvertToWriteLock(stamp);
                if (ws != 0l) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    lock.unlockRead(stamp);
                    stamp = lock.writeLock();
                }
            }
        }finally {
            lock.unlock(stamp);
        }
    }
}
