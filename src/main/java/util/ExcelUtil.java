package util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
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
}
