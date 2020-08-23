package com.zxc.toolsproject.commons.utils.watermark;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
*/

public class WaterMarkImg {

	public static void getThumbnailsImgToImg(String waterMarkImg,String impImg,String expImg) throws Exception {
		//URL checkUrl = new URL("http://img.ctrip.com/v1/tfs/T1WbdTBsWX1RCvBVdK");
		
		BufferedImage checkImage = ImageIO.read(new File(waterMarkImg)) ;
		File file = new File(impImg);
		BufferedImage sourceImage = ImageIO.read(file);

		Thumbnails.of(sourceImage).size(sourceImage.getWidth(), sourceImage.getHeight())
				.watermark(Positions.CENTER_RIGHT, checkImage, 1f).toFile(expImg);

	}
	
	public static void getCustomImgText(String impImg,String expImg,String text) throws IOException {
        String logoText = text;
        Font font = new Font("MS Song", Font.BOLD, 10);
		BufferedImage bufferedImage = ImageIO.read(new File(impImg));
		BufferedImage bufImge = waterMark(bufferedImage, logoText , font,-20);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufImge, "jpg", baos);
		File file = new File(expImg);
		if(file.exists()) {
			file.delete();
		}
		FileOutputStream out = new FileOutputStream(file, true);
		out.write(baos.toByteArray());
		out.flush();
		out.close();
	}
	
	public  static void getCustomImgToImg(String pressImg, String targetImg,  
            int x, int y) throws IOException {
		pressImage(pressImg, targetImg, x, y);
	}
	
	
	/**
     * 指定字体进行水印
     * 
     * @param bufferedImage
     * @param logoText
     * @param font
     * @param degree
     * @return
     */
    public  static BufferedImage waterMark(BufferedImage bufferedImage, String logoText, Font font, Integer degree) {
        if (bufferedImage == null) {
            return null;
        }
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int fontSize = (int) Math.sqrt(width * width + height * height) / (logoText.length() / 2 + 5);
        font = font.deriveFont(Font.BOLD, fontSize);
        double radians = Math.atan(height * 1.0 / width);

        Graphics2D graphics = bufferedImage.createGraphics();
        // 设置对线段的锯齿状边缘处理
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(bufferedImage, 0, 0, null);
        if (null != degree) {
            // 设置水印旋转
            graphics.translate(width / 2.0, height / 2.0);
            graphics.rotate(-radians);// , width + fontSize / 2, 0);
      
        }
        // 设置颜色
        graphics.setColor(Color.black);
        // 设置 Font
        graphics.setFont(font);
        float alpha = 0.5f;
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        graphics.drawString(logoText, -width / 2, 0);
        graphics.dispose();
        return bufferedImage;
    }
    
    /**
     * 把图片印刷到图片上 
     *  
     * @param pressImg -- 
     *            水印文件 
     * @param targetImg -- 
     *            目标文件 
     * @param x 
     *            --x坐标 
     * @param y 
     *            --y坐标 
     * @throws IOException 
     */
    public   static void pressImage(String pressImg, String targetImg,  
            int x, int y) throws IOException{
    	//目标文件
    	File file = new File(targetImg);
    	Image target = ImageIO.read(file);
    	int height = target.getHeight(null);
    	int width = target.getWidth(null);
    	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    	Graphics2D g = image.createGraphics();
    	g.drawImage(target, 0,0, width, height,null);
    	//水印文件
    	File fileMark = new File (pressImg);
    	Image water = ImageIO.read(fileMark);
    	int height_water  = water.getHeight(null);
    	int width_water  = water.getWidth(null);
    	System.out.println(height_water + "\t" + width_water);
    	// 左右\上下\宽\高  
        g.drawImage(water,x,  
                y, width/5, height/5, null);
        //水印结束
        g.dispose();
        FileOutputStream fos = new FileOutputStream(targetImg);
        /*
        JPEGImageEncoder encoder =  JPEGCodec.createJPEGEncoder(fos);
        encoder.encode(image);
        */
        fos.close();
    			
    }

}
