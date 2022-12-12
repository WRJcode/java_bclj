package basic;

public class StaticCounter {
    /**
     * 此处，synchronized保护的是类对象，
     * 实际上每个对象都有一个锁和一个等待队列
     */
    private static  int count = 0;
    public  static synchronized void incr(){
        count++;
    }
    public  static synchronized int getCount(){
        return count;
    }
}
