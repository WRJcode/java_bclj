package basic;

/**
 * wait和notify方法都只能在synchronized代码块中被调用，都必须持有锁；
 * wait调用时，会释放对象锁
 * wait和notify共享同一个条件变量和条件等待队列
 */
public class WaitThread extends Thread{
    private volatile boolean fire = false;

    @Override
    public void run(){
        try{
            synchronized (this){
                while (!fire){
                    wait();
                }
            }
            System.out.println("fired");
        }catch (InterruptedException e){

        }
    }
    public synchronized void fire(){
        this.fire = true;
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        Thread.sleep(1000);
        System.out.println("fire");
        waitThread.fire();
    }
}
