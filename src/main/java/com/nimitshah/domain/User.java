package com.nimitshah.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;

    private List<Message> messages = new ArrayList<>();

    private List<User> following = new ArrayList<>();

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<User> getFollowing() {
        return following;
    }
}
