package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import model.CustomerBlackList;
import model.CustomerCreditDetail;
import model.ResponseData;
import service.CustomerCreditDetailService;
import util.JwtUtil;

@RestController
@RequestMapping("customer")
public class CustomerCreditDetailController {
    @Autowired
    private CustomerCreditDetailService customerCreditDetailService;
    
    
    /**
     * 调用此接口批量新增面谈面签信息
     * @param CustomerCreditDetail
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/creditdetail/addlist",method = RequestMethod.POST)
    public ResponseData addCustomerCreditDetailList(@RequestBody List<CustomerCreditDetail> list,HttpServletRequest request) throws Exception{
    	String token =request.getHeader("Authorization");
    	 Long accountId = Long.valueOf(JwtUtil.parseToken(token).get("accountId").toString());
    	 String orgName=JwtUtil.parseToken(token).get("orgName").toString();
    	 String orgCode=JwtUtil.parseToken(token).get("orgCode").toString();
    	 String accountName=JwtUtil.parseToken(token).get("realName").toString();
    	 Map<String,Object> map=new HashMap<>();
    	 map.put("accountId", accountId);
    	 map.put("orgName", orgName);
    	 map.put("orgCode", orgCode);
    	 map.put("accountName", accountName);		
		 return new ResponseData().success().data(customerCreditDetailService.batchSave(list, map));


    }

    
    
    

    /**
     * 调用此接口新增面谈面签信息
     * @param CustomerCreditDetail
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/creditdetail",method = RequestMethod.POST)
    public ResponseData addCustomerCreditDetail(@RequestBody CustomerCreditDetail customerCreditDetail) {
        try {
            customerCreditDetailService.insertSelective(customerCreditDetail);
            return new ResponseData().success().data(customerCreditDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData().fail(e.getMessage());
        }


    }


    /**
     * 调用此接口修改面谈面签信息
     * @param CustomerCreditDetail
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/creditdetail/{id}",method = RequestMethod.PUT)
    public ResponseData updateCustomerCreditDetail(@RequestBody CustomerCreditDetail customerCreditDetail) {
        try {
            customerCreditDetailService.updateByPrimaryKeySelective(customerCreditDetail);
            return new ResponseData().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData().fail(e.getMessage());
        }


    }


    /**
     * 调用此接口删除面谈面签信息
     * @param id	
     * @return
     */
    @RequestMapping(value = "/creditdetail/{id}", method = RequestMethod.DELETE)
    public ResponseData deleteCustomerCreditDetail(@PathVariable long id) {
        try {
            customerCreditDetailService.deleteByPrimaryKey(id);
            return new ResponseData().success();
        } catch (Exception e) {
            return new ResponseData().fail(e.getMessage());
        }
    }
     /**
     * 分页和条件显示面谈面签列表
     * @param pageNo,PageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/creditdetail/list", method = RequestMethod.GET)
    public ResponseData getFaceTackListByPage(@RequestParam Map<String,Object>  map) throws Exception {
      
    	PageInfo<CustomerCreditDetail> pageInfo ;
        List<CustomerCreditDetail> list=  customerCreditDetailService.getListByPage(map);
        pageInfo=new PageInfo<>(list);

        return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());

    }

    
    /**
     * 查看单条面谈面签记录信息
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/creditdetail/{id}", method = RequestMethod.GET)
    public ResponseData getFaceTack(@PathVariable long id) throws Exception {
        CustomerCreditDetail record=  customerCreditDetailService.selectByPrimaryKey(id);
      return new ResponseData().success().data(record);

    }

    /***
	 * 查询某个客户某次面谈面签的征信报告
	 * @param creditDetailId  面谈面签ID
     * @throws Exception 
	 * 
	 * */
	 @RequestMapping (value = "/creditdetail/report/{creditDetailId}", method = RequestMethod.GET)
	    public ResponseData getCreditReportHtmlCode(@PathVariable long creditDetailId) throws Exception {
	        return new ResponseData().success().data(customerCreditDetailService.getCreditReportHtmlCode(creditDetailId));

	    }
	
    
    
    

}
