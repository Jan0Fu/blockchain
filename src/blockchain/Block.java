package blockchain;

import java.security.MessageDigest;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Block {

    private static int count = 0;
    private final int id;
    private final long timestamp;
    private final String previousHash;
    private int magicNumber;
    private final String hash;
    private int workTime;

    public Block(String previousHash, int zeros) {
        this.id = ++count;
        this.timestamp = System.currentTimeMillis();
        this.previousHash = previousHash;
        proveBlock(zeros);
        this.hash = applySha256(this.toString());
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return String.format("Block:\n" +
            "Id: %d\n" +
            "Timestamp: %d\n" +
            "Magic number: %d\n" +
            "Hash of the previous block:\u0020\n" +
            "%s\n" +
            "Hash of the block:\u0020\n" +
            "%s\n" +
            "Block was generating for %d seconds\n", id, timestamp, magicNumber, previousHash, hash, workTime);
    }

    private void proveBlock(int zeros) {
        StringBuilder zerosPrefix = new StringBuilder();
        for (int i = 0; i < zeros; i++) {
            zerosPrefix.append("0");
        }
        Random random = new Random();
        var startTime = Instant.now();
        while (true) {
            this.magicNumber = random.nextInt();
            if (applySha256(this.toString()).startsWith(zerosPrefix.toString())) {
                break;
            }
        }
        this.workTime = Math.toIntExact(Duration.between(startTime, Instant.now()).toSeconds());
    }

    private String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
