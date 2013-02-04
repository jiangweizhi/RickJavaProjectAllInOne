package com.rick.study;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

public class HtmlToPdf {
	public static void main(String[] args) {
		start();
	}

	private static void start() {
		try {
			convertHtmlToPdf("D:\\rick.html", "D:\\test235.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void convertHtmlToPdf(String inputFile, String outputFile)
			throws Exception {

		OutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		String url = new File(inputFile).toURI().toURL().toString();

		renderer.setDocument(url);

		// 解决中文支持问题
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont("C:/Windows/Fonts/SIMSUN.TTC",
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		// 解决图片的相对路径问题
		renderer.getSharedContext().setBaseURL("file:/D:/");
		renderer.layout();
		renderer.createPDF(os);

		os.flush();
		os.close();
	}

}
