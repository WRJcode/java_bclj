package juc_basic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子变量中CAS,是冲突检测，假定冲突比较少，这种更新逻辑是乐观且非阻塞式的；
 * 而synchronized是悲观阻塞式的，得不到锁会进入等待队列等待其他线程唤醒，有上下文开销。
 *
 *   对于复杂的数据结构和算法，非阻塞式往往难以理解，幸好juc中提供了一些非阻塞容器，如
 *  1.ConcurrentLinkedQueue和ConcurrentLinkedDeque：非阻塞并发队列
 *  2.ConcurrentSkipListMap和ConcurrentSkipListSet：非阻塞并发Map和Set
 */
public class AtomicIntegerDemo {
    private static AtomicInteger counter = new AtomicInteger(0);
    static class Vistor extends Thread{
        @Override
        public void run(){
            for (int i=0;i<1000;i++){
                counter.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        Thread[] threads = new Thread[num];
        for (int i=0;i<num;i++){
            threads[i] = new Vistor();
            threads[i].start();
        }
        for (int i=0;i<num;i++){
            threads[i].join();
        }
        System.out.println(counter.get());
    }
}
