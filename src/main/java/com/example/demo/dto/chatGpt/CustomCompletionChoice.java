package com.example.demo.dto.chatGpt;

import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.Data;

@Data
public class CustomCompletionChoice {
    Integer index;
    ChatMessage message;
    String finishReason;
}
