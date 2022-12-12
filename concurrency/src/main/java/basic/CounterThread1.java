package basic;

public class CounterThread1 extends Thread {
    /**
     * 使用synchronized关键字改进了CounterThread
     * 每一次输出都是期望值1000000
     *
     * 在Counter对象中，synchronized实例方法保护了同一个对象的方法调用，确保同时只能有一个线程执行；
     * 具体说，synchronized实例方法保护当前实例对象，也就是this，this对象有一个锁和等待队列，锁只能被一个线程持有，
     * 而其他试图获得同样的线程需要等待
     *
     * 注意：
     *   一般在保护变量时，需要在所有访问该变量的方法加上synchronized
     */
    Counter counter;
    public CounterThread1(Counter counter){
        this.counter = counter;
    }
    @Override
    public void run(){
        for (int i=0;i<1000;i++){
            counter.incr();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        Counter counter = new Counter();
        Thread[] threads = new Thread[num];
        for (int i=0;i<1000;i++){
            threads[i] = new CounterThread1(counter);
            threads[i].start();
        }
        for (int i=0;i<1000;i++){
            threads[i].join();
        }
        System.out.println(counter.getCount());
    }

}
