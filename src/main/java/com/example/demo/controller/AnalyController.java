package com.example.demo.controller;

import com.example.demo.dto.chatGpt.CustomChatCompletionResult;
import com.example.demo.dto.chatGpt.CustomChatMassage;
import com.example.demo.dto.chatGpt.CustomCompletionChoice;
import com.example.demo.service.chatGpt.ChatGptServiceImpl;
import com.example.demo.service.chatGpt.AnalysServiceImpl;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/analys")
public class AnalyController {

    private ChatGptServiceImpl chatGptService;
    private AnalysServiceImpl analysServiceImpl;
    @Autowired
    AnalyController(ChatGptServiceImpl chatGptService, AnalysServiceImpl analysServiceImpl){
        this.chatGptService = chatGptService;
        this.analysServiceImpl = analysServiceImpl;
    }

    @PostMapping("/upload")
    public String convertExcelToText(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        String text;
        try {
            String contentType = file.getContentType();
            if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                // 엑셀 .xlsx
                text = analysServiceImpl.xlsxConvertText(file);
            } else if (contentType.equals("application/vnd.ms-excel")) {
                // 엑셀 .xls
                text = analysServiceImpl.xlsConvertText(file);
            } else if (contentType.equals("application/pdf")) {
                // PDF 파일 업로드
                text = analysServiceImpl.pdfConvertText(file);
            } else {
                throw new IllegalArgumentException("Unsupported file type");
            }


            List<CustomChatMassage> massage = new ArrayList<>();
            massage.add(new CustomChatMassage("user","분석해줘 "+text));

            CustomCompletionChoice result = chatGptService.result(massage).getChoices().get(0);
            if (result.getFinishReason() == "length"){
                massage.clear();
                massage.add(new CustomChatMassage("user", "이어서 계속말해줘"));
                text = result.getMessage().getContent() +
                        chatGptService.result(massage).getChoices().get(0).getMessage().getContent();
            }
            model.addAttribute("analys",text);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("analys",null);
        }
        return "index";
    }

}
