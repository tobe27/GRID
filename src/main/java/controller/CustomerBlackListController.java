package controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import model.CustomerBlackList;

import model.ResponseData;
import service.CustomerBlackListService;
import util.PoiUtil;


@RestController
@RequestMapping("customer")
public class CustomerBlackListController {
	@Autowired
	private CustomerBlackListService  customerBlackListService;
	/**
     * 调用此接口新增黑名单
     * @param Customerblacklist 
     * @return
     * @throws Exception
     */
	
	@RequestMapping(value = "/blacklist",method = RequestMethod.POST)
	 public ResponseData addCustomerblacklist( CustomerBlackList customerBlacklist) {
		try {
			customerBlackListService.insertSelective(customerBlacklist);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	
	 /**
	   * 调用此接口修改黑名单信息
	   * @param Customerblacklist
	   * @return
	   * @throws Exception
	   */
	
	@RequestMapping(value = "/blacklist/{id}",method = RequestMethod.PUT)
	 public ResponseData updateCustomerblacklist( CustomerBlackList customerBlacklist) {
		try {
			customerBlackListService.updateByPrimaryKeySelective(customerBlacklist);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	
	/**
     * 调用此接口删除黑名单信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/blacklist/{id}", method = RequestMethod.DELETE)
    public ResponseData deleteCustomerblacklist(@PathVariable long id) {
        try {
        	customerBlackListService.deleteByPrimaryKey(id);
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
  @RequestMapping(value = "/blacklist/list", method = RequestMethod.GET)
  public ResponseData getListByPage(@RequestParam Map<String,Object>  map) {
	 
	  PageInfo<Map<String,Object>> pageInfo ;
	 
	  try {
		  List<Map<String,Object>> list=  customerBlackListService.getCustomerBlackListByPage(map);
		  pageInfo=new PageInfo<>(list);
        } catch (Exception e) {
            return new ResponseData().code(400).message("查询黑名单信息出错");
        }
        
     
		return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());
	  
  }
  /**
   * 调用此接口导入黑名单
   * @param
   * @return
   */
  @RequestMapping (value = "/blacklist/list", method = RequestMethod.POST)
  public  ResponseData importBlockList(HttpServletRequest request) throws Exception {
      Map<String,Object> map=PoiUtil.uploadFile(request,"excel");
      Map<String,Object> returnMap=new HashMap<>();
      if((boolean) map.get("flag")) {
         List<Map<String, Object>> blockList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),4,4,1);
          if(blockList!=null && blockList.size()>0) {
              returnMap.put("blockList", customerBlackListService.insertByExcel(blockList, map.get("gridCode").toString()));
          }
      }
      returnMap.put("failCount", 0);
      returnMap.put("successCount", 0);
      return new ResponseData().success().data(returnMap);   
  }
    
    

}
