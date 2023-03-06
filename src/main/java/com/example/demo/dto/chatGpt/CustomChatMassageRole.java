package com.example.demo.dto.chatGpt;

public enum CustomChatMassageRole {
    SYSTEM("system"),
    USER("user"),
    ASSISTANT("assistant");

    private final String value;

    CustomChatMassageRole(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
