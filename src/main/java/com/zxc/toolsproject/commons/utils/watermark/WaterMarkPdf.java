package com.zxc.toolsproject.commons.utils.watermark;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.*;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class WaterMarkPdf {
		public static  void getPdfImg(String impPdf, String ExpPdf, String imgLogo) {

			PdfDocument doc = new PdfDocument();
			doc.loadFromFile(impPdf);
			int index = doc.getPages().getCount();

			for (int i = 0; i < index; i++) {

				PdfPageBase page = doc.getPages().get(i);

				page.setBackgroundImage(imgLogo);

				Rectangle2D.Float rect = new Rectangle2D.Float();
				rect.setRect(280, 300, 150, 150);

				page.setBackgroundRegion(rect);

			}
			// doc.saveToFile("D:\\Test\\imageWaterMark.pdf");
			doc.saveToFile(ExpPdf);
			doc.close();

		}

		public static  void getPdfText(String impPdf, String ExpPdf, String text) {

			PdfDocument pdf = new PdfDocument();

			pdf.loadFromFile(impPdf);
			int index = pdf.getPages().getCount();

			for (int i = 0; i < index; i++) {
				PdfPageBase page = pdf.getPages().get(i);

				insertWatermark(page, text);

			}
			pdf.saveToFile(ExpPdf);

		}
		


		static void insertWatermark(PdfPageBase page, String watermark) {
		    /*
			Dimension2D dimension2D = new Dimension();
			dimension2D.setSize(page.getCanvas().getClientSize().getWidth() / 2,
					page.getCanvas().getClientSize().getHeight() / 3);
			PdfTilingBrush brush = new PdfTilingBrush(dimension2D);
			brush.getGraphics().setTransparency(0.3F);
			brush.getGraphics().save();
			brush.getGraphics().translateTransform((float) brush.getSize().getWidth() / 2,
					(float) brush.getSize().getHeight() / 2);
			brush.getGraphics().rotateTransform(-45);
			brush.getGraphics().drawString(watermark, new PdfFont(PdfFontFamily.Helvetica, 24), PdfBrushes.getViolet(), 0,
					0, new PdfStringFormat(PdfTextAlignment.Center));
			brush.getGraphics().restore();
			brush.getGraphics().setTransparency(1);
			Rectangle2D loRect = new Rectangle2D.Float();
			loRect.setFrame(new Point2D.Float(0, 0), page.getCanvas().getClientSize());
			page.getCanvas().drawRectangle(brush, loRect);
			*/
		    Font font = new Font("宋体", 10, 20);
	        PdfTrueTypeFont pdfTrueTypeFont = new PdfTrueTypeFont(font, true); //true防止中文乱码
	        
	        
		    Dimension2D dimension2D = new Dimension();
            dimension2D.setSize(page.getCanvas().getClientSize().getWidth() / 2,
                    page.getCanvas().getClientSize().getHeight() / 3);
            PdfTilingBrush brush = new PdfTilingBrush(dimension2D);
            brush.getGraphics().setTransparency(0.3F);
            brush.getGraphics().save();
            brush.getGraphics().translateTransform((float) brush.getSize().getWidth() / 2,
                    (float) brush.getSize().getHeight() / 2);
            brush.getGraphics().rotateTransform(-45);
            
            //brush.getGraphics().drawString(watermark, new PdfFont(PdfFontFamily.Helvetica, 24), PdfBrushes.getViolet(), 0,
            //        0, new PdfStringFormat(PdfTextAlignment.Center));
            brush.getGraphics().drawString(watermark, pdfTrueTypeFont, PdfBrushes.getViolet(), 0 , 0 , new PdfStringFormat(PdfTextAlignment.Center));
            brush.getGraphics().restore();
            brush.getGraphics().setTransparency(1);
            Rectangle2D loRect = new Rectangle2D.Float();
            loRect.setFrame(new Point2D.Float(0, 0), page.getCanvas().getClientSize());
            page.getCanvas().drawRectangle(brush, loRect);
		}
		

}