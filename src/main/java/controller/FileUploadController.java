package controller;

import model.CustomerCredit;
import model.CustomerInfo;
import model.ResidentInfo;
import model.ResponseData;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.*;
import util.ExcelUtil;
import util.PoiUtil;
import util.ValidUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    CustomerCreditAdviserService customerCreditAdviserService;
    @Autowired
    private CustomerPoorInfoService customerPoorInfoService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 调用此接口导入客户基本信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/customer/info", method = RequestMethod.POST)
    public ResponseData importCustomerInfo(HttpServletRequest request) throws Exception {

        // 上传EXCEL
        Map<String, Object> map = PoiUtil.uploadFile(request, "excel");
        if (ValidUtil.isEmpty(map.get("gridCode")) || ValidUtil.isEmpty(map.get("gridName"))
                || ValidUtil.isEmpty(map.get("registrant")) || ValidUtil.isEmpty(map.get("registerOrg"))) {
            return new ResponseData().fail("请求参数不全");
        }
        int count = 0;
        StringBuilder message = new StringBuilder();
        List<CustomerInfo> infoList;
        if ((boolean)map.get("flag")) {
            // 从EXCEL获取信息列表
            infoList = PoiUtil.getCustomerInfoByExcel(map.get("path").toString()+File.separator+map.get("fileName").toString(), 0,
                    // 转码。Tomcat默认编码方式为ISO-8859-1，iso-8859-1是不支持中文的，需转换为UTF-8
                    new String(map.get("gridCode").toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                    new String(map.get("gridName").toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                    new String(map.get("registrant").toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                    new String(map.get("registerOrg").toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

            // 循环导入到数据库
            if (ValidUtil.isNotEmpty(infoList)) {
                for (CustomerInfo info : infoList) {
                    if (ValidUtil.isEmpty(info.getCustomerName()) || ValidUtil.isEmpty(info.getIdNumber())) {
                        return new ResponseData().fail("姓名与身份证号不能为空！");
                    }
                    if (ValidUtil.isNotLength(info.getIdNumber(), 18)) {
                        message.append(info.getIdNumber()).append(":身份证号不正确!");
                        continue;
                    }
                    if (customerInfoService.getCustomerByIdNumber(info.getIdNumber()) != null) {
                        message.append(info.getIdNumber()).append(":身份证号已存在!");
                        continue;
                    }


                    try {
                        customerInfoService.insertByExcel(info);
                        count ++;
                    } catch (Exception e) {
                        logger.info("导入异常："+ info + e.getMessage());
                        message.append(info.getIdNumber()).append(":导入异常,请调整格式!");
                    }

                }
            }

        } else {
            return new ResponseData().fail("文件导入异常");
        }
        int total = (infoList == null ? 0 : infoList.size());
        String msg = (total - count) == 0 ? "" : ",失败原因:" + message;
        return new ResponseData().success("共"+ total + "条数据,导入成功" + count + "条,导入失败"+ (total - count) +"条"+ msg);

    }


    /**
     * 调用此接口导入预授信明细表
     * @param
     * @return
     */
    @RequestMapping (value = "/credit/detail", method = RequestMethod.POST)
    public ResponseData importCreditDetail(HttpServletRequest request) {

        try {
            Map<String,Object> map=PoiUtil.uploadFile(request,"excel");

            if((boolean) map.get("flag")) {
                Map<String,Object> returnMap=new HashMap<>();
                List<Map<String, Object>> creditDetailList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),28,4,1);

                if(creditDetailList!=null && creditDetailList.size()>0 &&  map.containsKey("gridCode")) {
                	if(!map.containsKey("accountId")|| !map.containsKey("orgCode")|| !map.containsKey("orgName")|| !map.containsKey("accountName")) {
                		 return new ResponseData().fail("参数有误");
                	}
                    returnMap.put("sum", customerCreditDetailService.insertByExcel(creditDetailList,map));
                }
                returnMap.put("failCount", 0);
                returnMap.put("successCount", 0);
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
     *//*
    @RequestMapping (value = "/credit/list", method = RequestMethod.POST)
    public  ResponseData importCreditList(HttpServletRequest request) throws Exception {

        Map<String,Object> map=PoiUtil.uploadFile(request,"excel");

        if((boolean) map.get("flag")) {
            Map<String,Object> returnMap=new HashMap<>();
            List<Map<String, Object>> whirtList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),4,4,1);
            List<Map<String, Object>> greyList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),4,4,2);
            List<Map<String, Object>> blockList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),4,4,3);
            //金融顾问咨询意见记录
            List<Map<String,Object>> customerCreditAdviserList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),11,4,4);

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
            if(customerCreditAdviserList!=null && customerCreditAdviserList.size()>0) {
                returnMap.put("customerCreditAdviserList", customerCreditAdviserService.insertByExcel(customerCreditAdviserList, map.get("gridCode").toString()));
            }else {
                returnMap.put("customerCreditAdviserList", 0);
            }
            return new ResponseData().success().data(returnMap);
        }

        return new ResponseData().success();
    }*/

    /**
     * 调用此接口导入贫困户名单
     * @param
     * @return
     */
    @RequestMapping (value = "/customer/poor/list", method = RequestMethod.POST)
    public  ResponseData importCustomerPoorList(HttpServletRequest request)throws Exception {
        Map<String,Object> map=PoiUtil.uploadFile(request,"excel");

        if((boolean) map.get("flag")) {
            Map<String,Object> returnMap=new HashMap<>();

            List<Map<String, Object>> poorList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),29,4,1);

            if(poorList!=null && poorList.size()>0) {
                return new ResponseData().success().data(customerPoorInfoService.insertFromExcel(poorList));
            }
        }
        return new ResponseData().success();
    }


    /**
     * 批量导入户籍信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/resident", method = RequestMethod.POST)
    public  ResponseData fileUpload(HttpServletRequest request) throws Exception {


        Map<String,Object> map=PoiUtil.uploadFile(request,"excel");
        if (ValidUtil.isEmpty(map.get("gridCode"))) {
            return new ResponseData().fail("请先选择网格");
        }
        if (ValidUtil.isEmpty(map.get("realName"))) {
            return new ResponseData().fail("参数缺失");
        }
        if (ValidUtil.isEmpty(map.get("orgName"))) {
            return new ResponseData().fail("参数缺失");
        }	
        int successCount=0;
        int failCount=0;
        if((boolean) map.get("flag")) {
            List<ResidentInfo> list=PoiUtil.getExcel(
                    map.get("path").toString()+File.separator+map.get("fileName").toString(), map.get("gridCode").toString());
            if(list!=null && list.size()>0) {
               for(ResidentInfo info:list) {
            	   try {
					residentInfoService.insertSelective(info, request);
					   successCount++;
				} catch (Exception e) {
					failCount++;
					continue;
				}
               }
               Map<String,Object> returnMap=new HashMap<>();
               returnMap.put("successCount", successCount);
               returnMap.put("failCount", failCount);
                return new ResponseData().success().data(returnMap);
            }
        }
        return new ResponseData().fail("导入失败");
    }

    /**
     *复议导入
     * @param request
     */
    @RequestMapping(value = "/review/result", method = RequestMethod.POST)
    public ResponseData uploadReviewResult(HttpServletRequest request) throws Exception {
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
    }

    /**
     * 户籍导入模板下载
     * @param response
     * @param request
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value ="/resident", method = RequestMethod.GET)
    public void downImport(HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException{
        String fileName="户籍导入模板.xls";
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
    public void downloadCustomInfoByGridCode(@PathVariable String gridCode, String gridName, Long accountId, HttpServletResponse response) throws Exception {
        if (ValidUtil.isEmpty(gridName) || ValidUtil.isEmpty(accountId)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print("{\"code\":400,\"message\":\"缺少必要参数！\"}");
            return;
        }
        List<CustomerInfo> dataList;
        CustomerInfo info = new CustomerInfo();
        info.setAccountId(accountId);
        info.setStatus(5);
        if("0".equals(gridCode)) {
            gridCode = "";
            gridName = "全部";
            dataList = customerInfoService.listCustomersByAccountId(info);
        } else {
            info.setGridCode(gridCode);
            dataList = customerInfoService.listCustomersByAccountId(info);
        }

        String fileName = gridName +gridCode+ "网格的客户信息.xlsx";
        String sheetName = "客户信息";
        if (dataList == null || dataList.size() == 0) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print("{\"code\":204,\"message\":\"不存在客户信息\"}");
            return;
        }

        Workbook workbook = ExcelUtil.output2Workbook(sheetName, dataList);
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso8859-1"));
        response.setCharacterEncoding("UTF-8");
        response.flushBuffer();
        workbook.write(response.getOutputStream());

    }

}
