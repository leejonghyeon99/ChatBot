package com.example.demo.dto.chatGpt;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomChatCompletionRequest implements Serializable {
    String model;
    List<CustomChatMassage> messages;
    Double temperature;
    @JsonProperty(value = "top_p")
    Double topP;
    Integer n;
    Boolean stream;
    List<String> stop;
    @JsonProperty(value = "max_tokens")
    Integer maxTokens;
    @JsonProperty(value = "presence_penalty")
    Double presencePenalty;
    @JsonProperty(value = "frequency_penalty")
    Double frequencyPenalty;
    String user;
}
