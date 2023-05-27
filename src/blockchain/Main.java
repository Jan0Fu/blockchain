package blockchain;

public class Main {
    public static void main(String[] args) {
        BlockChain chain = new BlockChain();
        for (int i = 0; i < 5; i++) {
            chain.addBlock();
        }
        System.out.println(chain);
    }
}
