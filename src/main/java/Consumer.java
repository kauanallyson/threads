import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Request> queue;

    public Consumer(BlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Request request = queue.take();
                if (request.id() == -1) break;
                System.out.println(Thread.currentThread().getName() + " recebeu o request: " + request.descricao());
                Thread.sleep(1000); // simula tempo de processamento
                System.out.println(Thread.currentThread().getName() + " processou o request: " + request.descricao());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
