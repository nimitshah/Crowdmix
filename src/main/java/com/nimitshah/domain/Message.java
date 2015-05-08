package com.nimitshah.domain;

import java.time.LocalDateTime;

public class Message {
    private String message;
    private LocalDateTime dateTime;

    public Message(String message) {
        this.message = message;
        dateTime = LocalDateTime.now();
    }
}
