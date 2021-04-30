import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * itextpdf显示中文需要字体文件支持
 */
public class pdfApp {

    public static void main(String[] args) {
//        String pdfTemplatePath = "C:\\Users\\zhangzijian\\Desktop\\孵化基地\\policy_apply_book_export_template_1_1_1.pdf";
//        String targetFilePath = "C:\\Users\\zhangzijian\\Desktop\\孵化基地\\生成文件.pdf";
//        File file = new File("C:\\Users\\zhangzijian\\Desktop\\孵化基地\\FuHuaJiDi.json");

//        String pdfTemplatePath = "C:\\Users\\zhangzijian\\Desktop\\工作站认定\\policy_apply_book_export_template_1_1_2.pdf";
//        String targetFilePath = "C:\\Users\\zhangzijian\\Desktop\\工作站认定\\生成文件.pdf";
//        File file = new File("C:\\Users\\zhangzijian\\Desktop\\工作站认定\\gongZhuoZhanRenDing.json");

//        String pdfTemplatePath = "C:\\Users\\zhangzijian\\Desktop\\共性技术\\policy_apply_book_export_template_3_6_11.pdf";
//        String targetFilePath = "C:\\Users\\zhangzijian\\Desktop\\共性技术\\生成文件.pdf";
//        File file = new File("C:\\Users\\zhangzijian\\Desktop\\共性技术\\GongXingJiShu.json");

//        String pdfTemplatePath = "C:\\Users\\zhangzijian\\Desktop\\个性技术\\policy_apply_book_export_template_3_7_11.pdf";
//        String targetFilePath = "C:\\Users\\zhangzijian\\Desktop\\个性技术\\生成文件.pdf";
//        File file = new File("C:\\Users\\zhangzijian\\Desktop\\个性技术\\GeXingJiShu.json");

//        String pdfTemplatePath = "C:\\Users\\zhangzijian\\Desktop\\中试平台认定\\policy_apply_book_export_template_1_1_3.pdf";
//        String targetFilePath = "C:\\Users\\zhangzijian\\Desktop\\中试平台认定\\生成文件.pdf";
//        File file = new File("C:\\Users\\zhangzijian\\Desktop\\中试平台认定\\ZhongShiPingTaiRenDing.json");

//        String pdfTemplatePath = "C:\\Users\\zhangzijian\\Desktop\\中试项目立项\\policy_apply_book_export_template_1_1_4.pdf";
//        String targetFilePath = "C:\\Users\\zhangzijian\\Desktop\\中试项目立项\\生成文件.pdf";
//        File file = new File("C:\\Users\\zhangzijian\\Desktop\\中试项目立项\\ZhongShiXiangMu.json");

        String pdfTemplatePath = "C:\\Users\\zhangzijian\\Desktop\\工业化生产项目\\policy_apply_book_export_template_1_1_5.pdf";
        String targetFilePath = "C:\\Users\\zhangzijian\\Desktop\\工业化生产项目\\生成文件.pdf";
        File file = new File("C:\\Users\\zhangzijian\\Desktop\\工业化生产项目\\GongYeHuaShengChanXiangMu.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        Map<String, String> map = null;
        try {
            map = objectMapper.readValue(file, new TypeReference<Map<String, String>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            PDFGenerateUtil.pdfOutFile(map, null, pdfTemplatePath, targetFilePath);
            System.out.println("生成pdf成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成pdf失败");
        }

    }
}
