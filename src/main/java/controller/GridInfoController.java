package controller;



import java.util.HashMap;
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

import model.GridInfo;
import model.GridRole;
import model.ResponseData;
import service.GridInfoService;

@RestController
@RequestMapping
public class GridInfoController {
	
	@Autowired
	private GridInfoService gridInfoService;
	
	 /**
	   * 调用此接口新增网格信息
	   * @param GridInfo
	   * @return
	   * @throws Exception
	   */
	
	@RequestMapping(value = "/gridinfo",method = RequestMethod.POST)
	 public ResponseData addGridInfo(@RequestBody GridInfo gridInfo) {
		try {
			gridInfoService.insertSelective(gridInfo);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	 /**
	   * 调用此接口修改网格信息
	   * @param GridInfo
	   * @return
	   * @throws Exception
	   */
	
	@RequestMapping(value = "/gridinfo/{gridCode}",method = RequestMethod.PUT)
	 public ResponseData updateGridInfo(@RequestBody GridInfo gridInfo) {
		try {
			 gridInfoService.updateByPrimaryKeySelective(gridInfo);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	 /**
	   * 调用此接口查询网格信息
	   * @param gridCode
	   * @return
	   * @throws Exception
	   */
	@RequestMapping(value = "/gridinfo/{id}",method = RequestMethod.GET)
	 public ResponseData updateGridInfo(@PathVariable long id) {
		try {	
			
			 return new ResponseData().success().data(gridInfoService.selectByPrimaryKey(id));
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	
	
	
	
	/**
     * 调用此接口删除网格信息
     * @param residentId
     * @return
     */
    @RequestMapping(value = "/gridinfo/{id}", method = RequestMethod.DELETE)
    public ResponseData deleteGridInfo(@PathVariable long id) {
        try {
        	gridInfoService.deleteByPrimaryKey(id);
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
  @RequestMapping(value = "/gridinfo/list", method = RequestMethod.GET)
  public ResponseData getListByPage(@RequestParam Map<String,Object>  map) {
	 
	  PageInfo<Map<String, Object>> pageInfo ;
	 
	  try {
		  List<Map<String,Object>> list=  gridInfoService.getGridInfoList(map);
		  pageInfo=new PageInfo<>(list);
        } catch (Exception e) {
            return new ResponseData().code(400).message("查询网格信息出错");
        }
        return new ResponseData().success().data(pageInfo.getList()).result("count", pageInfo.getTotal());
	  
  }

}
