package basic;

public class HelloRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("hello Runnable!");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread helloRunnable = new Thread(new HelloRunnable());
        //未启动，false
        System.out.println(helloRunnable.isAlive());
        helloRunnable.start();
        //启动后,true
        System.out.println(helloRunnable.isAlive());

        //join,让子线程结束后主线程再结束
        helloRunnable.join();
    }
}
