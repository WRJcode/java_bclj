package juc_basic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *  ABA问题
 *  解决： 使用AtomicStampedReference,修改值同时附近一个时间戳，只有值和时间戳都相同才进行修改！
 */
public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        Pair pair = new Pair(100,200);
        int stamp = 1;
        AtomicStampedReference<Pair> pairRef = new AtomicStampedReference<>(pair,stamp);
        int newStamp = 2;
        pairRef.compareAndSet(pair,new Pair(200,200),stamp,newStamp);
    }

}
