package basic;

import java.util.Collections;
import java.util.Map;

/**
 *
 * @param <K>
 * @param <V>
 *
 *  同步容器及其注意事项
 *   Collections中有一些方法可以返回线程安全的同步容器,源码里面是通过给所有容器方法加上synchronized实现安全的；
 *     但是否意味着绝对安全呢？nonono，以下情况要注意：
 *     1.复合情况，如下例子
 *     2.伪同步
 *     3.迭代；如一个线程修改，一个线程遍历，会报错；解决：在迭代遍历时给整个集合加锁
 *     4.同步容器性能较低，访问量比较大时候性能较差。此时可使用专为并发设计的容器，如CopyOnWriteArrayList、ConcurrentHashMap、
 *        ConcurrentLinkedQueue、ConcurrentSkipListSet等
 */
public class EnhanceMap<K,V> {
    Map<K,V> map;
    public EnhanceMap(Map<K,V> map){
        this.map = Collections.synchronizedMap(map);
    }
    public V putIfAbsent(K key,V value){
        V old = map.get(key);
        if (old != null){
            return old;
        }
        return map.put(key,value);
    }

    public V put(K key,V value){
        return map.put(key,value);
    }
}
