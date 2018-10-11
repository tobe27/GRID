package util;

import model.CustomerInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断Excel的版本,获取Workbook 
     * @param path
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(String path) throws Exception{
        Workbook wb = null;
        File file = new File(path); // 创建文件对象  
        FileInputStream in = new FileInputStream(file); // 文件流  
        ExcelUtil.checkExcelValid(file);

        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003  
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010  
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否是excel 
     * @throws Exception
     */
    public static void checkExcelValid(File file) throws Exception{
        if(!file.exists()){
            throw new Exception("文件不存在");
        }
        if(!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))){
            throw new Exception("文件不是Excel");
        }
    }


    @SuppressWarnings("deprecation")
    public static Object getValue(Cell cell) {
        if(cell==null){
            return null;
        }
        Object obj = null;
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }

    /**
     * 创建Excel，把数据读入workbook
     * @param sheetName
     * @param dataList
     * @param columnName
     * @return
     */
    public static Workbook output2Workbook(String sheetName, List<Map<String, String>> dataList, String[] columnName) {
        // 总列数
        int columnNum = columnName.length;
        // 创建excel
        Workbook workbook = new XSSFWorkbook();
        // 创建第一页并命名
        Sheet sheet = workbook.createSheet(sheetName);
        // 创建第一行
        Row row = sheet.createRow(0);
        // 创建两种单元格格式
        CellStyle cellStyle1 = workbook.createCellStyle();
        CellStyle cellStyle2 = workbook.createCellStyle();
        // 创建两种字体
        Font font1 = workbook.createFont();
        Font font2 = workbook.createFont();

        // 设置第一种字体样式(用于列名)
        font1.setBold(true);
        font1.setFontHeightInPoints((short) 10);
        font1.setColor(IndexedColors.BLACK.getIndex());

        // 设置第二种字体样式（用于值）
        font2.setFontHeightInPoints((short) 10);
        font2.setColor(IndexedColors.BLACK.getIndex());

        // 设置列名
        for (int i = 0; i < columnNum; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnName[i]);
            cell.setCellStyle(cellStyle1);
        }
        // 设置每行每列的值
        // 总行数
        int rowNum = dataList.size();
        for (int j = 1; j <= rowNum; j++) {
            // 创建第一行
            Row row1 = sheet.createRow(j);
            // 将一行数据每一列的值注入进去
            for (int k = 0; k < columnNum; k++) {
                Cell cell = row1.createCell(k);
                cell.setCellValue(dataList.get(j-1).get(columnName[k])); // map的值
                cell.setCellStyle(cellStyle2);
            }
        }
        return workbook;
    }

    /**
     * 将客户信息列表转成map
     * @param listInfo
     * @return
     */
    public static List<Map<String, String>> parseObject2ListMap(List<CustomerInfo> listInfo) {
        List<Map<String, String>> dataList = new ArrayList<>();
        for (CustomerInfo info : listInfo) {
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("客户ID",info.getCustomerId().toString());
            dataMap.put("姓名",info.getCustomerName());
            dataMap.put("性别",info.getSex() == 1 ? "男" : "女");
            dataMap.put("身份证号",info.getIdNumber());
            dataMap.put("婚姻状况",info.getMaritalStatus() == 0 ? "未婚" : "已婚");
            dataMap.put("最高学历",info.getEducationLevel());
            dataMap.put("民族",info.getNation());
            dataMap.put("生日",info.getBirthday());
            dataMap.put("籍贯",info.getNativePlace());
            dataMap.put("手机号",info.getPhoneNumber().toString());
            dataMap.put("管护人",info.getCustodian());
            dataMap.put("网格号",info.getGridCode());
            dataMap.put("户号",info.getHouseholdId().toString());
            dataMap.put("户籍地址",info.getNativeAddress());
            dataMap.put("常驻地址",info.getResidenceAddress());
            dataMap.put("政治面貌",info.getPoliticsStatus());
            dataMap.put("居住状况",info.getLivingSituation());
            dataMap.put("身体状况",info.getPhysicalCondition());
            dataMap.put("最高学位",info.getAcademicDegree());
            dataMap.put("年收入",info.getYearSalary().toString());
            dataMap.put("国籍",info.getNationality());
            dataMap.put("借款人性质",info.getBorrowerNature());
            dataMap.put("邮编",info.getPostcode().toString());
            dataMap.put("是否本行股东",info.getIsStockholder());
            dataMap.put("是否本行员工",info.getIsStaff());
            dataMap.put("是否企业主",info.getIsOwner());
            dataList.add(dataMap);
        }
        return dataList;
    }

    /**
     * 输出workbook
     * @param sheetName
     * @param listInfo
     * @return
     */
    public static Workbook output2Workbook(String sheetName, List<CustomerInfo> listInfo) {
        String[] columnName = {"客户ID", "姓名", "性别", "身份证号", "婚姻状况", "最高学历", "最高学位",
                "民族", "生日", "籍贯", "手机号", "管护人", "网格号", "户号", "政治面貌",
                "居住状况", "身体状况", "年收入", "国籍", "借款人性质", "邮编", "是否本行股东",
                "是否本行员工", "是否企业主", "户籍地址", "常驻地址"};
        return output2Workbook(sheetName, parseObject2ListMap(listInfo), columnName);
    }


}
