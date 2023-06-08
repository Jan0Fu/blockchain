package blockchain;

import java.security.MessageDigest;
import java.util.Date;


public class Block {

    private final int id;
    private final long timeStamp;
    private int magicNumber;
    private String previousHash;
    private String data;

    private String hash;
    private static int lastId;
    private final long creationDuration;
    private final long miner;
    private final String prefixState;

    private Message message = new Message();


    public Block(int lengthOfPrefix, long miner) {
        long startTime = System.nanoTime();
        this.timeStamp = new Date().getTime();
        this.hash = mineBlock(lengthOfPrefix);
        this.id = ++lastId;
        this.creationDuration = (System.nanoTime() - startTime) / 1000000;//to milliseconds
        this.miner = miner;
        this.data = message.getMessages(id - 1);
        if (this.creationDuration > 60) {
            prefixState = "N was decreased by 1";
        } else if (this.creationDuration < 10) {
            prefixState = String.format("N was increased to %d", lengthOfPrefix + 1);
        } else {
            prefixState = "N stays the same";
        }
    }

    public String getHash() {
        return hash;
    }


    public long getCreationDuration() {
        return creationDuration;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getData() {
        return id + previousHash + timeStamp  + data + magicNumber;
    }


    @Override
    public String toString() {
        return "Block:" + '\n' +
                "Created by miner" + miner + '\n' +
                "miner" + miner + " gets 100 VC" + '\n' +
                "Id: " + id + '\n' +
                "Timestamp: " + timeStamp + '\n' +
                "Magic number: " + magicNumber + '\n' +
                "Hash of the previous block: " + '\n' +
                previousHash + '\n' +
                "Hash of the block: " + '\n' +
                hash + '\n' +
                "Block data: " +
                this.data + '\n' +
                "Block was generating for " + creationDuration + " seconds" + '\n' +
                prefixState + '\n';
    }

    private String calculateBlockHash() {
        return applySha256(this.getData());
    }

    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String mineBlock(int prefix) {
        if (prefix == 0) return calculateBlockHash();
        hash = "                                                      ";
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString)) {
            magicNumber++;
            hash = calculateBlockHash();
        }
        return hash;
    }
}