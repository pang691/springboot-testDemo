package com.taikang.test.FileToImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.aspose.words.*;
import org.apache.fontbox.encoding.Encoding;

/**
 * @author XXX
 *
 */
public class Word2PdfUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
//		doc2pdf("C:\\Users\\lenovo\\Desktop\\新建 Microsoft Word 文档.docx",
//				"C:\\Users\\lenovo\\Desktop\\2.pdf");   //测试成功
//		doc2pdf("C:\\Users\\lenovo\\Desktop\\新建 Microsoft Word 文档.docx",
//				"C:\\Users\\lenovo\\Desktop\\1.jpg");
		//doc2png("D://ImageTest//0054.doc", "D://ImageTest//0054.png");   //测试通过
		try {
			doc2html("C:\\Users\\lenovo\\Desktop\\新建 Microsoft Word 文档.docx",
					"C:\\Users\\lenovo\\Desktop\\1.html");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//	public static boolean getLicense() {
//		boolean result = false;
//		try {
//			InputStream is = Test.class.getClassLoader().getResourceAsStream("license.xml");
//                   // license.xml应放在..\WebRoot\WEB-INF\classes路径下
//			System.out.println(Test.class.getClassLoader().getResource("").getPath());
//			License aposeLic = new License();
//			aposeLic.setLicense(is);
//			result = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	public static void doc2html(String inPath, String outPath) throws Exception {
		long old = System.currentTimeMillis();
		File file = new File(outPath); // 新建一个空白pdf文档
		FileOutputStream os = new FileOutputStream(outPath);
		Document doc = new Document(inPath); // Address是将要被转化的word文档
		HtmlSaveOptions htmlSaveOptions = new HtmlSaveOptions(SaveFormat.HTML);
		htmlSaveOptions.setEncoding(Charset.forName("x-mswin-936"));
		doc.save(os, htmlSaveOptions );// 全面支持DOC, DOCX, OOXML, RTF HTML,
		long now = System.currentTimeMillis();
		System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
	}
	public static void doc2pdf(String inPath, String outPath) {

//		if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
//			return;
//		}
		try {
			long old = System.currentTimeMillis();
			File file = new File(outPath); // 新建一个空白pdf文档
			FileOutputStream os = new FileOutputStream(outPath);
			Document doc = new Document(inPath); // Address是将要被转化的word文档
			doc.save(os, SaveFormat.PNG );// 全面支持DOC, DOCX, OOXML, RTF HTML,
			// OpenDocument, PDF,
			// EPUB, XPS, SWF 相互转换
//			Document doc = new Document(getMyDir() + "Document.doc");
//			doc.save(getMyDir() + "Document Out.html");
			long now = System.currentTimeMillis();
			System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
