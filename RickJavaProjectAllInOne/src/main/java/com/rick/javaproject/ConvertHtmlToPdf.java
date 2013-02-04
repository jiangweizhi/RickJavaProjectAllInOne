package com.rick.javaproject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;

public class ConvertHtmlToPdf {
	public static void main(String[] args){
		start("http://www.baidu.com","D:\\test.pdf");
	}
	
	private static void start(String htmlUrl,String outputFile){
		try {
			OutputStream os = new FileOutputStream(outputFile);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(htmlUrl); 
			// 解决中文支持问题    
			ITextFontResolver fontResolver = renderer.getFontResolver();
			try {
				fontResolver.addFont("C:/Windows/Fonts/SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				//解决图片的相对路径问题
				renderer.getSharedContext().setBaseURL("file:/D:/");
				renderer.layout();   
				renderer.createPDF(os); 
				os.flush();
				os.close();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}    
		
	}
}
