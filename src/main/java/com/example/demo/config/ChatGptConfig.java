package com.example.demo.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "openai")
public class ChatGptConfig {
    private String authorization;
    private String bearer;
    private String apiKey;
    private String model;
    private Integer maxToken;
    private Double temperature;
    private Double topP;
    private String mediaType;
    private String url;
    private double presencePenalty;
    private double frequencyPenalty;
    private boolean stream;
    private List<String> stop;
    private String user;
    private int n;
}