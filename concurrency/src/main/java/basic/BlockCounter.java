package basic;

public class BlockCounter {
    private int count;
    public void incr(){
        synchronized (this){
            count++;
        }
    }
    public int getCount(){
        synchronized (this){
            return count;
        }
    }
}
