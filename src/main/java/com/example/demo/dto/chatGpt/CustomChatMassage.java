package com.example.demo.dto.chatGpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomChatMassage{
    String role;
    String content;
}
