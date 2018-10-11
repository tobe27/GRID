package controller;

import java.util.List;
import java.util.Map;

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

@RestController
@RequestMapping("customer")
public class CustomerCreditDetailController {
	@Autowired
	private CustomerCreditDetailService customerCreditDetailService;
	
	/**
     * 调用此接口新授信详情信息
     * @param CustomerCreditDetail 
     * @return
     * @throws Exception
     */
	
	@RequestMapping(value = "/creditdetail",method = RequestMethod.POST)
	 public ResponseData addCustomerCreditDetail( CustomerCreditDetail customerCreditDetail) {
		try {
			customerCreditDetailService.insertSelective(customerCreditDetail);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	
	 /**
	   * 调用此接口修改授信详情信息
	   * @param CustomerCreditDetail
	   * @return
	   * @throws Exception
	   */
	
	@RequestMapping(value = "/creditdetail/{id}",method = RequestMethod.PUT)
	 public ResponseData updateCustomerCreditDetail(CustomerCreditDetail customerCreditDetail) {
		try {
			customerCreditDetailService.updateByPrimaryKeySelective(customerCreditDetail);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	
	/**
     * 调用此接口删除授信详情信息
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
     * 分页和条件显示角色列表
     * @param pageNo,PageSize
     * @return
     * @throws Exception
     */
  @RequestMapping(value = "/creditdetail/list", method = RequestMethod.GET)
  public ResponseData getListByPage(@RequestParam Map<String,Object>  map) {
	  PageInfo<CustomerCreditDetail> pageInfo ;
	  try {
		  List<CustomerCreditDetail> list=  customerCreditDetailService.getListByPage(map);
		  pageInfo=new PageInfo<>(list);
        } catch (Exception e) {
            return new ResponseData().code(400).message("查询授信详情信息出错");
        }
        return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());
	  
  }
    

}
