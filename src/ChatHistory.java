

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ChatHistory implements IterableByUser {
    private final List<Message> messageHistory;

    public ChatHistory() {
        this.messageHistory = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messageHistory.add(message);
    }

    public void removeLastMessage() {
        if (!messageHistory.isEmpty()) {
            messageHistory.remove(messageHistory.size() - 1);
        }
    }

    public void removeMessage(Message message) {
        messageHistory.remove(message);
    }

    public Message getLastMessage() {
        if (!messageHistory.isEmpty()) {
            return messageHistory.get(messageHistory.size() - 1);
        }
        return null;
    }

    public Iterator<Message> getMessagesByUser(User user) {
        return new searchMessagesByUser(messageHistory, user);
    }

    @Override
    public Iterator<Message> iterator(User user) {
        return new searchMessagesByUser(messageHistory, user);
    }
}