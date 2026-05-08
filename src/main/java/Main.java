import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numClientes = 3;

        try (ExecutorService pool = Executors.newFixedThreadPool(numClientes)) {
            // queue que blocka caso esteja cheia
            BlockingQueue<Pedido> queue = new LinkedBlockingQueue<>(25);

            for (int i = 1; i <= numClientes; i++) {
                pool.submit((new Cozinheiro(queue, i)));
            }

            Thread cliente = new Thread(new Cliente(queue, numClientes));
            cliente.start();
            cliente.join();
        }
        System.out.println("Restaurante fechado!");
    }
}
