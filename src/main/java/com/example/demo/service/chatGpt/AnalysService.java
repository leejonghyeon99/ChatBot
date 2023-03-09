package com.example.demo.service.chatGpt;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AnalysService {

    public String xlsxConvertText(MultipartFile file) throws IOException;
    public String xlsConvertText(MultipartFile file) throws IOException;
    public String pdfConvertText(MultipartFile file) throws IOException;
}
