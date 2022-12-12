package juc_collections;

public class CopyOnWriteArrayListDemo {
    /**
     * 主要思想：写时复制
     *
     * 使用和其他List基本一样，但这个支持两个原子方法；
     * 写都需要锁；
     * 内部维护一个数组对象，此数组对象volatile修饰
     * 写入时，new新对象，复制值再写入新值，最后set新的数组引用
     */
}
