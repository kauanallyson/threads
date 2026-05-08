import java.util.concurrent.BlockingQueue;

public class Cliente implements Runnable {
    private final BlockingQueue<Pedido> queue;
    private final int numDeCozinheiros;

    public Cliente(BlockingQueue<Pedido> queue, int numDeCozinheiros) {
        this.queue = queue;
        this.numDeCozinheiros = numDeCozinheiros;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 100; i++) {
                System.out.println("Cliente fez o pedido: " + i);
                queue.put(new Pedido(i, "pedido " + i));
                Thread.sleep(100);
            }
            for (int i = 0; i < numDeCozinheiros; i++) {
                queue.put(new Pedido(-1, "")); // pedido envenenado
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
