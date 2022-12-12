package basic;

/**
 * public boolean isInterrupted()返回当前线程中断标志位是否为true
 * public void interrupt(),中断对应线程
 * public static boolean interrupted(),返回当前线程线程中断标志位是否为true，会清空标志位
 */
public class InterruptRunnableDemo extends Thread{
    private static int count = 0;
    @Override
    public void run(){
        while (!Thread.currentThread().isInterrupted()){
            System.out.println("循环里面");
            count++;
            if (count >= 5){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("done");
    }

    public static void main(String[] args) {
        Thread t1 = new InterruptRunnableDemo();
        t1.start();
        //t1.interrupt();
    }
}
