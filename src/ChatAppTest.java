import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChatAppTest {
    private ChatServer chatServer;
    private User user1, user2, user3;

    @Before
    public void setUp() {
        chatServer = new ChatServer();

        user1 = new User("Stephanie Pocci", chatServer);
        user2 = new User("Alan Cortez", chatServer);
        user3 = new User("Jennifer Pocci", chatServer);

        chatServer.registerUser(user1);
        chatServer.registerUser(user2);
        chatServer.registerUser(user3);
    }

    @Test
    public void testSendMessage() {
        List<String> recipients = new ArrayList<>();
        recipients.add(user2.name);
        recipients.add(user3.name);
        user1.sendMessage(recipients, "Chat, is this real?!");

        Iterator<Message> user2Messages = user2.getMessagesByUser(user1);
        Iterator<Message> user3Messages = user3.getMessagesByUser(user1);

        assertTrue(user2Messages.hasNext());
        assertTrue(user3Messages.hasNext());
        assertEquals("Chat, is this real?!", user2Messages.next().getContent());
        assertEquals("Chat, is this real?!", user3Messages.next().getContent());
        assertFalse(user2Messages.hasNext());
        assertFalse(user3Messages.hasNext());
    }

    @Test
    public void testBlockUser() {
        user2.blockUser(user1.name);
        user1.sendMessage(new ArrayList<>(List.of(user2.name)), "This message should not be received by User2.");

        Iterator<Message> user2Messages = user2.getMessagesByUser(user1);
        assertFalse(user2Messages.hasNext());
    }

    @Test
    public void testUndoLastMessage() {
        List<String> recipients = new ArrayList<>();
        recipients.add(user2.name);
        recipients.add(user3.name);
        user1.sendMessage(recipients, "Chat, is this real?!");
        user1.undoLastMessage();

        Iterator<Message> user2Messages = user2.getMessagesByUser(user1);
        Iterator<Message> user3Messages = user3.getMessagesByUser(user1);

        assertFalse(user2Messages.hasNext());
        assertFalse(user3Messages.hasNext());
    }
}