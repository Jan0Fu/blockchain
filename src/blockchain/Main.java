package blockchain;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final List<String> names = List.of("George", "Caroline", "Samantha", "John", "Tiffany");

    public static void main(String[] args) throws InterruptedException {

        BlockChain blockchain = new BlockChain();
        int minerCount = Math.min(Runtime.getRuntime().availableProcessors(), 5);
        if (minerCount < 2) minerCount = 2;
        int personCount = names.size();
        ExecutorService executor = Executors.newFixedThreadPool(minerCount + personCount);

        for (int i = 0; i < minerCount; i++) {
            executor.submit(new MessageSender(blockchain, new Person(names.get(i))));
        }

        for (int i = 0; i < minerCount; i++) {
            executor.submit(new Miner(blockchain, i));
        }

        while (blockchain.isAcceptingNewBlocks())
            TimeUnit.SECONDS.sleep(2);

        executor.shutdown();

        if (!blockchain.isValid())
            System.out.println("Invalid blockchain!");
    }
}
