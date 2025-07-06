package com.messenger.entity;

public class TextMessage extends Message{
    private String content;

    public TextMessage(User sender, String content) {
        super(sender);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
