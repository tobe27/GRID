package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	 public ResponseData addDirctionaryList(  @RequestBody List<GridMap> list) {
		try {
			gridMapService.batchSave(list);
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
	 public ResponseData getDictionaryByCode(@PathVariable("gridCode") String gridCode) {
		 try {
			 Map<String, Object> map=new HashMap<>();
			 map.put("gridCode", gridCode);
			 List<GridMap>list=gridMapService.getGridMapList(map);
	         return new ResponseData().success().data( list);
	     } catch (Exception e) {
	         return new ResponseData().code(400).message(e.getMessage());
	     }
	 }
	  
	

}
