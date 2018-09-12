package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GridMapMapper;
import exception.MyException;
import model.GridMap;
import service.GridMapService;
@Service
public class GridMapServiceImpl implements GridMapService {
	@Autowired
	private GridMapMapper gridMapMapper;

	
	

	 /**
	   * 调用此方法新增坐标组
	   * @param List<GridMap>
	   * @return
	   * @throws Exception
	   */
	
	@Override
	public boolean batchSave(List<GridMap> list) throws Exception {
		if(list.isEmpty()) {
			throw new MyException("传入坐标不能为空");
		}
		for(GridMap gridMap:list) {
			if("".equals(gridMap.getGridCode())) {
				throw new MyException("网格编号不能为空");
			}
			if("".equals(gridMap.getGridName())) {
				throw new MyException("网格名称不能为空");
			}
			if("".equals(gridMap.getCoordinate())) {
				throw new MyException("网格坐标不能为空");
			}
			
			gridMap.setCreatedAt(System.currentTimeMillis());
		}
		
		//新增之前先把之前的坐标信息标记为删除状态
		
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("gridCode", list.get(0).getGridCode());
			return gridMapMapper.batchSave(list)==1;
        }  catch(Exception e) {
        	e.printStackTrace();
        	 throw new MyException("创建网格坐标异常");
        }	
	}
	 /**
	   * 调用此方法查询坐标组
	   * @param gridCode
	   * @return List<GridMap> list
	   * @throws Exception
	   */
	
	@Override
	public List<GridMap> getGridMapList(Map<String, Object> map) throws Exception {
		if(!map.containsKey("gridCode") || map.get("gridCode")==null||"".equals(map.get("gridCode"))) {
			 throw new MyException("查询网格坐标异常");
		}
		try {
		  return gridMapMapper.getGridMapList(map);
		}catch(Exception e) {
			e.printStackTrace();
			 throw new MyException("查询网格坐标异常");
		}
	}

}
