package school21.sockets.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Long            messageId;
    private User            author;
    private String          text;
    private LocalDateTime   localDateTime;

    public Message(Long messageId, User author, String text, LocalDateTime localDateTime) {
        this.messageId = messageId;
        this.author = author;
        this.text = text;
        this.localDateTime = localDateTime;
    }

    public Message() {}

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;

    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId.equals(message.messageId) && author.equals(message.author) && text.equals(message.text) && localDateTime.equals(message.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, author, text, localDateTime);
    }

    @Override
    public String toString() {
        return "Message: {\n" +
                "messageId=" + messageId +
                "\nauthor=" + author +
                "\ntext='" + text + '\'' +
                "\nlocalDateTime=" + localDateTime +
                '}';
    }
}