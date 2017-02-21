import StartingThreads.Runner;

public class Main {

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
