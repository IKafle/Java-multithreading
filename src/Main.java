import StartingThreads.Runner;

public class Main {


    /*
            * In Java, threads are mapped to system-level threads which are operating system’s resources.
            * If you create threads uncontrollably, you may run out of these resources quickly.

            The context switching between threads is done by the operating system
            as well – in order to emulate parallelism.
            A simplistic view is that – the more threads you spawn, the less time each thread spends doing actual work.

            The Thread Pool pattern helps to save resources in a multithreaded application,
            and also to contain the parallelism in certain predefined limits.

            When you use a thread pool, you write your concurrent code in the form of parallel tasks
            and submit them for execution to an instance of a thread pool.
            This instance controls several re-used threads for executing these tasks.
    *
    *
    *
    *  java.util.concurrent package:  any API belonging to this package are thread safe.
    *
    *
    *
    * */

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Two ways to  run threads, thread class and runnable interface.
        Runner runner1 = new Runner();
        runner1.start(); /* can't call run () on thread class,
         because it makes the thread run on main thread , instead start() should be called.*/

        Runner runner2 = new Runner();
        runner2.start();
    }
}
