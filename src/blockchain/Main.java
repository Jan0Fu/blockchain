package blockchain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        BlockChain blockchain = new BlockChain();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> new Miner().mine(blockchain));
        }
        executor.shutdown();
        executor.awaitTermination(10L, TimeUnit.SECONDS);
        System.out.println(blockchain.toString());
    }
}
