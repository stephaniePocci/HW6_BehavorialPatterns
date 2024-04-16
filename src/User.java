import java.util.Iterator;
import java.util.List;

public class User {
    public final String name;
    private final ChatServer chatServer;
    private final ChatHistory chatHistory;

    public User(String name, ChatServer chatServer) {
        this.name = name;
        this.chatServer = chatServer;
        this.chatHistory = new ChatHistory();
    }

    public void sendMessage(List<String> recipients, String content) {
        Message message = new Message(this.name, recipients, content);
        chatServer.sendMessage(message);
        chatHistory.addMessage(message);
    }

    public void receiveMessage(Message message) {
        chatHistory.addMessage(message);
    }

    public void undoLastMessage() {
        Message lastMessage = chatHistory.getLastMessage();
        if (lastMessage != null) {
            chatServer.undoMessage(lastMessage);
            chatHistory.removeLastMessage();
        }
    }

    public void undoMessage(Message message) {
        chatHistory.removeMessage(message);
    }

    public void blockUser(String user) {
        chatServer.blockUser(this.name, user);
    }

    public Iterator<Message> getMessagesByUser(User user) {
        return chatHistory.getMessagesByUser(user);
    }

    public Iterator<Message> iterator() {
        return chatHistory.iterator(this);
    }
}