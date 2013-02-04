package com.rick.study;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.SAXmyHtmlHandler;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class TestIText {
	
	public static void main(String[] args) {
		String htmlCode = "<html xmlns='http://www.w3.org/1999/xhtml'><head><title>心悸 - 一心健康网</title></head>";
		htmlCode += "<body>江伟智</body></html>";
		
		TestIText ih = new TestIText();  
        //ih.htmlCodeComeFromFile("D:\\rick.html", "D:\\iText_1.pdf");  
        //ih.htmlCodeComeString(htmlCode, "D:\\iText_2.pdf");  
		//ih.start("D:\\rick.html", "D:\\iText_3.pdf");  
		try {
			ih.html2pdf();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void html2pdf() throws Exception {
        String htmlPath = "D:\\rick.html";
        Document doc = new Document();
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        PdfWriter.getInstance(doc, new FileOutputStream("d:\\test8899.pdf"));
        SAXmyHtmlHandler saxHandler = new SAXmyHtmlHandler(doc, bf);
        parser.parse(new File(htmlPath), saxHandler);
    }

	
	public void htmlCodeComeFromFile(String filePath, String pdfPath) {  
        Document document = new Document();  
        try {  
            StyleSheet st = new StyleSheet();  
            st.loadTagStyle("body", "leading", "16,0");  
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));  
            document.open();  
            ArrayList p = HTMLWorker.parseToList(new FileReader(filePath), st);  
            for(int k = 0; k < p.size(); ++k) {  
                document.add((Element)p.get(k));  
            }  
            document.close();  
            System.out.println("文档创建成功");  
        }catch(Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void htmlCodeComeString(String htmlCode, String pdfPath) {  
        Document doc = new Document(PageSize.A4);  
        try {  
            PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));  
            doc.open();  
            // 解决中文问题  
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
            Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);  
            Paragraph t = new Paragraph(htmlCode, FontChinese);  
            doc.add(t);  
            doc.close();  
            System.out.println("文档创建成功");  
        }catch(Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    public void start(String htmlUrl,String outputFile){
		try {
			OutputStream os = new FileOutputStream(outputFile);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(htmlUrl); 
			// 解决中文支持问题    
			ITextFontResolver fontResolver = renderer.getFontResolver();
			try {
				BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
	            Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);  
	            //fontResolver.addFont(bfChinese,"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
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
