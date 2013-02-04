package com.rick.study;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class DemoMyFirstPDF {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String pdfPath = "d:/demo-first.pdf";
		createFirstPDF(pdfPath);
	}

	public static void createFirstPDF(String pdfPath) throws Exception {
		// 第一步： Create a Document
		Document document = new Document(PageSize.A4);
		// 第二 步： Get a PdfWriter instance.
		PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
		// 第三步：Open the Document.
		document.open();

		// 添加Meta信息
		document.addAuthor("Michael Sun");
		document.addCreator("Michael Sun");
		document.addTitle("Michael的技术博客");
		document.addSubject("技术博客");
		document.addCreationDate();
		document.addKeywords("开源技术,企业架构,集群,负载均衡,分布式,J2EE,Java,SSH");

		// 添加Header信息
		document.addHeader("blog", "http://www.micmiu.com");
		document.addHeader("twitter", "@suncto");
		document.addHeader("weibo", "http://weibo.com/ctosun");
		document.addHeader("mail", "sjsky007@gmail.coom");

		// 第四步：添加内容

		// 添加 Paragraph
		document.add(new Paragraph("Hello iText."));

		document.add(Chunk.NEWLINE);

		// 添加 中文信息
		BaseFont bfCN = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
				false);
		Font fontCN = new Font(bfCN, 12, Font.NORMAL, BaseColor.BLUE);
		document.add(new Paragraph("这是中文：欢迎来到iText世界。", fontCN));

		// 第五步：Close the Document.
		document.close();
	}

}