package com.example.demo.service.chatGpt;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class AnalysServiceImpl implements AnalysService{

    public String xlsxConvertText(MultipartFile file) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textBuilder.toString();
    }

    public String xlsConvertText(MultipartFile file) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        Workbook workbook = new HSSFWorkbook(file.getInputStream());
        try {
            Sheet sheet = workbook.getSheetAt(0);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textBuilder.toString();
    }


    public String pdfConvertText(MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();
        return text;
    }
}
