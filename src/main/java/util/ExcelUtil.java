package util;

import model.CustomerInfo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

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
     * HSSFWorkbook-xls
     * XSSFWorkbook-xlsx
     * @param sheetName
     * @param dataList
     * @param columnName
     * @return
     */
    public static Workbook output2Workbook(String sheetName, List<Map<String, String>> dataList, String[] columnName) {
        // 总列数
        int columnNum = columnName.length;
        // 创建excel,HSSFWorkbook生成xls，XSSFWorkbook-xlsx
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建第一页并命名
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 创建第一行
        HSSFRow row = sheet.createRow(0);
        // 创建两种单元格格式
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
        // 创建两种字体
        HSSFFont font1 = workbook.createFont();
        HSSFFont font2 = workbook.createFont();

        // 设置第一种字体样式(用于列名)
        font1.setBold(true);
        font1.setFontHeightInPoints((short) 10);
        font1.setColor(IndexedColors.BLACK.getIndex());

        // 设置第二种字体样式（用于值）
        font2.setFontHeightInPoints((short) 10);
        font2.setColor(IndexedColors.BLACK.getIndex());

        // 设置列名
        for (int i = 0; i < columnNum; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(columnName[i]);
            cell.setCellStyle(cellStyle1);
        }
        // 设置每行每列的值
        // 总行数
        int rowNum = dataList.size();
        for (int j = 1; j <= rowNum; j++) {
            // 创建第一行
            HSSFRow row1 = sheet.createRow(j);
            // 将一行数据每一列的值注入进去
            for (int k = 0; k < columnNum; k++) {
                HSSFCell cell = row1.createCell(k);
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
            dataMap.put("客户编号",info.getCustomerId().toString());
            dataMap.put("信贷编号", info.getCreditId());
            dataMap.put("姓名",info.getCustomerName());
            dataMap.put("证件类型", info.getIdType());
            dataMap.put("证件号码",info.getIdNumber());
            dataMap.put("性别",info.getSex());
            dataMap.put("出生日期",info.getBirthday());
            dataMap.put("手机号码",info.getPhoneNumber());
            dataMap.put("身体状况",info.getPhysicalCondition());
            dataMap.put("最高学历",info.getEducationLevel());
            dataMap.put("国籍",info.getNationality());
            dataMap.put("民族",ValidUtil.isEmpty(info.getNation()) ? "无" : info.getNation());
            dataMap.put("政治面貌", info.getPoliticsStatus());
            dataMap.put("婚姻状况",info.getMaritalStatus());

            dataMap.put("职业",info.getCareer());
            dataMap.put("工作年限",ValidUtil.isEmpty(info.getWorkYear()) ? "无" : info.getWorkYear().toString());
            dataMap.put("单位名称",ValidUtil.isEmpty(info.getCompanyName()) ? "无" : info.getCompanyName());
            dataMap.put("单位地址",ValidUtil.isEmpty(info.getCompanyAddress()) ? "无" : info.getCompanyAddress());

            dataMap.put("是否农户",info.getIsFarmer());
            dataMap.put("有无县城购房",info.getIsBuyHouse());
            dataMap.put("小区名称",ValidUtil.isEmpty(info.getCellName()) ? "无" : info.getCellName());
            dataMap.put("小区地址",ValidUtil.isEmpty(info.getCellAddress()) ? "无" : info.getCellAddress());

            dataMap.put("户号",info.getHouseholdId());
            dataMap.put("居住状况",info.getLivingSituation());
            dataMap.put("户籍地址",info.getNativeAddress());
            dataMap.put("居住地址",info.getResidenceAddress());
            dataMap.put("居住地邮编",info.getPostcode());
            dataMap.put("是否本行股东",info.getIsStockholder());
            dataMap.put("是否本行员工",info.getIsStaff());
            dataMap.put("是否企业主",info.getIsOwner());

            dataMap.put("网格编号",info.getGridCode());
            dataMap.put("网格名称",info.getGridName());
            dataMap.put("调查人(A角)",info.getInvestigator1());
            dataMap.put("调查人(B角)",info.getInvestigator2());
            dataMap.put("登记人",info.getRegistrant());
            dataMap.put("登记机构",info.getRegisterOrg());
            dataMap.put("登记日期",DateConvertUtil.convert2String(info.getCreatedAt()));
            dataMap.put("更新日期",DateConvertUtil.convert2String(info.getUpdatedAt()));

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
        String[] columnName = {"客户编号", "信贷编号", "姓名", "证件类型", "证件号码", "性别", "出生日期",
                "手机号码", "身体状况", "最高学历", "国籍", "民族", "政治面貌", "婚姻状况",
                "职业", "工作年限", "单位名称", "单位地址",
                "是否农户","有无县城购房", "小区名称", "小区地址",
                "户号", "居住状况", "户籍地址", "居住地址", "居住地邮编", "是否本行股东", "是否本行员工", "是否企业主",
                "网格编号", "网格名称", "调查人(A角)", "调查人(B角)", "登记人", "登记机构", "登记日期", "更新日期"};
        return output2Workbook(sheetName, parseObject2ListMap(listInfo), columnName);
    }


}
