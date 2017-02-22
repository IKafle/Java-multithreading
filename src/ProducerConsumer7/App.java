package ProducerConsumer7;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by script on 2/22/17.
 */


public class App {
    /*
    * Producer-Consumer is the situation where one or more threads are producing data items
     * and adding them to a shared data store of some kind
      * while one or more other threads process those items, removing them from the data store.
    * */


    //QUEUE = FIFO.
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {

        Thread thread1 = new Thread(App::producer);
        Thread thread2 = new Thread(App::consumer);

        thread1.start();
        thread2.start();

        try {
            /* join() method : - It can be used to pause the current thread execution until
            unless the specified thread is dead.

                              - Waits for this thread to die
            */

             //let all threads ie. thread1 and thread2 finish execution before finishing main thread
            thread1.join(); //Waits for this thread to die
            thread2.join();  //Waits for this thread to die
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads are dead and main thread is resumed.");
    }

    private static void producer() {

        while (true) {
            Random random = new Random();
            try {
                // produces random num between 0 to 99.
                queue.put(random.nextInt(100));             //If queue is full, then put() will patiently wait until items are removed
                                                            // from the queue and size is less than queue size, then it will execute.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    private static void consumer() {

        while (true) {
            Random random = new Random();
            try {
                Thread.sleep(100);

                if (random.nextInt(10) == 0) {
                    Integer value = queue.take();  // If there is nothing in the queue, then take() will patiently wait
                                                    // until something is added to the queue and then only take() will take it out
                                                    // and it doesn't consume too many resources while it's waiting or anything like that.

                    System.out.println("Taken value:" + value + " Queue size is:" + queue.size());

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }


}
