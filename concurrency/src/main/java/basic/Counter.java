package basic;

public class Counter {
    /**
     * synchronized特征：
     *   1.可重入性
     *     例如在synchronized实例方法内，可以直接调用相同锁的其他实例方法；
     *     可重入是通过记录锁的持有线程和持有数量来实现的
     *     注意并不是所有锁都可重入；
     *   2.内存可见性
     *       synchronized除了保证原子操作外，重要作用了保证内存可见性；
     *       具体实现内存可见性是，对象在释放锁时，所有写入都会写回内存；
     *       而获取锁后，也都会从内存中读取最新的数据；
     *
     *       竞态条件：当两个线程竞争同一资源时,如果对资源的访问顺序敏感,就称存在竞态条件。
     *
     *       而当问题不存在竞态条件时候，仅仅考虑内存可见性使用synchronized成本会比较高，
     *       这时候可以使用更轻量级的方式——给变量添加volatile修饰符；
     *       java会在操作对应变量时插入特殊指令，保证读写到内存最新值而非缓存
     *   3.死锁
     *     a线程持有锁A,等待锁B；b线程持有锁B，等待锁A，称之为死锁。
     *
     */
    private int count;
    public synchronized void incr(){
        count ++;
    }
    public synchronized int getCount(){
        return count;
    }
}
