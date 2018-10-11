package util;

import exception.MyException;
import model.ResidentInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * POI工具类
 *
 * **/
public class PoiUtil {

    public static boolean isImage(MultipartFile file) {
        String type=null;// 文件类型
        String fileName=file.getOriginalFilename();// 文件原名称
        // 判断文件类型
        type= fileName.contains(".") ?fileName.substring(fileName.lastIndexOf(".")+1):null;
        if (type!=null) {// 判断文件类型是否为空
            return "JPEG".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase());
        }
        return false;
    }

    /**
     * 上传图片
     * @param
     * @return map
     * @throws IOException
     * @throws IllegalStateException
     * @throws Exception
     */
    public static Map<String,Object> saveImage(MultipartFile file,String type,String fileName,String idNumber) throws IllegalStateException, IOException{
        if(file==null) {
            return null;
        }
        String pathType="";
        switch (type) {
            case "1":
                pathType="basicInfo";
                break;
            case "1.1":
                pathType="basicInfo";
                break;
            case "1.2":
                pathType="basicInfo";
                break;
            case "2":
                pathType="propertyInfo";
                break;
            case "3":
                pathType="manageInfo";
                break;
            case "4":
                pathType="loanInfo";
                break;
            case "4.1":
                pathType="loanInfo";
                break;
            case "4.2":
                pathType="loanInfo";
                break;
            case "5":
                pathType="pledgeInfo";
                break;
            case "6":
                pathType="otherInfo";
                break;

        }
        String path="";
        File tempFile = null;
        String saveFile="";
        if(isOSLinux()) {
            path="/data/attach/image/"+pathType+"/"+idNumber;
            tempFile =new File(path);
            if(!tempFile.exists()) {
                tempFile.setWritable(true, false);    //设置写权限，windows下不用此语句
                tempFile.mkdirs();
            }
            saveFile=path+"/"+fileName+file.getOriginalFilename();
        }else {
            path="D:\\gridFileUpload\\attach\\image\\"+pathType+"\\"+idNumber;
            tempFile =new File(path);
            if(!tempFile.exists()) {
                tempFile.mkdirs();
            }
            saveFile=path+"\\"+fileName+file.getOriginalFilename();
        }
        File storeFile = new File(saveFile);
        file.transferTo(storeFile);
        Map<String,Object> map=new HashMap<>();
        map.put("path", saveFile);
        return map;
    }


    /**
     * 将文件移至回收站
     * @param
     * @return map
     * @throws Exception
     */
    public static Map<String,Object> moveFileToRecycle(String filePath,String fileType){

        File file = new File(filePath);
        String path="";
        File tempFile;
        String destinationFile ="";
        if(isOSLinux()) {
            path="/data/recycle/"+fileType;
            tempFile =new File(path);
            if(!tempFile.exists()) {
                tempFile.setWritable(true, false);    //设置写权限，windows下不用此语句
                tempFile.mkdirs();
            }
            destinationFile = tempFile+"/"+file.getName();
            file.renameTo(new File(destinationFile));

        }else {
            path="D:\\gridFileUpload\\recycle\\"+fileType;
            tempFile =new File(path);
            if(!tempFile.exists()) {
                tempFile.mkdirs();
            }
            destinationFile = tempFile+"\\"+file.getName();
            file.renameTo(new File(destinationFile));
        }

        Map<String,Object> map=new HashMap<>();
        map.put("path", destinationFile);

        return map;

    }

    /**
     * 上传文件
     * @param request
     * @return map
     * @throws Exception
     */
    public static Map<String,Object> uploadFile(HttpServletRequest request,String pathType) throws Exception {
        String path="";
        Map<String,Object> map=new HashMap<>();
        boolean flag=false;
        if(request==null ) {
            map.put("flag", flag);
            map.put("msg", "参数有误");
            return map;
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(date);
        File tempFile = null;
        //如果是linux系统
        if(isOSLinux()) {
            path="/data/"+"/gridFileUpload/"+pathType+"/"+dateNowStr;
            tempFile =new File(path);
            if(!tempFile.exists()) {
                tempFile.setWritable(true, false);    //设置写权限，windows下不用此语句
                tempFile.mkdirs();
            }
        }else {
            path="D:\\gridFileUpload\\"+pathType+"\\"+dateNowStr;
            tempFile =new File(path);
            if(!tempFile.exists()) {
                tempFile.mkdirs();
            }
        }
        String fileName="";//上传后的文件名
        try{
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(tempFile);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");
            List<FileItem> list = upload.parseRequest(request);
            for(int i=0;i<list.size();i++){
                FileItem item=list.get(i);
                if(!item.isFormField()){
                    if(item.getName().length() > 0){
                        fileName = item.getName();
                        int num = (int) (Math.random() * 9000 + 1000);
                        fileName=num+"-"+fileName;
                        tempFile=new File(path+File.separator+fileName);
                        item.write(tempFile);
                        map.put("path", path);
                        map.put("fileName", fileName);
                        map.put("flag", true);
                    }
                }else {
                    map.put(item.getFieldName(), item.getString());
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            throw new MyException("上传附件异常");
        }
        return map;
    }

    /**
     * 解析excel获取户籍信息
     * @param path
     * @return map
     * @throws Exception
     */
    public static List<ResidentInfo> getExcel(String path) {
        List<ResidentInfo> result=new ArrayList<>();
        try {
            Workbook workbook = ExcelUtil.getWorkbook(path);
            Sheet sheet = workbook.getSheetAt(0);
            // 为跳过第一行目录设置count  
            int count = 0;
            // DecimalFormat decimalFormat = new DecimalFormat("##0");
            for (Row row : sheet) {
                // 跳过第一和第二行的目录
                if(count < 1 ) {
                    count++;
                    continue;
                }
                if(row.getCell(0)==null||"".equals(row.getCell(0).toString())){
                    return result;
                }
                ResidentInfo residentInfo=new ResidentInfo();
                residentInfo.setResidentName(ExcelUtil.getValue(row.getCell(0)).toString());
                residentInfo.setSex(ExcelUtil.getValue(row.getCell(1)).toString());
                residentInfo.setNation(ExcelUtil.getValue(row.getCell(2)).toString());
                residentInfo.setBirthdate(ExcelUtil.getValue(row.getCell(3)).toString());
                residentInfo.setIdNumber(ExcelUtil.getValue(row.getCell(4)).toString());
                if(ExcelUtil.getValue(row.getCell(5)) !=null) {
                    residentInfo.setAddress(ExcelUtil.getValue(row.getCell(5)).toString());
                }
                if(ExcelUtil.getValue(row.getCell(6))!=null) {
                    residentInfo.setHouseholdType(ExcelUtil.getValue(row.getCell(6)).toString());
                }
                if(ExcelUtil.getValue(row.getCell(7))!=null) {
                    residentInfo.setRelationship(ExcelUtil.getValue(row.getCell(7)).toString());
                }
                if(ExcelUtil.getValue(row.getCell(9))!=null) {
                    residentInfo.setContact(ExcelUtil.getValue(row.getCell(9)).toString());
                }
                if(ExcelUtil.getValue(row.getCell(10))!=null) {
                    residentInfo.setIsInList(ExcelUtil.getValue(row.getCell(10)).toString());
                }
                if(ExcelUtil.getValue(row.getCell(11))!=null) {
                    residentInfo.setRemark(ExcelUtil.getValue(row.getCell(11)).toString());
                }

                residentInfo.setHouseholdId(Long.valueOf(String.valueOf(ExcelUtil.getValue(row.getCell(8)))));

                residentInfo.setCreatedAt(System.currentTimeMillis());
                residentInfo.setUpdatedAt(System.currentTimeMillis());
                result.add(residentInfo);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析excel的某些列的数据 
     * @param path,column
     * @return map
     * @throws Exception
     */
    public static List<Map<String,Object>> getExcelByColumnNums(String path,int num) throws Exception{
        List<Map<String,Object>> listMap=new ArrayList<>();
        Workbook workbook = ExcelUtil.getWorkbook(path);
        Sheet sheet = workbook.getSheetAt(0);
        // 为跳过第一行目录设置count
        int count = 0;
        // DecimalFormat decimalFormat = new DecimalFormat("##0");
        for (Row row : sheet) {
            // 跳过第一和第二行的目录
            if(count < 1 ) {
                count++;
                continue;
            }
            if(row.getCell(0)==null||"".equals(row.getCell(0).toString())){
                return listMap;
            }
            Map<String,Object> map=new HashMap<>();
            for(int i=0;i<num;i++) {
                map.put(i+"", ExcelUtil.getValue(row.getCell(i)));
            }
            listMap.add(map);
        }
        return listMap;
    }


    /**
     * 从第*行开始解析*列的数据
     * @param path,列数：column，读取起始行rowNum,读取的sheet页数 sheetNum
     * @return map
     * @throws Exception
     */
    public static List<Map<String,Object>> getExcelByColumnNumsAndRowNum(String path,int column,int rowNum,int sheetNum) throws Exception{
        List<Map<String,Object>> listMap=new ArrayList<>();
        Workbook workbook = ExcelUtil.getWorkbook(path);
        Sheet sheet = workbook.getSheetAt(sheetNum-1);
        // 为跳过第一行目录设置count
        int count = 0;
        // DecimalFormat decimalFormat = new DecimalFormat("##0");

        for (Row row : sheet) {
            // 跳过第一和第二行的目录
            if(count < rowNum-1 ) {
                count++;
                continue;
            }

            Map<String,Object> map=new HashMap<>();
            for(int i=0;i<column;i++) {
                map.put(i+"", ExcelUtil.getValue(row.getCell(i)));
            }
            listMap.add(map);
        }

        return listMap;
    }


    public static boolean isOSLinux() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        return os != null && os.toLowerCase().contains("linux");
    }
    public static boolean isValidLong(String str){
        try{
            long _v = Long.parseLong(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }


}
