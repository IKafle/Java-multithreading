package SynchronizedKeyWord;

/**
 * Created by script on 2/21/17.
 */


public class App {

    /*- Every objects in java have Intrinsic/Monitor lock aka mutex.
    *- If you call synchronized method of an object,you have to acquire the intrinsic lock before you can call it.
    * - Only one thread can acquire intrinsic lock at a time.
    *
    * If one thread acquires an intrinsic lock and calls a synchronized method and another thread
    * at the same time tries to call the same method (synchronized method), then the second thread
    * would just have to wait until first
    * thread releases the intrinsic lock by method finishing executing.
    *
    * */

    // this is a shared data between two threads. => synchronized key word.
    private int counter = 0;

    private synchronized void increment() {
        counter++;
    }

    public static void main(String[] args) {

        App app = new App();

        app.doWork();

    }


    private void doWork() {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        thread1.start();
        thread2.start();


        /*
        *
        * thread1.join() // join => pause the execution of that thread upon which it was called
        * ( thread1 was called upon main thread, so main thread will get paused until thread1 completes)
        *
        * thread2.join() // same with thread2
        *
        * */

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" The counter is :" + counter);
    }
}
