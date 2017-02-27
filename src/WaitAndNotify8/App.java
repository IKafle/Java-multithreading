package WaitAndNotify8;

/**
 * Created by script on 2/27/17.
 */
public class App {


    /*
    * A thread that owns the object’s monitor (for instance, a thread that has entered a synchronized
    * section guarded by the object) may call wait() to temporarily release the monitor and
    * give other threads a chance to acquire the monitor. This may be done, for instance, to wait for
     * a certain condition.
    *
    *
     When another thread that acquired the monitor fulfills the condition, it may call object.notify()
     or object.notifyAll() and release the monitor. The notify method awakes a single thread in the waiting
     state, and the notifyAll method awakes all threads that wait for this monitor, and they all compete for
      re-acquiring the lock.
    *
    *
    *
    * */

    public static void main(String[] args) {

        Processor processor = new Processor();
        Thread thread1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Thread thread2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        thread1.start();
        thread2.start();

        try {
            thread1.join(); //waits until the thread is completed or has returned.
            thread2.join();
        } catch (InterruptedException e) {
            e.getMessage();
        }

    }
}
