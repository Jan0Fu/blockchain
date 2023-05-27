package blockchain;

import java.security.MessageDigest;

public class Block {

    private static int count = 0;
    private final int id;
    private final long timestamp;
    private final String previousHash;
    private final String hash;

    public Block(String previousHash) {
        this.id = ++count;
        this.timestamp = System.currentTimeMillis();
        this.previousHash = previousHash;
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
            "Hash of the previous block:\u0020\n" +
            "%s\n" +
            "Hash of the block:\u0020\n" +
            "%s\n", id, timestamp, previousHash, hash);
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
