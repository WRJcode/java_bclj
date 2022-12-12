package basic;

public class AssemblePointDemo {
    static class Tourist extends Thread{
        AssemblePoint ap;
        public Tourist(AssemblePoint ap){
            this.ap = ap;
        }
        @Override
        public void run(){
            try{
                Thread.sleep((int)(Math.random() * 1000));
                ap.await();
                System.out.println("arrived:"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int num = 10;
        Tourist[] threads = new Tourist[num];
        AssemblePoint ap = new AssemblePoint(num);
        for (int i=0;i<num;i++){
            threads[i] = new Tourist(ap);
            threads[i].start();
        }

    }
}
