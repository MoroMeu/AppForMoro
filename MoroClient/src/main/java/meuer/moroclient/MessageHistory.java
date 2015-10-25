package meuer.moroclient;

import meuer.morolibs.Message;
import java.util.ArrayList;
import java.util.List;

public class MessageHistory {

    private List<Message> messages = new ArrayList<>();

    public MessageHistory() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Message getMessageAt(int index) {
        return messages.get(index);
    }

    public void addMessage(Message msg) {
        messages.add(msg);
    }
}
