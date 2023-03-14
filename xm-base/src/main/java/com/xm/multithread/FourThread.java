package com.xm.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个线程打印
 *
 * @author XM
 * @date 2023/3/13
 */
public class FourThread implements Runnable {
    private final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private static volatile int currentCount = 0;
    private int flag;

    public FourThread(int flag) {
        this.flag = flag;
    }

    private int checkFlag(int n) {
        if (n % 15 == 0) {
            return 0;
        } else if (n % 5 == 0) {
            return 1;
        } else if (n % 3 == 0) {
            return 2;
        } else {
            return 3;
        }
    }

    public void print(int flag, int n) {
        if (flag == 1) {
            System.out.print("A");
        } else if (flag == 0) {
            System.out.print("C");
        } else if (flag == 2) {
            System.out.print("B");
        } else if (flag == 3) {
            System.out.print(n);
        }
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (currentCount < array.length && checkFlag(array[currentCount]) % 4 != flag) {
                    condition.await();
                }
                if (currentCount < array.length) {
                    print(flag, array[currentCount]);
                    currentCount++;
                    condition.signalAll();
                } else {
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new FourThread(0)).start();
        new Thread(new FourThread(1)).start();
        new Thread(new FourThread(2)).start();
        new Thread(new FourThread(3)).start();
    }
}