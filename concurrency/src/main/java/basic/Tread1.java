package basic;

public class Tread1 extends Thread{
    @Override
    public void run(){
        System.out.println("thread name:"+ Thread.currentThread().getName());
        System.out.println("hello,run thread!");

    }

    public static void main(String[] args) {
        Thread thread = new Tread1();
        thread.start();
        System.out.println(Thread.currentThread().getName());
        //run启动，并不会开启新线程，只会在原来线程中启动！
        thread.run();
    }
}
