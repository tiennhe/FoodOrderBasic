package com.example.foodorderbasic.Model;

public class ChatBotModel {

    private String messagesSend ;
    private String messagesGet;

    private String Uid ;

    public String getMessagesSend() {
        return messagesSend;
    }

    public void setMessagesSend(String messagesSend) {
        this.messagesSend = messagesSend;
    }

    public String getMessagesGet() {
        return messagesGet;
    }

    public void setMessagesGet(String messagesGet) {
        this.messagesGet = messagesGet;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public ChatBotModel() {
    }

    public ChatBotModel(String messagesSend, String messagesGet, String uid) {
        this.messagesSend = messagesSend;
        this.messagesGet = messagesGet;
        Uid = uid;
    }
}
