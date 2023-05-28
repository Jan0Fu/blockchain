package blockchain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BlockChain chain = new BlockChain();

        System.out.print("Enter how many zeros the hash must start with: ");
        int zeros = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < 5; i++) {
            chain.addBlock(zeros);
        }
        System.out.println(chain);
    }
}
