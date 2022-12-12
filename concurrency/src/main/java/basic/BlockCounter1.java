package basic;

public class BlockCounter1 {
    private static int count = 0;
    public static void incr(){
        synchronized (BlockCounter1.class){
            count++;
        }
    }
    public static int getCount(){
        synchronized (BlockCounter1.class){
            return count;
        }
    }
}
