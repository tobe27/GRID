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

import model.GridDictionary;
import model.GridMap;
import model.ResponseData;
import service.GridMapService;

@RestController
@RequestMapping
public class GridMapController {
	@Autowired
	private GridMapService gridMapService;
	
	
	
	 /**
	   * 调用此接口新增坐标组
	   * @param List<GridMap>
	   * @return
	   * @throws Exception
	   */
	
	@RequestMapping(value = "/gridmap",method = RequestMethod.POST)
	 public ResponseData addGridMapList(@RequestBody Map<String,Object> map) {
		if(!map.containsKey("gridCode")|| !map.containsKey("gridName")|| !map.containsKey("coordinate")) {
			 return new ResponseData().fail("参数异常");
		}
		
      GridMap gridMap=new GridMap();
      gridMap.setGridCode(map.get("gridCode").toString());
      gridMap.setGridName(map.get("gridName").toString());
      gridMap.setCoordinate(map.get("coordinate").toString());
		try {
			gridMapService.insertSelective(gridMap);
			 return new ResponseData().success();
		} catch (Exception e) {
			e.printStackTrace();
			  return new ResponseData().fail(e.getMessage());
		}
		
		
	}
	

	 /**
	   * 调用此接口查询坐标组
	   * @param gridCode
	   * @return List<GridMap>
	   * @throws Exception
	   */
	@RequestMapping(value = "/gridmap/{gridCode}", method = RequestMethod.GET)
	 public ResponseData getGridMapByCode(@PathVariable("gridCode") String gridCode) {
		 try {
			 Map<String, Object> map=new HashMap<>();
			 map.put("gridCode", gridCode);
			 List<GridMap>list=gridMapService.getGridMapList(map);
	         return new ResponseData().success().data( list);
	     } catch (Exception e) {
	         return new ResponseData().code(400).message(e.getMessage());
	     }
	 }
	  
	 /**
	   * 调用此接口查询PC首页所有的网格地图坐标数据接口
	   * @param roleId,orgCode
	   * @return List<GridMap>
	   * @throws Exception
	   */
	@RequestMapping(value = "/gridmap/list/{roleid}/{orgcode}", method = RequestMethod.GET)
	 public ResponseData getGridMapByOrgCode(@PathVariable("roleid") String roleId,@PathVariable("orgcode") String orgCode) {
		 try {
			 
			 List<GridMap>list=gridMapService.getGridMapByOrgCode(roleId, orgCode);
	         return new ResponseData().success().data( list);
	     } catch (Exception e) {
	         return new ResponseData().code(400).message(e.getMessage());
	     }
	 }
	  
	

}
