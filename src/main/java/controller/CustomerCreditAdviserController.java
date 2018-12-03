package controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import model.CustomerCreditAdviser;
import model.ResponseData;
import service.CustomerCreditAdviserService;

@RestController
@RequestMapping("customer")
public class CustomerCreditAdviserController {
	@Autowired
	private CustomerCreditAdviserService customerCreditAdviserService;
	
	/**
     * 调用此接口新增咨询意见
     * @param CustomerCreditDetail 
     * @return
     * @throws Exception
     */
	
	@RequestMapping(value = "/creditadviser",method = RequestMethod.POST)
	 public ResponseData addCustomerCreditAdviser(@RequestBody CustomerCreditAdviser customerCreditAdviser)throws Exception {
		customerCreditAdviserService.insertSelective(customerCreditAdviser);
			 return new ResponseData().success();	
	}
	
	
	 /**
	   * 调用此接口修改咨询意见
	   * @param CustomerCreditAdviser
	   * @return
	   * @throws Exception
	   */
	
	@RequestMapping(value = "/creditadviser/{id}",method = RequestMethod.PUT)
	 public ResponseData updateCustomerCreditAdviser(@RequestBody CustomerCreditAdviser customerCreditAdviser)throws Exception  {
			customerCreditAdviserService.updateByPrimaryKeySelective(customerCreditAdviser);
			 return new ResponseData().success();
	}
	
	
	/**
     * 调用此接口删除咨询意见
     * @param id
     * @return
     */
    @RequestMapping(value = "/creditadviser/{id}", method = RequestMethod.DELETE)
    public ResponseData deleteCustomerCreditAdviser(@PathVariable long id) throws Exception {
        	customerCreditAdviserService.deleteByPrimaryKey(id);
            return new ResponseData().success();
    }
    /**
     * 分页和条件显示角色列表
     * @param pageNo,PageSize
     * @return
     * @throws Exception
     */
  @RequestMapping(value = "/creditadviser/list", method = RequestMethod.GET)
  public ResponseData getListByPage(@RequestParam Map<String,Object>  map)throws Exception  {
	  PageInfo<CustomerCreditAdviser> pageInfo;
		  List<CustomerCreditAdviser> list=  customerCreditAdviserService.getCustomerCreditAdviserist(map);
		  pageInfo=new PageInfo<>(list);
        return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());
	  
  }
  
  /**
   * 调用此接口查询顾问意见
   * @param id
   * @return
   * @throws Exception
   */
 @RequestMapping(value = "/creditadviser/{id}",method = RequestMethod.GET)
 public ResponseData selectById(@PathVariable long id) throws Exception   {
		 return new ResponseData().success().data(customerCreditAdviserService.selectByPrimaryKey(id));
}
	
	
}
