package blockchain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BlockChain {

    private List<Block> blockChain;

    public BlockChain() {
        blockChain = new LinkedList<>();
    }

    public void addBlock() {
        this.blockChain.add(new Block(blockChain.size() == 0 ? "0" : blockChain.get(blockChain.size() - 1).getHash()));
    }

    @Override
    public String toString() {
        return blockChain.stream()
                .map(Block::toString)
                .collect(Collectors.joining("\n"));
    }

    public boolean validateChain() {
        String previous = "0";
        for (Block b: blockChain) {
            if (!b.getPreviousHash().equals(previous)) return false;
            previous = b.getHash();
        }
        return true;
    }
}
