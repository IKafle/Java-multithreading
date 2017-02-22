package ThreadPool5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by script on 2/22/17.
 */

class Processor implements Runnable {

    private int id;

    Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed: " + id);
    }
}


public class App {

    public static void main(String[] args) {

        /*ExecutorService automatically provides a pool of threads and API for assigning tasks to it.
        *
        *ExecutorService can execute Runnable and Callable tasks.
        *
        * Task is assigned to Executor service.
        *
        * ExecutorService use-case: Transactions or requests according to the scheme “one thread for one task.”
        * */

        ExecutorService executor = Executors.newFixedThreadPool(2); //create a thread-pool with 2 threads:

        for (int i = 0; i < 5; i++) {

            //assign tasks to service.
            executor.submit(new Processor(i));
        }

        // shut down threads as soon as the tasks are completed.
        executor.shutdown();

        System.out.println("All tasks are submitted");


        /*
        * the ExecutorService will not be automatically destroyed when there is not task to process.
        * It will stay alive and wait for new work to do.
        *
        * To properly shut down an ExecutorService, we have the shutdown() and shutdownNow() APIs.
        *
        *shutdown() method : It doesn’t cause an immediate destruction of the ExecutorService.
        *                    It will make the ExecutorService stop accepting new tasks and shut
        *                    down after all running threads finish their current work.
        *
        *
        *shutdownNow() method : It tries to destroy the ExecutorService immediately, but it doesn’t guarantee
         *                       that all the running threads will be stopped at the same time.
         *                       This method returns a list of tasks which are waiting to be processed.
         *                       It is up to the developer to decide what to do with these tasks.
        *
        *
        * awaitTermination() method : With this approach, the ExecutorService will first stop taking new tasks,
        *                             then wait up to a specified period of time for all tasks to be completed.
        *                             If that time expires, the execution is stopped immediately.
        *
        * */
        try {
            if (!executor.awaitTermination(1, TimeUnit.DAYS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("All tasks are completed!");
    }
}
