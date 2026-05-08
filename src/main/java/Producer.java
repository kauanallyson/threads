import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Request> queue;
    private final int consumerAmount;

    public Producer(BlockingQueue<Request> queue, int consumerAmount) {
        this.queue = queue;
        this.consumerAmount = consumerAmount;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " fez o request: " + i);
                queue.put(new Request(i, "request " + i));
                Thread.sleep(100);
            }
            for (int i = 0; i < consumerAmount; i++) {
                queue.put(new Request(-1, "")); // request envenenado para cada um dos consumidores
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
