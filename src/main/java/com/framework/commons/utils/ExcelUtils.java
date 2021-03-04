package com.framework.commons.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class ExcelUtils {

    //输入一个MultipartFile file，返回一个输入流
    public static InputStream getInputStream(MultipartFile file){
        try {
            return file.getInputStream();
        } catch (IOException e) {
            e.getCause();
            return null;
        }
    }
    //输入一个MultipartFile file，返回一个HSSFWorkbook
    public  static HSSFWorkbook getHSSFWorkBook(MultipartFile file){
        HSSFWorkbook hssfWorkbook;
        try {
            hssfWorkbook = new HSSFWorkbook(ExcelUtils.getInputStream(file));
        } catch (Exception e) {
            e.getCause();
            return null;
        }
        return hssfWorkbook;
    }

    //输入一个HSSFWorkbook，直接关闭
    public  static void destroyHSSFWorkBook(HSSFWorkbook hw){
        try {
            hw.close();
        } catch (Exception e) {
            e.getCause();
            /////////----------------
        }
    }

}
