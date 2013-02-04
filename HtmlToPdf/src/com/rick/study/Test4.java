package com.rick.study;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class Test4 {
	public static void main(String[] args) {
		Document doc = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(
					"D:\\ItextDemo.pdf"));
			doc.open();
			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			Font font = new Font(bf, 12, Font.NORMAL);
			doc.add(new Paragraph("中文测试。。 Itext! ", font));
			doc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
