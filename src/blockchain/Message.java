package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {

    private List<String> messages;

    public Message(){
        messages = new ArrayList<>();
        this.messages.add("no messages");
        this.messages.add("Tom: Hey, I'm first!");
        this.messages.add("Sarah: It's not fair!\n" +
                "Sarah: You always will be first because it is your blockchain!");
        this.messages.add("Sarah: Anyway, thank you for this amazing chat.");
        this.messages.add("Tom: You're welcome :)\n" +
                "Nick: Hey Tom, nice chat");

    }

    public String getMessages(int index) {
        if (index < 5)
            return messages.get(index);
        else {
            Random random = new Random();
            return messages.get(random.nextInt(5));
        }
    }
}
