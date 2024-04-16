
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChatApp {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();

        User user1 = new User("Stephanie Pocci", chatServer);
        User user2 = new User("Alan Cortez", chatServer);
        User user3 = new User("Jennifer Pocci", chatServer);

        chatServer.registerUser(user1);
        chatServer.registerUser(user2);
        chatServer.registerUser(user3);

        List<String> recipients = new ArrayList<>();
        recipients.add(user2.name);
        recipients.add(user3.name);
        user1.sendMessage(recipients, "Chat, is this real?!");

        user2.blockUser(user1.name);

        user1.sendMessage(recipients, "This message should not be received by User2.");

        user1.undoLastMessage();

        // Iterate over messages by user
        Iterator<Message> user1Messages = user1.getMessagesByUser(user1);
        while (user1Messages.hasNext()) {
            Message message = user1Messages.next();
            System.out.println("Sender: " + message.getSender() + "\nRecipient(s): " + message.getRecipients() + ", Content: " + message.getContent());
        }
    }
}