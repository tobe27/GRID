package controller;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import service.*;
import util.ExcelUtil;
import util.OcrUtil;
import util.PoiUtil;
import util.ValidUtil;

@RestController
@RequestMapping("file")
public class FileUploadController {
	@Autowired
	private ResidentInfoService residentInfoService;
	@Autowired
	private CustomerCreditService customerCreditService;
	@Autowired
	private CustomerWhiteListService customerWhiteListService;
	@Autowired
	private CustomerGreyListService customerGreyListService;
	@Autowired
	private CustomerBlackListService customerBlackListService;
	@Autowired
	private CustomerCreditDetailService customerCreditDetailService;
	@Autowired
    CustomerInfoService customerInfoService;

	private Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	
	
	
	/**
     * 调用此接口导入预授信明细表
     * @param
     * @return
     */
	@RequestMapping (value = "/importcreditdetail", method = RequestMethod.POST)
	public ResponseData importCreditDetail(HttpServletRequest request) {
		
		try {
			Map<String,Object> map=PoiUtil.uploadFile(request,"excel");
			
			if((boolean) map.get("flag")) {
				Map<String,Object> returnMap=new HashMap<>();
				List<Map<String, Object>> creditDetailList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),7,4,1);
				
				if(creditDetailList!=null && creditDetailList.size()>0) {
					returnMap.put("sum", customerCreditDetailService.insertByExcel(creditDetailList, map.get("gridCode").toString()));
				}else {
					returnMap.put("sum", 0);
				}
				return new ResponseData().success().data(returnMap);
			}
		} catch (Exception e) {
		e.printStackTrace();
		return new ResponseData().fail(e.getMessage());
		}
		return new ResponseData().success();
		
	}
	
	
	/**
     * 调用此接口导入整村授信名单库
     * @param
     * @return
     */
	@RequestMapping (value = "/importcreditlist", method = RequestMethod.POST)
	public  ResponseData importCreditList(HttpServletRequest request) {
		
		try {
			Map<String,Object> map=PoiUtil.uploadFile(request,"excel");
			
			if((boolean) map.get("flag")) {
				Map<String,Object> returnMap=new HashMap<>();
				List<Map<String, Object>> whirtList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),4,4,1);
				List<Map<String, Object>> greyList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),4,4,2);
				List<Map<String, Object>> blockList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),4,4,3);

				if(whirtList!=null && whirtList.size()>0) {
					returnMap.put("whitelist", customerWhiteListService.insertByExcel(whirtList, map.get("gridCode").toString()));
				}else {
					returnMap.put("whitelist", 0);
				}
				if(greyList!=null && greyList.size()>0) {
					returnMap.put("greylist", customerGreyListService.insertByExcel(greyList, map.get("gridCode").toString()));
				}else {
					returnMap.put("greylist", 0);
				}
				if(blockList!=null && blockList.size()>0) {
					returnMap.put("blockList", customerBlackListService.insertByExcel(blockList, map.get("gridCode").toString()));
				}else {
					returnMap.put("blockList", 0);
				}
				return new ResponseData().success().data(returnMap);
			}
		} catch (Exception e) {
		e.printStackTrace();
		return new ResponseData().fail(e.getMessage());
		}
		return new ResponseData().success();
	}
	
	
	
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public  ResponseData fileUpload(HttpServletRequest request) {
		try {
			Map<String,Object> map=PoiUtil.uploadFile(request,"excel");
			if((boolean) map.get("flag")) {
				List<ResidentInfo> list=PoiUtil.getExcel(map.get("path").toString()+File.separator+map.get("fileName").toString());
				if(list!=null && list.size()>0) {
					residentInfoService.batchSave(list);
					return new ResponseData().success().data(list.size());
				}
			}
		} catch (Exception e) {
		e.printStackTrace();
		return new ResponseData().fail(e.getMessage());
		}
		return new ResponseData().success();
	}
	
	/***
	 * 
	 * 
	 * 
	 * 
	 * **/
	
	@RequestMapping(value = "/uploadreviewresult", method = RequestMethod.POST)
	
	public ResponseData uploadReviewResult(HttpServletRequest request) {
		try {
		Map<String,Object> map=PoiUtil.uploadFile(request,"excel");
		int i=0;
		if((boolean) map.get("flag")) {
			
				List<Map<String,Object>> listMap=PoiUtil.getExcelByColumnNums(map.get("path").toString()+File.separator+map.get("fileName").toString(),6);
				if(listMap==null ||listMap.isEmpty()) {
				return 	new ResponseData().fail().message("模板数据为空");
				}else {
					
					for(Map<String,Object> result:listMap) {
						
						if(!result.containsKey("0")||("".equals(result.get("0").toString()))) {
							continue;
						}
						if(!result.containsKey("1")||("".equals(result.get("1").toString()))) {
							continue;
						}
						if(!result.containsKey("2")||("".equals(result.get("2").toString()))) {
							continue;
						}
						if(!result.containsKey("3")||("".equals(result.get("3").toString()))) {
							continue;
						}
						if(!result.containsKey("4")||(!"是".equals(result.get("4")))) {
							continue;
						}
						if(!result.containsKey("5")||("".equals(result.get("5")))) {
							continue;
						}
						CustomerCredit customerCredit=new CustomerCredit();
						customerCredit.setCreatedAt(System.currentTimeMillis());
						customerCredit.setIdNumber(result.get("3").toString());
						BigDecimal bd=new BigDecimal(result.get("5").toString());
						customerCredit.setPreCreditLimit(bd);
						customerCreditService.insertSelective(customerCredit);
						i++;
					}
					
				}
		}
		return new ResponseData().success().data(i);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseData().fail(e.getMessage());
			}
	
		
	}

	@RequestMapping(value ="/downImport", method = RequestMethod.GET)
	public void downImport(HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException{
		String fileName="户籍导入模板.xlsx";
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-download");
//		response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fileName, "UTF-8"));
        InputStream inputStream = null;
        OutputStream os = null;
        try {
            String path =request.getSession().getServletContext().getRealPath("/")
                    + "template"+File.separator+fileName;
            inputStream = new FileInputStream(new File(path));
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (Exception e) {
            logger.error("输入输出流错误",e);
        }finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                logger.error("关闭输出流异常",e);
            }

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("关闭输入流异常",e);
            }

        }
	}

    /**
     * 根据网格号下载客户信息
     * @param gridCode
     * @param response
     * @throws Exception
     */
	@RequestMapping(value = "/customer/{gridCode}", method = RequestMethod.GET)
    public void downloadCustomInfoByGridCode(@PathVariable String gridCode, HttpServletResponse response) throws Exception {
	    CustomerInfo info = new CustomerInfo();
        List<CustomerInfo> dataList;
	    if("0".equals(gridCode)) {
	        gridCode = "全部";
	        dataList = customerInfoService.listCustomers(info);
        } else {
	        info.setGridCode(gridCode);
	        dataList = customerInfoService.listCustomers(info);
        }

        String fileName = gridCode + "网格的客户信息.xlsx";
	    String sheetName = "客户信息";

	    // 设置导出格式
	    Workbook workbook = ExcelUtil.output2Workbook(sheetName, dataList);
	    response.setContentType("application/octet-stream;charset=utf-8");
	    response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso8859-1"));
	    response.setCharacterEncoding("UTF-8");
	    response.flushBuffer();
	    workbook.write(response.getOutputStream());
    }

}
