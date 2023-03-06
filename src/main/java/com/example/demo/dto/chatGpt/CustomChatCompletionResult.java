package com.example.demo.dto.chatGpt;

import com.theokanning.openai.Usage;
import lombok.Data;

import java.util.List;


@Data
public class CustomChatCompletionResult {
    String id;
    String object;
    long created;
    String model;
    List<CustomCompletionChoice> choices;
    Usage usage;
}
