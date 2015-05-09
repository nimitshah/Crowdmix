package com.nimitshah.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Message> getMessagesSortByEarliestTime() {
        return messages.stream().sorted((m1,m2)->m2.getDateTime().compareTo(m1.getDateTime())).collect(Collectors.toList());
    }

    public List<User> getFollowing() {
        return following;
    }
}
