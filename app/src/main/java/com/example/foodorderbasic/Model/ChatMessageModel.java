package com.example.foodorderbasic.Model;

public class ChatMessageModel {

    private String message;
    private boolean isUser;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public ChatMessageModel(String message, boolean isUser) {
        this.message = message;
        this.isUser = isUser;
    }

    public ChatMessageModel() {
    }
}
