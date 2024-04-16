import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer {
    private final Map<String, User> users;
    private final Map<String, List<String>> blockedUsers;

    public ChatServer() {
        this.users = new HashMap<>();
        this.blockedUsers = new HashMap<>();
    }

    public void registerUser(User user) {
        users.put(user.name, user);
    }

    public void unregisterUser(User user) {
        users.remove(user.name);
    }

    public void sendMessage(Message message) {
        for (String recipient : message.getRecipients()) {
            if (!isBlocked(message.getSender(), recipient)) {
                users.get(recipient).receiveMessage(message);
            }
        }
    }

    public void undoMessage(Message message) {
        for (String recipient : message.getRecipients()) {
            if (!isBlocked(message.getSender(), recipient)) {
                users.get(recipient).undoMessage(message);
            }
        }
    }

    public void blockUser(String blocker, String blocked) {
        if (!blockedUsers.containsKey(blocker)) {
            blockedUsers.put(blocker, new ArrayList<>());
        }
        blockedUsers.get(blocker).add(blocked);
    }

    private boolean isBlocked(String sender, String recipient) {
        return blockedUsers.containsKey(recipient) && blockedUsers.get(recipient).contains(sender);
    }
}