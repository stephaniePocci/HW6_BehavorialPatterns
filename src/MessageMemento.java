public class MessageMemento {
    private final String content;
    private final long timestamp;

    public MessageMemento(Message message) {
        this.content = message.getContent();
        this.timestamp = message.getTimestamp();
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}