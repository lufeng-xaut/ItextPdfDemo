import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: 大强
 * @Date: 2019/5/29 16:23
 */
public class PDFGenerateUtil {

    /**
     * 利用模板生成pdf
     *
     * @param dateMap      pdf要填充的数据
     * @param imgMap       pdf要填充的图片
     * @param templatePath pdf的模板路径
     * @param targetPath   pdf文件路径
     * @return
     * @throws Exception
     */
    public static void pdfOutFile(Map<String, String> dateMap, Map<String, String> imgMap, String templatePath, String targetPath) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = PDFGenerateUtil.pdfout(dateMap, imgMap, templatePath);
        // 生成文件
        File file = new File(targetPath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.close();
        byteArrayOutputStream.close();
    }

    /**
     * 利用模板生成pdf
     *
     * @param dateMap      pdf要填充的数据
     * @param imgMap       pdf要填充的图片
     * @param templatePath pdf的模板路径
     * @return
     * @throws Exception
     */
    public static ByteArrayOutputStream pdfout(Map<String, String> dateMap, Map<String, String> imgMap, String templatePath) throws Exception {
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;

        // 判断操作系统选择字体文件
        String fontPath = null;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("win")) {
            fontPath = "c:/windows/fonts/simsun.ttc,1";
        } else {
            fontPath = "/usr/share/fonts/chinese/simsun.ttc,1";
        }
        // 使用系统字体
        BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        // 使用iTextAsian.jar中的字体
//        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        // 读取pdf模板
        reader = new PdfReader(templatePath);
        bos = new ByteArrayOutputStream();
        stamper = new PdfStamper(reader, bos);
        AcroFields form = stamper.getAcroFields();
        //文字类的内容处理
        form.addSubstitutionFont(bf);
        form.setGenerateAppearances(true);
        for (String key : dateMap.keySet()) {
            String value = dateMap.get(key);
            form.setField(key, value, true);
        }
        //图片类的内容处理
        if (null != imgMap) {
            for (String key : imgMap.keySet()) {
                String value = imgMap.get(key);
                String imgpath = value;
                int pageNo = form.getFieldPositions(key).get(0).page;
                Rectangle signRect = form.getFieldPositions(key).get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                //根据路径读取图片
                Image image = Image.getInstance(imgpath);
                //获取图片页面
                PdfContentByte under = stamper.getOverContent(pageNo);
                //图片大小自适应
                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                //添加图片
                image.setAbsolutePosition(x, y);
                under.addImage(image);
            }
        }
        // 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
        stamper.setFormFlattening(true);
        stamper.close();
        return bos;
    }

    /**
     * 合并PDF文件
     *
     * @param bosList        pdfFilenames为文件路径数组
     * @param targetFilename targetFileName为目标pdf路径
     * @throws Exception
     */
    public static void combinePdf(List<ByteArrayOutputStream> bosList, String targetFilename) throws Exception {
        PdfReader reader = null;
        Document doc = new Document();
        PdfCopy pdfCopy = new PdfCopy(doc, new FileOutputStream(targetFilename));
        int pageCount = 0;
        doc.open();
        for (ByteArrayOutputStream bos : bosList) {
            reader = new PdfReader(bos.toByteArray());
            pageCount = reader.getNumberOfPages();
            for (int j = 1; j <= pageCount; ++j) {
                pdfCopy.addPage(pdfCopy.getImportedPage(reader, j));
            }
        }
        doc.close();
    }
}
