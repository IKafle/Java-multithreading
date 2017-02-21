package MultipleLocks4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by script on 2/21/17.
 */
class Worker {

    private Random random = new Random();

    /*Good practice to declare separate dedicated lock objects.As one java object = one monitor lock.
    * lock1 and lock2 are two different java objects and hence are two different intrinsic locks.
    *
    * */
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    //Never declare shared resources as lock objects. Results will be uncertain and confusing.
    //and you will end up complicating things.
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();


    //since two synchronized block inside methods stageOne() and stageTwo() have two different lock objects,
    // two  different threads can simultaneously execute these two different methods with different lock objects inside synchronized block

    private void stageOne() {

        // No two threads can execute the same synchronized block at the same time.
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list1.add(random.nextInt(100));
        }

    }

    private void stageTwo() {

        // if one thread has already entered synchronized block,
        // another thread will have to wait until the first thread leaves the monitor lock by finishing the execution.
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));

        }

    }

    void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }

    }
}


class Main {

    public static void main(String[] args) {
        Worker worker = new Worker();

        System.out.println(" two parallel threads starting ...");

        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(worker::process); // method referencing.
        Thread thread2 = new Thread(worker::process); // method referencing.

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken:" + (end - start));

        System.out.println("List1: " + worker.list1.size() + ", list2: " + worker.list2.size());

    }

}