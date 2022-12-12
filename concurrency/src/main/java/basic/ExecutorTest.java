package basic;

import java.util.concurrent.Callable;

public class ExecutorTest {

    public interface MyFuture<V>{
        V get() throws Exception;
    }

    static class ExecuteThread<V> extends Thread{
        private V result = null;
        private Exception exception = null;
        private boolean done = false;
        private Callable<V> task;
        private Object lock;

        public ExecuteThread(){

        };

        public ExecuteThread(Callable<V> task,Object lock){
            this.task = task;
            this.lock = lock;
        }
        @Override
        public void run(){
            try{
                result = task.call();
            } catch (Exception e){
                exception = e;
            } finally {
                synchronized (lock){
                    done = true;
                    lock.notifyAll();
                }
            }
        }
        public V getResult(){
            return result;
        }
        public boolean isDone(){
            return done;
        }
        public Exception getException(){
            return exception;
        }

        public <V> MyFuture<V> execute(final Callable<V> task){
            final Object lock = new Object();
            final ExecuteThread<V> thread = new ExecuteThread<>(task,lock);
            thread.start();
            MyFuture future = new MyFuture() {
                @Override
                public Object get() throws Exception {
                    synchronized (lock){
                        while (!thread.isDone()){
                            try{
                                lock.wait();
                            }catch (InterruptedException e){

                            }
                        }
                        if (thread.getException() !=null){
                            throw thread.getException();
                        }
                        return thread.getResult();
                    }
                }
            };
            return future;
        }

    }



    public static void main(String[] args) {
        ExecuteThread executeThread = new ExecuteThread();
        Callable<Integer> subTask = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int mills = (int) (Math.random() * 1000);
                Thread.sleep(mills);
                return mills;
            }
        };
        MyFuture<Integer> future = executeThread.execute(subTask);
        try{
            Integer result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
