package basic;

public class CounterThread extends Thread{
    /**
     * 期望值是1000000，但为什么总是获取不到期望值，所得值永远小于期望值呢？
     * 原因：
     *   1.内存不可见性，线程都是从缓存读取值，也是写入到内存
     *   2.多个线程可能同时获取到同一个值，造成修改后值永远小于期望值
     *
     * 解决方法：
     *   1.synchronized
     *   2.显式锁
     *   3.原子变量
     *
     *
     *  内存可见性：
     *     计算机系统中，数据除了在内存中，还会被缓存到寄存器以及各级缓存中，在多线程程序中造成
     *     另一个线程看不到看不到；一是修改没有及时同步到内存，二是其他线程没有从内存读
     *
     *  内存可见性解决方法：
     *    1.volatile关键字
     *    2.synchronized关键字或显式同步锁
     */
    private static int counter = 0;
    @Override
    public void run(){
        for (int i = 0;i<1000;i++){
            counter++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        Thread[] threads = new Thread[num];
        for (int i = 0;i<num;i++){
            threads[i] = new CounterThread();
            threads[i].start();
        }
        for (int i = 0;i<num;i++){
            threads[i].join();
        }
        System.out.println(counter);
    }
}
