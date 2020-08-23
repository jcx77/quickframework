package com.zxc.toolsproject.commons.utils.watermark;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

public class WatermarkUtils {

    public  static void getPdfImg(String impPdf, String ExpPdf, String imgLogo) {
        //pdf添加图片水印
        WaterMarkPdf.getPdfImg(impPdf, ExpPdf, imgLogo);
        System.out.println("OK");
    }
    
    public static  void getPdfText(String impPdf, String ExpPdf, String text) {
        //pdf添加文字水印
        //WaterMarkPdf.getPdfText( impPdf,  ExpPdf,  text);//此方法不使用
        addPdfWaterMark( impPdf,  ExpPdf,  text);
        System.out.println("OK");
    }
    
    public  static void getDocText(String impDoc, String ExpDoc, String text) {
        //word添加图片水印
        WatermarkDoc.getDocText(impDoc, ExpDoc, text);
        System.out.println("OK");
    }
    
    public static  void getDocImg(String impDoc, String ExpDoc, String img) {
        //word添加文字水印
        WatermarkDoc.getDocImg(impDoc,  ExpDoc, img);
        System.out.println("OK");
    }
    
    
    public static  void getCustomImgText(String impImg,String expImg,String text) throws Exception {
        //自定义写入文字水印
        WaterMarkImg.getCustomImgText( impImg, expImg, text);
        System.out.println("OK");
    }
    public static  void getCustomImgToImg(String pressImg, String targetImg, int x, int y) throws Exception {
        //自定义写入图片水印
        WaterMarkImg.getCustomImgToImg( pressImg,  targetImg, x,  y);
        System.out.println("OK");
    }
    
    public static  void getThumbnailsImgToImg(String waterMarkImg,String impImg,String expImg) throws Exception {
        //Thumbnails生成图片水印
        WaterMarkImg.getThumbnailsImgToImg( waterMarkImg, impImg, expImg);
        System.out.println("OK");
    }
    public static void addPdfWaterMark(String srcFile, String destFile, String text) {
        // 待加水印的文件
        PdfReader reader =null;
        FileOutputStream fos=null;
        PdfStamper stamper=null;
        try {
             reader = new PdfReader(srcFile);
             fos=new FileOutputStream(destFile);
            // 加完水印的文件
            stamper = new PdfStamper(reader,fos );
            int total = reader.getNumberOfPages() + 1;
            PdfContentByte content;
            // 设置透明度
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.3f);
            // 设置字体
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            // 循环对每页插入水印
            for (int i = 1; i < total; i++) {
                // 水印的起始
                content = stamper.getOverContent(i);
                content.setGState(gs);
                content.setFontAndSize(base, 32);
                // 开始
                content.beginText();
                // 设置颜色 默认为黑色
                content.setColorFill(BaseColor.RED);
                // 开始写入水印
                content.showTextAligned(Element.ALIGN_MIDDLE, text, 180, 340, 45);
                content.endText();
            }

            stamper.close();
            reader.close();
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != stamper) {
                try {
                    stamper.close();
                } catch (Exception e) {
                }
            }
            if (null != reader) {
                try {
                    reader.close();
                } catch (Exception e) {
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (Exception e) {
                }
            }
          
        }
    }
    public static void main(String[] args)  {
        /*
        WatermarkUtils.getPdfImg("D:\\test副本\\jd.pdf", "D:\\Test\\imageWaterMark.pdf", "D:\\Test\\logo.png");
        WatermarkUtils.getPdfText("D:\\test副本\\测 试.pdf", "D:\\Test22\\测 试2.pdf", "测试测试测试测试测试测试测试");
        WatermarkUtils.getDocText("D:\\test副本\\tb.docx", "D:\\Test\\imageWaterMarktext.docx", "测试java2020-03-17");
        WatermarkUtils.getDocImg("D:\\test副本\\tb.docx", "D:\\Test\\imageWaterMarkimg.docx", "D:\\Test\\logo.png");
        WatermarkUtils.getCustomImgText("D:\\test副本\\texstzxj.jpg","D:\\Test\\1111111.JPG","水印测试 1233");
        WatermarkUtils.getCustomImgToImg("D:\\test副本\\logo.png","D:\\Test\\DSC01550.JPG",100,100);
        WatermarkUtils.getThumbnailsImgToImg("D:\\test副本\\logo.png","D:\\Test\\6622605_202640535324_2.jpg","D:\\Test\\22222.JPG");
        WatermarkUtils.getPdfText("d:\\ftproot\\T_YWYJ_CS1\\66d5f728045648bdae9ca75078ecd8a1\\888.pdf", "d:\\ftproottmp\\T_YWYJ_CS1\\66d5f728045648bdae9ca75078ecd8a1\\888.pdf", "测试java2020-03-17");
       */ 
        /*
        for(int i=0;i<8;i++) {
            System.out.println(i);
        WatermarkUtils.getPdfText("D:\\ftproot\\T_CS_1\\2021-220203-LI92030100\\f925b462183c4bcdaebed23ff2529b82.pdf", "D:\\Test22\\测 试2.pdf", "测试测试测试测试测试测试测试");
        } 
        */  
    }
}
