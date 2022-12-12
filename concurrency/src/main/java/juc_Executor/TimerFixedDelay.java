package juc_Executor;

import java.util.Timer;
import java.util.TimerTask;

public class TimerFixedDelay {
    static class LongRunningTask extends TimerTask {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            System.out.println("long running finished");
        }
    }
    static class FixedDelayTask extends TimerTask {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new LongRunningTask(), 10);
        timer.schedule(new FixedDelayTask(), 100, 1000);
    }
}
