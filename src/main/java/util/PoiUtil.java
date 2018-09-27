package util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import exception.MyException;
import model.ResidentInfo;

/**
 * POI工具类
 * 
 * **/
public class PoiUtil {
	


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
	        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
	            return true;
	        } else {
	            return false;
	        }
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
