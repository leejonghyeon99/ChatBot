package com.example.demo.service.chatGpt;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ExcelToText {
    public String convert(MultipartFile file) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        textBuilder.append(cell.getNumericCellValue());
                        textBuilder.append("\t"); // 각 셀의 값 사이에 탭 추가
                    } else if (cell.getCellType() == CellType.STRING) {
                        textBuilder.append(cell.getStringCellValue());
                        textBuilder.append("\t"); // 각 셀의 값 사이에 탭 추가
                    }
                }
                textBuilder.append("\n"); // 각 행의 끝에 개행 추가
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textBuilder.toString();
    }
}
