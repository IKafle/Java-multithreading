package CountdownLatch6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by script on 2/22/17.
 */


class Processor implements Runnable {

    // Countdown latch is thread safe, no need to use synchronized keyword.
    private CountDownLatch latch;

    Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("started :");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //call countdown() after each thread finishes.
        //Decrement countdown latch counter by one.
        latch.countDown();
    }


}

public class App {
    public static void main(String[] args) {

        /*-using a CountDownLatch: we can cause a thread to block until other threads have completed a given task.
        *
        *- a CountDownLatch has a counter field, which you can decrement as required.
        * We can then use it to block a calling thread until it’s been counted down to zero.
        *
        * -instantiate the CountDownLatch with the same value for the counter as a number of threads we want to work across.
         * Then, we could just call countdown() after each thread finishes, guaranteeing that a dependent thread calling await()
          * will block until the worker threads are finished.
          *
        * */


        CountDownLatch latch = new CountDownLatch(3); //counter = 3

        ExecutorService service = Executors.newFixedThreadPool(3); // number of threads we want to work across = 3

        for (int i = 0; i < 3; i++) {
            //assign task to service.
            service.execute(new Processor(latch));
        }

        try {

            //It awaits until the countdown latch has counted down to zero.
            //dependent thread calling await() = main thread,
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("completed:");
    }
}
