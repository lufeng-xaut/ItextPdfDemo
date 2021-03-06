//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.AcroFields;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//import com.itextpdf.text.pdf.AcroFields.Item;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.*;
//
//public class PDFUtils {
//
//
//    /**
//     * @param fields
//     * @param data
//     * @throws IOException
//     * @throws DocumentException
//     */
//    private static void fillData(AcroFields fields, Map<String, String> data) throws IOException, DocumentException {
//        List<String> keys = new ArrayList<String>();
//        Map<String, Item> formFields = fields.getFields();
//        for (String key : data.keySet()) {
//            if(formFields.containsKey(key)){
//                String value = data.get(key);
//                fields.setField(key, value,"true"); // 为字段赋值,注意字段名称是区分大小写的
//                keys.add(key);
//            }
//        } //fields.setGenerateAppearances(true);
//
//        Iterator<String> itemsKey = formFields.keySet().iterator();
//        while(itemsKey.hasNext()){
//            String itemKey = itemsKey.next();
//           /* if (itemKey.contains("yes")){
//
//            }*/
//            if(!keys.contains(itemKey)){
//                fields.setField(itemKey, " ");
//
//            }
//        }
//    }
//
//    /**
//     * @param templatePdfPath
//     *            模板pdf路径
//     * @param generatePdfPath
//     *            生成pdf路径
//     * @param data
//     *            数据
//     */
//    public static String generateChinesePDF(String templatePdfPath, String generatePdfPath, Map<String, String> data) {
//        OutputStream fos = null;
//        ByteArrayOutputStream bos = null;
//        try {
//            PdfReader reader = new PdfReader(templatePdfPath);
//            bos = new ByteArrayOutputStream();
//            /* 将要生成的目标PDF文件名称 */
//            PdfStamper ps = new PdfStamper(reader, bos);
//            /* 使用中文字体 */
//            /* 使用中文字体 */
//            // BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/seguisym.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);         
//            //BaseFont bf = BaseFont.createFont("/Users/huangxiaogen/Downloads/seguisym.ttf", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//
////            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
//            ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
////            fontList.add(bf);
//            /* 取出报表模板中的所有字段 */
//            AcroFields fields = ps.getAcroFields();
//            fields.setGenerateAppearances(true);
//            fields.setSubstitutionFonts(fontList);
//            fillData(fields, data);
//            /* 必须要调用这个，否则文档不会生成的  如果为false那么生成的PDF文件还能编辑，一定要设为true*/
//            ps.setFormFlattening(true);
//            ps.close();
//            fos = new FileOutputStream(generatePdfPath);
//            fos.write(bos.toByteArray());
//            fos.flush();
//            return generatePdfPath;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            if (bos != null) {
//                try {
//                    bos.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//    }
//
//
//
//    public static void main(String[] args) {
//        Map<String, String> data = new HashMap<String, String>();
//        Map<String, String> checkData = new HashMap<String, String>();
//        //key为pdf模板的form表单的名字，value为需要填充的值
//      /*  data.put("title", "在线医院");
//        data.put("case", "123456789");
//        data.put("date", "2018.12.07");
//        data.put("name", "gitbook");
//        data.put("sex", "男");
//        data.put("age", "29");
//        data.put("phone", "13711645814");
//        data.put("office", "内科");
//        data.put("cert", "身痒找打");
//        data.put("drug", "1、奥美拉唑肠溶胶囊             0.25g10粒×2板 ");
//        data.put("dose", "×2盒");
//        data.put("cons", "用法用量：口服 一日两次 一次2粒");
//        data.put("tips", "温馨提示");
//        data.put("desc", "尽量呆在通风较好的地方，保持空气流通，有利于病情康复。尽量呆在通风较好的地方");*/
////        data.put("check_name", "张三");
////        data.put("date", "2019-01-04");
////
////        data.put("self_way","true");//复选框问题解决
//
//        data.put("fill_1","1");
//        data.put("fill_2","2");
//        data.put("fill_3","3");
//        data.put("fill_4","4");
//        data.put("fill_5","5");
//        data.put("fill_6","6");
//        data.put("Box1","true");
//        data.put("Box2","0");
//        data.put("Box3","true");
//
//
//        String templatePath = "C:\\Users\\zhangzijian\\Desktop\\FuHuaJiDi_ApplyBook_2021_01.pdf";
//        String generatePath = "C:\\Users\\zhangzijian\\Desktop\\FuHuaJiDi_ApplyBook_2021_data.pdf";
//
//
//
//        generateChinesePDF(templatePath,
//                generatePath, data );
//
//
//
//
//    }
//
//
//}
