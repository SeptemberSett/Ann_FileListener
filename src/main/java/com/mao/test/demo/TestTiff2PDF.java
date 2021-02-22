package com.mao.test.demo;

/**
 * @Author Mr mao
 * @Date 8:44
 * @Version 1.0
 * @Description
 */

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>测试TIF转PDF</pre>
 *
 * @author aqiu 2020/11/3 8:28
 **/
public class TestTiff2PDF {

    private static final Logger log = LoggerFactory.getLogger(TestTiff2PDF.class);

    public static void convertImgToPDF(String imagePath, String pdfPath) throws Exception {

        //预先对图片大小判断
        Image img = Image.getInstance(imagePath);
        Rectangle rec = new Rectangle(img.getPlainWidth(),img.getPlainHeight());

        // 创建一个文档对象
        Document doc = new Document(rec, 0,0,0,0);
        //Document doc = new Document(PageSize.A3, 0,0,0,0);
        FileOutputStream out = new FileOutputStream(pdfPath);
        try {
            // 定义输出位置并把文档对象装入输出对象中
            PdfWriter.getInstance(doc, out);
            // 打开文档对象
            doc.open();
            img = Image.getInstance(imagePath);
            //添加图片到文档对象中
            doc.add(img);
        } catch (DocumentException | IOException e) {
            log.error(String.format("文件转换失败：%s", e.getMessage()), e);
        } finally {
            doc.close();
            out.close();
        }
    }

//    public static void main(String[] args) throws Exception {
//        convertImgToPDF("D:\\Users\\admin\\Downloads\\test1.tiff",
//                "D:\\Users\\admin\\Downloads\\test1.pdf");
//    }
        public static void main(String[] args) throws Exception {
            convertImgToPDF("D:\\Users\\admin\\Downloads\\test2.TIF",
                    "D:\\Users\\admin\\Downloads\\test2.PDF");
        }
}
