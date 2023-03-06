package com.example.demo.service.chatGpt;

import com.example.demo.config.ChatGptConfig;
import com.example.demo.dto.chatGpt.CustomChatCompletionRequest;
import com.example.demo.dto.chatGpt.CustomChatCompletionResult;
import com.example.demo.dto.chatGpt.CustomChatMassage;
import com.example.demo.dto.chatGpt.CustomChatMassageRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ChatGptServiceImpl implements GptService{

    private ChatGptConfig chatGptConfig;

    @Autowired
    public ChatGptServiceImpl(ChatGptConfig chatGptConfig) {
        this.chatGptConfig = chatGptConfig;
    }

    public HttpEntity<CustomChatCompletionRequest> buildHttpEntity(CustomChatCompletionRequest customChatCompletionRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(chatGptConfig.getMediaType()));
        headers.add(chatGptConfig.getAuthorization(), chatGptConfig.getBearer() + " " + chatGptConfig.getApiKey());
        return new HttpEntity<>(customChatCompletionRequest, headers);
    }

    public CustomChatCompletionResult getResponse(HttpEntity<CustomChatCompletionRequest> customChatCompletionRequestHttpEntity) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<CustomChatCompletionResult> responseEntity = restTemplate.postForEntity(
                chatGptConfig.getUrl(),
                customChatCompletionRequestHttpEntity,
                CustomChatCompletionResult.class);
        return responseEntity.getBody();
    }

    public CustomChatCompletionResult result(List<CustomChatMassage> massage) {
        return this.getResponse(
                this.buildHttpEntity(CustomChatCompletionRequest.builder()
                        .model(chatGptConfig.getModel())
                        .messages(massage)
                        .temperature(chatGptConfig.getTemperature())
                        .topP(chatGptConfig.getTopP())
                        .n(chatGptConfig.getN())
                        .stream(chatGptConfig.isStream())
                        .stop(chatGptConfig.getStop())
                        .maxTokens(chatGptConfig.getMaxToken())
                        .presencePenalty(chatGptConfig.getPresencePenalty())
                        .frequencyPenalty(chatGptConfig.getFrequencyPenalty())
                        .user(chatGptConfig.getUser())
                        .build())
                );
    }

}
