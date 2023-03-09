package com.example.demo.controller;

import com.example.demo.dto.chatGpt.CustomChatCompletionResult;
import com.example.demo.dto.chatGpt.CustomChatMassage;
import com.example.demo.service.chatGpt.ChatGptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/chat")
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
