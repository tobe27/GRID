package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.ResidentInfo;
import model.ResponseData;
import service.ResidentInfoService;
import util.PoiUtil;

@RestController
@RequestMapping("file")
public class FileUploadController {
	@Autowired
	private ResidentInfoService residentInfoService;
	
	
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public  ResponseData fileUpload(HttpServletRequest request) {
		try {
			Map<String,Object> map=PoiUtil.uploadFile(request);
			if((boolean) map.get("flag")) {
				List<ResidentInfo> list=PoiUtil.getExcel(map.get("path").toString()+File.separator+map.get("fileName").toString());
				if(list!=null && list.size()>0) {
					residentInfoService.batchSave(list);
					return new ResponseData().success().code(200).data(list.size());
				}
			}
			
		} catch (Exception e) {
			
		e.printStackTrace();
		return new ResponseData().fail(e.getMessage());
		}
		return new ResponseData().code(200);
	}
	
	@RequestMapping(value ="/downImport", method = RequestMethod.GET)
	public void downImport(HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException{
		String fileName="户籍导入模板.xlsx";
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-download");
//		response.setContentType("applicatoin/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileName, "UTF-8"));
        try {
        	
            String path = request.getRealPath("/")
                    + "templet"+File.separator+fileName;
            InputStream inputStream = new FileInputStream(new File(path));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
 
            inputStream.close();
        } catch (Exception e) {
            
        }
	}

}
