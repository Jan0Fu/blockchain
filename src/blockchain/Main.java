package blockchain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        initBlockchain();
    }

    public static void initBlockchain() {
        int processors = Runtime.getRuntime().availableProcessors();
        BlockChain blockChain = new BlockChain();
        ExecutorService executorMiner = Executors.newFixedThreadPool(processors);

        for (int i = 0; i < 15; i++) {
            executorMiner.submit(() -> {
                try {
                    long threadId = Thread.currentThread().getId();
                    blockChain.createBlock(threadId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executorMiner.shutdown();
        try {
            executorMiner.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException interruptedException) {
            System.out.println("Thread interrupted");
        }
    }

}