package controller;

import java.io.File;
import java.util.ArrayList;
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

import exception.MyException;
import model.CustomerWhitelist;
import model.GridRole;
import model.ResponseData;
import service.CustomerWhiteListService;
import util.JwtUtil;
import util.PoiUtil;

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
  public ResponseData getRolesByPage(@RequestParam Map<String,Object>  map,HttpServletRequest request) {
	  String token=request.getHeader("Authorization");
	  List<Long> rolelist=(List<Long>) JwtUtil.parseToken(token).get("roleIdList");
	  String orgCode=JwtUtil.parseToken(token).get("orgCode").toString();
	  String accountId=JwtUtil.parseToken(token).get("accountId").toString();
	  map.put("rolelist", rolelist);
	  map.put("orgCode", orgCode);
	  map.put("accountId", accountId);
	  PageInfo<Map<String,Object>> pageInfo ;
	 
	  try {
		  List<Map<String,Object>> list=  customerWhiteListService.getCustomerWhiteListByPage(map);
		  pageInfo=new PageInfo<>(list);
        } catch (Exception e) {
        	e.printStackTrace();
            return new ResponseData().code(400).message("查询白名单信息出错");
        }
        
     
		return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());
	  
  }
    
  

	 /**
	   * 调用此接口实现白/灰名单互转
	   * @param idNumber,type 1为白转灰  2为灰转白
	   * @return
	   * @throws Exception
	   */
	
	@RequestMapping(value = "/whitelist/movecustomer",method = RequestMethod.POST)
	 public ResponseData moveCustomerInfo(@RequestBody Map<String,Object> map) {
		try {
			if(!map.containsKey("idNumber") || !map.containsKey("type") || !map.containsKey("reason")) {
				throw new MyException("操作参数缺失");
			}
			customerWhiteListService.moveCustomerInfo(map.get("idNumber").toString(),map.get("type").toString() , map.get("reason").toString());
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
  
	
	
	/**
     * 调用此接口导入白名单库
     * @param
     * @return
     */
    @RequestMapping (value = "/whitelist/import", method = RequestMethod.POST)
    public  ResponseData importWhiteList(HttpServletRequest request) throws Exception {
        Map<String,Object> map=PoiUtil.uploadFile(request,"excel");
        if((boolean) map.get("flag")) {
            Map<String,Object> returnMap=new HashMap<>();
            List<Map<String, Object>> whirtList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),4,4,1);
           if(whirtList!=null && whirtList.size()>0) {
                return new ResponseData().success().data(customerWhiteListService.insertByExcel(whirtList, map.get("gridCode").toString()));
            }else {
            	returnMap.put("successCount", 0);
            	returnMap.put("failCount", 0);
            }
            return new ResponseData().success().data(returnMap);
        }

        return new ResponseData().success();
    }
	
	
	
    

}
