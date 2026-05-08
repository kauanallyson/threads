import java.util.concurrent.BlockingQueue;

public class Cozinheiro implements Runnable {
    private final BlockingQueue<Pedido> queue;
    private final int id;

    public Cozinheiro(BlockingQueue<Pedido> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Pedido pedido = queue.take();
                if (pedido.id() == -1) break;
                System.out.println("Cozinheiro " + id + " preparou o pedido: " + pedido.descricao());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
