import java.util.Iterator;
import java.util.List;

class searchMessagesByUser implements Iterator<Message> {
    private final List<Message> messageHistory;
    private final User user;
    private int index;

    public searchMessagesByUser(List<Message> messageHistory, User user) {
        this.messageHistory = messageHistory;
        this.user = user;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        while (index < messageHistory.size()) {
            Message message = messageHistory.get(index);
            if (message.getSender().equals(user.name) || message.getRecipients().contains(user.name)) {
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public Message next() {
        Message message = messageHistory.get(index);
        index++;
        return message;
    }
}