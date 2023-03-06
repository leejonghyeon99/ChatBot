package com.example.demo;

import com.example.demo.config.ChatGptConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    ChatGptConfig chatGptConfig;

    @Test
    void contextLoads() {
        System.out.println(chatGptConfig.toString());
    }

}
