package com.codecool.fixedchance.domain;

public class Message {

    private final String value;

    public Message(String value) {
        this.value = value;
    }

    public String getMessage() {
        return value;
    }
}
