import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numProducers = 5;

        try (ExecutorService pool = Executors.newFixedThreadPool(numProducers)) {
            BlockingQueue<Request> queue = new LinkedBlockingQueue<>(25);

            for (int i = 1; i <= numProducers; i++) {
                pool.submit((new Consumer(queue)));
            }

            Thread producer = new Thread(new Producer(queue, numProducers));
            producer.start();
            producer.join();
        }
        System.out.println("Todas as requests foram processadas!");
    }
}
