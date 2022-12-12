package juc_basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter extends Thread {
    private final Lock lock = new ReentrantLock();
    private static volatile int count = 0;
    public void incr(){
        lock.lock();
        try{
            count++;
        } finally {
            lock.unlock();
        }
    }
    public static int getCount(){
        return count;
    }

    @Override
    public void run(){
        this.incr();
    }

    public static void main(String[] args) throws InterruptedException {

        int num = 1000;
        Thread[] threads = new Counter[num];
        for (int i=0;i<num;i++){
            threads[i] = new Counter();
            threads[i].start();
        }
        for (int i=0;i<num;i++){
            threads[i].join();
        }
        System.out.println(Counter.getCount());

    }
}
