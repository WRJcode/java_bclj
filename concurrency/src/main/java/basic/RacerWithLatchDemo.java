package basic;

public class RacerWithLatchDemo {
    private int count;
    public RacerWithLatchDemo(int count){
        this.count = count;
    }
    public synchronized void await() throws InterruptedException {
        while (count > 0){
            wait();
        }
    }
    public synchronized void countDown(){
        count--;
        if (count<=0){
            notifyAll();
        }
    }
    static class Racer extends Thread{
        RacerWithLatchDemo latch;
        public Racer(RacerWithLatchDemo latch){
            this.latch = latch;
        }
        @Override
        public void run(){
            try{
                this.latch.await();
                System.out.println("start run" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 10;
        RacerWithLatchDemo latch = new RacerWithLatchDemo(1);
        Thread[] racers = new Racer[num];
        for (int i=0;i<num;i++){
            racers[i] = new Racer(latch);
            racers[i].start();
        }
        Thread.sleep(1000);
        latch.countDown();
    }
}
