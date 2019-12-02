package com.taikang.test.FileToImage;

import com.aspose.cells.*;
import com.aspose.words.ImageSaveOptions;
import com.aspose.words.SaveFormat;
import com.aspose.words.SaveOptions;
import org.springframework.core.io.ClassPathResource;


import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConvertToImage {
    public static boolean getLicense() {
        boolean result = false;
        try {
            ClassPathResource resource = new ClassPathResource("static/wordLicense.xml");
            InputStream inputStream = resource.getInputStream();
            License aposeLic = new License();
            aposeLic.setLicense(inputStream);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public  static ByteArrayOutputStream ConvertToImage(InputStream inputStream){
        Workbook book = null;
        if (!getLicense()) {
            return null;
        }
        try {
            book = new Workbook(inputStream);
            Worksheet sheet = book.getWorksheets().get(0);
            sheet.getPageSetup().setLeftMargin(-20);
            sheet.getPageSetup().setRightMargin(0);
            sheet.getPageSetup().setBottomMargin(0);
            sheet.getPageSetup().setTopMargin(0);
            ImageOrPrintOptions imgOptions = new ImageOrPrintOptions();
            imgOptions.setImageFormat(ImageFormat.getJpeg());
            imgOptions.setCellAutoFit(true);
            imgOptions.setOnePagePerSheet(true);
//            imgOptions.setDesiredSize(1000,800);

            SheetRender render = new SheetRender(sheet, imgOptions);
            //render.toImage(0, "D:\\SheetImage.jpg");
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            render.toImage(0,output);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //outputStream转inputStream
    public static ByteArrayInputStream parse(OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }
}