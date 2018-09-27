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

import model.CustomerBackToBackPreCredit;

import model.ResponseData;
import service.CustomerBlackToBackPreCreditService;

@RestController
@RequestMapping("customer")
public class CustomerBackToBackPreCreditController {
	
	@Autowired
	private CustomerBlackToBackPreCreditService customerBlackToBackPreCreditService;
	
	
	/**
     * 调用此接口新预授信信息
     * @param CustomerBackToBackPreCredit 
     * @return
     * @throws Exception
     */
	
	@RequestMapping(value = "/precredit",method = RequestMethod.POST)
	 public ResponseData addCustomerBackToBackPreCredit(@RequestBody CustomerBackToBackPreCredit customerBackToBackPreCredit) {
		try {
			customerBlackToBackPreCreditService.insertSelective(customerBackToBackPreCredit);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	
	 /**
	   * 调用此接口修改预授信信息
	   * @param Customerblacklist
	   * @return
	   * @throws Exception
	   */
	
	@RequestMapping(value = "/precredit/{id}",method = RequestMethod.PUT)
	 public ResponseData updateCustomerBackToBackPreCredit(@RequestBody CustomerBackToBackPreCredit customerBackToBackPreCredit) {
		try {
			customerBlackToBackPreCreditService.updateByPrimaryKeySelective(customerBackToBackPreCredit);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	
	/**
     * 调用此接口删除预授信信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/precredit/{id}", method = RequestMethod.DELETE)
    public ResponseData deleteCustomerBackToBackPreCredit(@PathVariable long id) {
        try {
        	customerBlackToBackPreCreditService.deleteByPrimaryKey(id);
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
  @RequestMapping(value = "/precredit/list", method = RequestMethod.GET)
  public ResponseData getListByPage(@RequestParam Map<String,Object>  map) {
	 
	  PageInfo<CustomerBackToBackPreCredit> pageInfo ;
	 
	  try {
		  List<CustomerBackToBackPreCredit> list=  customerBlackToBackPreCreditService.getListByPage(map);
		  pageInfo=new PageInfo<>(list);
        } catch (Exception e) {
            return new ResponseData().code(400).message("查询预授信信息出错");
        }
        
     
		return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());
	  
  }
	

}
