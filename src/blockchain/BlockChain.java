package blockchain;

import java.util.LinkedList;

public class BlockChain {
    private volatile int numberOfZeros;
    private final LinkedList<Block> blocks = new LinkedList<>();

    public int getNumberOfZeros() {
        return numberOfZeros;
    }

    public Block getLastBlock() {
        return blocks.isEmpty() ? null : blocks.getLast();
    }

    public synchronized boolean addBlock(Block block) {
        String prevHash = block.getPrevHash();
        String hash = block.getHash();

        boolean isValid = hash.startsWith("0".repeat(numberOfZeros))
                && prevHash.equals(blocks.isEmpty() ? "0" : blocks.getLast().getHash())
                && StringUtil.applySha256(
                block.getId() + block.getTimestamp() + block.getMagicNumber() + prevHash).equals(hash);

        if (isValid) {
            long generationTime = block.getGenerationTime();
            if (generationTime < 10_000) {
                numberOfZeros++;
                block.setZerosChanges(numberOfZeros);
            } else if (generationTime > 60_000) {
                numberOfZeros--;
                block.setZerosChanges(- numberOfZeros);
            }
            blocks.add(block);
        }
        return isValid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        blocks.forEach(b -> sb.append(b.toString()).append("\n"));
        return sb.toString();
    }
}
