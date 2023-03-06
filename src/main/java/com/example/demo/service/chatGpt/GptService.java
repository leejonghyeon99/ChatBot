package com.example.demo.service.chatGpt;

import com.example.demo.dto.chatGpt.CustomChatCompletionRequest;
import com.example.demo.dto.chatGpt.CustomChatCompletionResult;
import com.example.demo.dto.chatGpt.CustomChatMassage;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import org.springframework.http.HttpEntity;

import java.util.List;
import java.util.Map;

public interface GptService {

    public HttpEntity<CustomChatCompletionRequest> buildHttpEntity(CustomChatCompletionRequest CustomChatCompletionRequest);
    public CustomChatCompletionResult getResponse(HttpEntity<CustomChatCompletionRequest> customChatCompletionRequestHttpEntity);
    public CustomChatCompletionResult result(List<CustomChatMassage> massage);
}
