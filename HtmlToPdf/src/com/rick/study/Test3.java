package com.rick.study;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.text.Document;

/**
 * 测试xml worker 页面包含中文字符的转换
 *
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class Test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String pdfFile = "d:/test9999999.pdf";
		String htmlFile = "d:/rick1314.html";

		Document document = new Document();
		PdfWriter pdfwriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
		pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
		document.open();

		// html文件
		InputStreamReader isr = new InputStreamReader(new FileInputStream(htmlFile), "UTF-8");
		//XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document, isr);
		XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document, isr);
		document.close();

	}

}
