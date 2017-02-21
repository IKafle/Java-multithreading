package StartingThreads2;

/**
 * Created by script on 2/21/17.
 */
public class Runner implements Runnable {
    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

            System.out.println(" i am an index: " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class App {


    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runner());
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                System.out.println(" lambda expression index: " + i);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        });

        thread1.start();
        thread2.start();

    }

}
