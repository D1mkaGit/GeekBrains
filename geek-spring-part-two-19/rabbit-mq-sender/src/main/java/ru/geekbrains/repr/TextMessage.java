package ru.geekbrains.repr;

public class TextMessage {

    private String text;

    private String sender;

    public TextMessage() {
    }

    public TextMessage(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
