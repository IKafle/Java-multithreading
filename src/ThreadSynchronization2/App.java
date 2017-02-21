package ThreadSynchronization2;

import java.util.Scanner;

/**
 * Created by script on 2/21/17.
 */


class Processor extends Thread {

    /*- One thread doesn't expect to modify its data from another thread.
    * - the thread has its own copy of data (variables,etc) i.e it caches the data.
    * - The thread doesn't bother to check variable modified from another thread as it has its own copy of that data.So,
    * to prevent thread from caching the data, keyword 'volatile' should be used.
    * */

    //Threads have the own copy of this data and is non visible to other threads. Using volatile makes it visible to all the threads.
    public volatile boolean isRunning = true;


    public void run() {

        while (isRunning) {

            System.out.println("hello man");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    public void shutdown() {

        this.isRunning = false;
    }
}

public class App {

    public static void main(String[] args) {

        Processor processor = new Processor();
        processor.start();

        System.out.println("please, press retrun to stop thread");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor.shutdown();
    }
}
