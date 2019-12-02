package com.taikang.test.FileToImage;




import com.aspose.cells.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class WordToPngController {
    public static void main(String[] args) {
        File file = new File("D:\\我的资料\\网盘资料\\30 Linux性能优化实战\\pdf\\03 基础篇：经常说的 CPU 上下文切换是什么意思？（上）.pdf");
        try {
            FileInputStream fis = new FileInputStream(file);
            AsposeUtil.parseFileToBase64_PNG(fis,10,"txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
