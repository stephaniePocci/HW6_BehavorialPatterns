import java.util.Iterator;

public interface IterableByUser {
    Iterator<Message> iterator(User user);
}