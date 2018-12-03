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

import model.CustomerGreylist;

import model.ResponseData;
import service.CustomerGreyListService;
import util.JwtUtil;
import util.PoiUtil;


@RestController
@RequestMapping("customer")
public class CustomerGreyListController {
	@Autowired
	private  CustomerGreyListService  customerGreyListService;
	
	/**
     * 调用此接口新增灰名单
     * @param Customergreylist 
     * @return
     * @throws Exception
     */
	
	@RequestMapping(value = "/greylist",method = RequestMethod.POST)
	 public ResponseData addCustomergreylist( CustomerGreylist customerGreylist) {
		try {
			customerGreyListService.insertSelective(customerGreylist);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	
	 /**
	   * 调用此接口修改灰信息
	   * @param Customergreylist
	   * @return
	   * @throws Exception
	   */
	
	@RequestMapping(value = "/greylist/{id}",method = RequestMethod.PUT)
	 public ResponseData updateCustomerGreylist( CustomerGreylist customerGreylist) {
		try {
			customerGreyListService.updateByPrimaryKeySelective(customerGreylist);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	
	/**
     * 调用此接口删除灰名单信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/greylist/{id}", method = RequestMethod.DELETE)
    public ResponseData deleteCustomerGreylist(@PathVariable long id) {
        try {
        	customerGreyListService.deleteByPrimaryKey(id);
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
  @RequestMapping(value = "/greylist/list", method = RequestMethod.GET)
  public ResponseData getListByPage(@RequestParam Map<String,Object>  map,HttpServletRequest request) {
	  String token=request.getHeader("Authorization");
	  List<Long> rolelist=(List<Long>) JwtUtil.parseToken(token).get("roleIdList");
	  String orgCode=JwtUtil.parseToken(token).get("orgCode").toString();
	  String accountId=JwtUtil.parseToken(token).get("accountId").toString();
	  map.put("rolelist", rolelist);
	  map.put("orgCode", orgCode);
	  map.put("accountId", accountId);
	  PageInfo<Map<String, Object>> pageInfo ;
	  try {
		  List<Map<String, Object>> list=  customerGreyListService.getCustomerGreylistByPage(map);
		  pageInfo=new PageInfo<>(list);
        } catch (Exception e) {
            return new ResponseData().code(400).message("查询灰名单信息出错");
        }
        return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());
	  
  }
    
  
  /**
   * 调用此接口导入灰名单
   * @param
   * @return
   */
  @RequestMapping (value = "/greylist/import", method = RequestMethod.POST)
  public  ResponseData importGeryList(HttpServletRequest request) throws Exception {
      Map<String,Object> map=PoiUtil.uploadFile(request,"excel");
      Map<String,Object> returnMap=new HashMap<>();
      if((boolean) map.get("flag")) {
          List<Map<String, Object>> greyList=PoiUtil.getExcelByColumnNumsAndRowNum(map.get("path").toString()+File.separator+map.get("fileName").toString(),4,4,1);
          if(greyList!=null && greyList.size()>0) {
              return new ResponseData().success().data(customerGreyListService.insertByExcel(greyList, map.get("gridCode").toString()));
          }
  }
      returnMap.put("failCount", 0);
      returnMap.put("successCount", 0);
      return new ResponseData().success().data(returnMap);   
	
  }

}
