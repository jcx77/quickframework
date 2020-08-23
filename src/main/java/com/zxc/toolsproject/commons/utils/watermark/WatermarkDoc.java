package com.zxc.toolsproject.commons.utils.watermark;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.TextWatermark;
import com.spire.doc.documents.BackgroundType;
import com.spire.doc.documents.WatermarkLayout;

import java.awt.*;
/**
* @Description: TODO(文档水印)
* @Param: 
* @return: 
* @Author: zcx
* @Date: 2020/7/4 13:12
*/
public class WatermarkDoc {


	public static void getDocText(String impDoc, String ExpDoc, String text) {

		Document document = new Document();
		document.loadFromFile(impDoc);

		int index = document.getSections().getCount();

		for (int i = 0; i < index; i++) {
			insertTextWatermark(document.getSections().get(i), text);
		}

		document.saveToFile(ExpDoc, FileFormat.Docx);

	}


	public  static void getDocImg(String impDoc, String ExpDoc, String img) {

		String inputFile = impDoc;
		String backgroundImg = img;
		String outputFile = ExpDoc;

		// load a word document
		Document document = new Document(inputFile);

		// set the background type as picture
		document.getBackground().setType(BackgroundType.Picture);

		// set the background picture
		document.getBackground().setPicture(backgroundImg);

		// save the file
		document.saveToFile(outputFile, FileFormat.Docx);

	}

	private static void insertTextWatermark(Section section, String text) {
		TextWatermark txtWatermark = new TextWatermark();
		txtWatermark.setText(text);
		txtWatermark.setFontSize(40);
		txtWatermark.setColor(Color.red);
		txtWatermark.setLayout(WatermarkLayout.Diagonal);
		section.getDocument().setWatermark(txtWatermark);
	}

}
