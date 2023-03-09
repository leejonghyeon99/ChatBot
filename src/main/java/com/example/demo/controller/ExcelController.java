package com.example.demo.controller;

import com.example.demo.dto.chatGpt.CustomChatCompletionResult;
import com.example.demo.dto.chatGpt.CustomChatMassage;
import com.example.demo.service.chatGpt.ChatGptServiceImpl;
import com.example.demo.service.chatGpt.ExcelToText;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/excel")
public class ExcelController {

    private ChatGptServiceImpl chatGptService;

    @Autowired
    ExcelController(ChatGptServiceImpl chatGptService){
        this.chatGptService = chatGptService;
    }

    @PostMapping("/upload")
    public String convertExcelToText(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        ExcelToText excelToText = new ExcelToText();
        List<CustomChatMassage> massage = new ArrayList<>();
        try{
            String text = excelToText.convert(file);
            CustomChatMassage customChatMassage = new CustomChatMassage("user","요약해서 설명해줘"+text);
            massage.add(customChatMassage);
            String msg = chatGptService.result(massage).getChoices().get(0).getMessage().getContent();
            model.addAttribute("excel",msg);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("excel",null);
        }
        return "index";
    }


}
