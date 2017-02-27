package WaitAndNotify8;

import java.util.Scanner;

/**
 * Created by script on 2/27/17.
 */
public class Processor {


    //First produce() is called,and when control reaches wait() method then
    //wait() method pause the thread and hands over the lock to
    // another thread ( in our case, another thread = consumer() as consumer() is invoked
    // from a thread from inside static main method )
    public void produce() throws InterruptedException {

        //this method can't run until it has acquired the lock of processor object.
        synchronized (this) {
            System.out.println("Producer thread running ...");
            //Every object in java has wait() method because it is the method of an Object class.
            //wait()  can only be called inside synchronized code block.
            wait();
            System.out.println("Resumed ...");
        }
    }


    //As soon as consumer thread receives the same lock from producer thread's wait() method
    // consumer synchronized block gets executed and then notify() method gets called.
    // notify() => as soon as synchronized block gets completed executing inside which notify() is defined,
    // the lock is passed back to thread which is
    // waiting on the same lock (in our case, producer thread)
    //producer thread is waiting on same lock = this = Processor object
    public void consume() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        //same intrinsic lock for both producer and consumer methods.
        //intrinsic lock = this = Processor object.
        synchronized (this) {
            System.out.println("Waiting for return key ...");
            scanner.nextLine();
            System.out.println("Return key pressed ...");

            ///notify()  can only be called inside synchronized code block.

            //notify() wakes up thread with wait() method on it.(That thread which has passed intrinsic lock to it,in our case Producer thread.)
            //notify() doesn't pass lock like wait() until synchronized block gets completed executing.
            notify();

            Thread.sleep(5000);
            //notifyAll() => notifies all the threads that are waiting on the same lock.
            // Lock in this case = this = Processor object
        }

    }
}
