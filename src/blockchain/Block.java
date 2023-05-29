package blockchain;

public class Block {

    private final long   minerId;
    private final int    id;
    private final long   timestamp;
    private final int    magicNumber;
    private final String prevHash;
    private final String hash;
    private final long   generationTime;
    private int          zerosChanges;

    public Block(long minerId, int id, long timestamp, int magicNumber,
                 String prevHash, String hash, long generationTime) {
        this.minerId = minerId;
        this.id = id;
        this.timestamp = timestamp;
        this.magicNumber = magicNumber;
        this.prevHash = prevHash;
        this.hash = hash;
        this.generationTime = generationTime;
    }

    public int getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getHash() {
        return hash;
    }

    public long getGenerationTime() {
        return generationTime;
    }

    public void setZerosChanges(int zerosChanges) {
        this.zerosChanges = zerosChanges;
    }

    @Override
    public String toString() {
        return "Block:" +
                "\nCreated by miner # " + minerId +
                "\nId: " + id +
                "\nTimestamp: " + timestamp +
                "\nMagic number: " + magicNumber +
                "\nHash of the previous block:\n" + prevHash +
                "\nHash of the block:\n" + hash +
                "\nBlock was generating for " + generationTime + " seconds\n" +
                (zerosChanges > 0 ? "N was increased to " + zerosChanges + "\n"
                        : zerosChanges < 0 ? "N was decreased by 1\n"
                        : "N stays the same\n");
    }
}
