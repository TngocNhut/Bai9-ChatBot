package com.tngocnhat.chatbot;

public class Message {
    private int id;
    private String sessionUser;
    private String senderRole; // "guest" or "manager"
    private String content;
    private long timestamp;

    public Message() {
    }

    public Message(int id, String sessionUser, String senderRole, String content, long timestamp) {
        this.id = id;
        this.sessionUser = sessionUser;
        this.senderRole = senderRole;
        this.content = content;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(String sessionUser) {
        this.sessionUser = sessionUser;
    }

    public String getSenderRole() {
        return senderRole;
    }

    public void setSenderRole(String senderRole) {
        this.senderRole = senderRole;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
