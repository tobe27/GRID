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

import model.CustomerWhitelist;
import model.ResponseData;
import service.CustomerWhiteListService;

@RestController
@RequestMapping("customer")
public class CustomerWhiteListController {
	
	@Autowired
	private CustomerWhiteListService customerWhiteListService;
	
	/**
     * 调用此接口新增白名单
     * @param CustomerWhitelist 
     * @return
     * @throws Exception
     */
	
	@RequestMapping(value = "/whitelist",method = RequestMethod.POST)
	 public ResponseData addCustomerWhitelist( CustomerWhitelist customerWhitelist) {
		try {
			customerWhiteListService.insertSelective(customerWhitelist);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	
	 /**
	   * 调用此接口修改白信息
	   * @param CustomerWhitelist
	   * @return
	   * @throws Exception
	   */
	
	@RequestMapping(value = "/whitelist/{id}",method = RequestMethod.PUT)
	 public ResponseData updateCustomerWhitelist(CustomerWhitelist customerWhitelist) {
		try {
			customerWhiteListService.updateByPrimaryKeySelective(customerWhitelist);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	
	/**
     * 调用此接口删除白名单信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/whitelist/{id}", method = RequestMethod.DELETE)
    public ResponseData deleteCustomerWhitelist(@PathVariable long id) {
        try {
        	customerWhiteListService.deleteByPrimaryKey(id);
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
  @RequestMapping(value = "/whitelist/list", method = RequestMethod.GET)
  public ResponseData getRolesByPage(@RequestParam Map<String,Object>  map) {
	 
	  PageInfo<CustomerWhitelist> pageInfo ;
	 
	  try {
		  List<CustomerWhitelist> list=  customerWhiteListService.getCustomerWhiteListByPage(map);
		  pageInfo=new PageInfo<>(list);
        } catch (Exception e) {
            return new ResponseData().code(400).message("查询白名单信息出错");
        }
        
     
		return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());
	  
  }
    
    

}
