package com.rick.study;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.WritableDirectElement;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.tool.xml.ElementHandler;
import com.itextpdf.tool.xml.Writable;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.pipeline.WritableElement;

/**
 * HTML文件转换为PDF
 *
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class Demo4HTMLFile2PDF {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String htmlFile = "d:/rick520.html";

		// 直接把HTML文件转为PDF文件
		String pdfFile = "d:/demo-htmlfile.pdf";
		Demo4HTMLFile2PDF.parseHTML2PDFFile(pdfFile, new FileInputStream(
				htmlFile));

		// HTML文件转为PDF中的Elements
		/*String pdfFile2 = "d:/test/itext/demo-htmlfile2.pdf";
		Demo4HTMLFile2PDF.parseHTML2PDFElement(pdfFile2, new FileInputStream(
				htmlFile));*/
	}

	/**
	 * 用于HTML直接转换为PDF文件
	 *
	 * @param fileName
	 * @throws Exception
	 */
	public static void parseHTML2PDFFile(String pdfFile,
			InputStream htmlFileStream) throws Exception {

		BaseFont bfCN = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		// 中文字体定义
		Font chFont = new Font(bfCN, 12, Font.NORMAL, BaseColor.BLUE);
		Font secFont = new Font(bfCN, 12, Font.NORMAL, new BaseColor(0, 204,
				255));

		Document document = new Document();
		PdfWriter pdfwriter = PdfWriter.getInstance(document,
				new FileOutputStream(pdfFile));
		pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
		document.open();

		int chNum = 1;
		Chapter chapter = new Chapter(new Paragraph("HTML文件转PDF测试", chFont),
				chNum++);

		Section section = chapter.addSection(new Paragraph("/dev/null 2>&1 详解",
				secFont));
		// section.setNumberDepth(2);
		// section.setBookmarkTitle("基本信息");
		section.setIndentation(10);
		section.setIndentationLeft(10);
		section.setBookmarkOpen(false);
		section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
		section.add(Chunk.NEWLINE);
		document.add(chapter);

		// html文件
		InputStreamReader isr = new InputStreamReader(htmlFileStream, "UTF-8");

		// 方法一：默认参数转换
		XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document, isr);

		// 方法二：可以自定义参数
		// HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
		// htmlContext.charSet(Charset.forName("UTF-8"));
		// htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
		// CSSResolver cssResolver = XMLWorkerHelper.getInstance()
		// .getDefaultCssResolver(true);
		// Pipeline<?> pipeline = new CssResolverPipeline(cssResolver,
		// new HtmlPipeline(htmlContext, new PdfWriterPipeline(document,
		// pdfwriter)));
		// XMLWorker worker = new XMLWorker(pipeline, true);
		// XMLParser p = new XMLParser();
		// p.addListener(worker);
		//
		// p.parse(isr);
		// p.flush();

		document.close();
	}

	/**
	 * HTML文件转为PDF中的Elements,便于把HTML内容追加到已有的PDF中
	 *
	 * @param pdfFile
	 * @param htmlFileStream
	 */
	public static void parseHTML2PDFElement(String pdfFile,
			InputStream htmlFileStream) {
		try {
			Document document = new Document(PageSize.A4);

			FileOutputStream outputStream = new FileOutputStream(pdfFile);
			PdfWriter pdfwriter = PdfWriter.getInstance(document, outputStream);
			// pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
			document.open();

			BaseFont bfCN = BaseFont.createFont("STSongStd-Light",
					"UniGB-UCS2-H", false);
			// 中文字体定义
			Font chFont = new Font(bfCN, 12, Font.NORMAL, BaseColor.BLUE);
			Font secFont = new Font(bfCN, 12, Font.NORMAL, new BaseColor(0,
					204, 255));
			Font textFont = new Font(bfCN, 12, Font.NORMAL, BaseColor.BLACK);

			int chNum = 1;
			Chapter chapter = new Chapter(new Paragraph(
					"HTML文件转PDF元素，便于追加其他内容", chFont), chNum++);

			Section section = chapter.addSection(new Paragraph(
					"/dev/null 2>&1 详解", secFont));

			section.setIndentation(10);
			section.setIndentationLeft(10);
			section.setBookmarkOpen(false);
			section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
			section.add(Chunk.NEWLINE);

			final List<Element> pdfeleList = new ArrayList<Element>();
			ElementHandler elemH = new ElementHandler() {

				public void add(final Writable w) {
					if (w instanceof WritableElement) {
						pdfeleList.addAll(((WritableElement) w).elements());
					}

				}
			};
			InputStreamReader isr = new InputStreamReader(htmlFileStream,
					"UTF-8");
			XMLWorkerHelper.getInstance().parseXHtml(elemH, isr);

			List<Element> list = new ArrayList<Element>();
			for (Element ele : pdfeleList) {
				if (ele instanceof LineSeparator
						|| ele instanceof WritableDirectElement) {
					continue;
				}
				list.add(ele);
			}
			section.addAll(list);

			section = chapter.addSection(new Paragraph("继续添加章节", secFont));

			section.setIndentation(10);
			section.setIndentationLeft(10);
			section.setBookmarkOpen(false);
			section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
			section.add(new Chunk("测试HTML转为PDF元素，方便追加其他内容", textFont));

			document.add(chapter);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}