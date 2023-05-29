package blockchain;

import java.util.Date;

public class Miner extends Thread {

    public void mine(BlockChain blockchain) {
        Block lastBlock = blockchain.getLastBlock();

        int id;
        long timestamp = new Date().getTime();
        String prevHash;

        if (lastBlock == null) {
            id = 1;
            prevHash = "0";
        } else {
            id = lastBlock.getId() + 1;
            prevHash = lastBlock.getHash();
        }

        String zeros = "0".repeat(blockchain.getNumberOfZeros());
        String hash;
        int magicNumber;

        do {
            magicNumber = (int) (Math.random() * 1_000_000_000);
            hash = StringUtil.applySha256(id + timestamp + magicNumber + prevHash);
        } while (!hash.startsWith(zeros));

        long generationTime = (new Date().getTime() - timestamp) / 1000;
        boolean added = blockchain.addBlock(
                new Block(Thread.currentThread().getId(), id, timestamp, magicNumber, prevHash, hash, generationTime));
        if (!added) {
            mine(blockchain);
        }
    }
}
