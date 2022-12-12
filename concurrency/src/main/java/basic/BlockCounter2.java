package basic;

public class BlockCounter2 {
    private int count;
    private Object lock = new Object();
    public void incr(){
        synchronized (lock){
            count++;
        }
    }
    public int getCount(){
        synchronized (lock){
            return count;
        }
    }
}
