package com.example.demo.controller;

import com.example.demo.dto.chatGpt.CustomChatCompletionResult;
import com.example.demo.dto.chatGpt.CustomChatMassage;
import com.example.demo.dto.chatGpt.CustomChatMassageRole;
import com.example.demo.service.chatGpt.ChatGptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat-gpt")
public class ChatGptController {

    private ChatGptServiceImpl chatGptService;

    @Autowired
    ChatGptController(ChatGptServiceImpl chatGptService){
        this.chatGptService = chatGptService;
    }

    @PostMapping("/question")
    public CustomChatCompletionResult sendMassage(@RequestBody List<CustomChatMassage> massage){
        return chatGptService.result(massage);
    }
}
