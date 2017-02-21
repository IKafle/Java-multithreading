package StartingThreads;

/**
 * Created by script on 2/21/17.
 */
public class Runner extends Thread {

    public void run() {

        for (int i = 0; i < 10; i++) {

            System.out.println(" i am index: " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
