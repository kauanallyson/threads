import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Request> queue;
    private final int numDeCozinheiros;

    public Producer(BlockingQueue<Request> queue, int numDeCozinheiros) {
        this.queue = queue;
        this.numDeCozinheiros = numDeCozinheiros;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " fez o request: " + i);
                queue.put(new Request(i, "request " + i));
                Thread.sleep(100);
            }
            for (int i = 0; i < numDeCozinheiros; i++) {
                queue.put(new Request(-1, "")); // request envenenado para cada um dos cozinheiros
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
