package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GridInfoMapper;
import dao.GridMapMapper;
import exception.MyException;
import model.GridInfo;
import model.GridMap;
import service.GridMapService;
@Service
public class GridMapServiceImpl implements GridMapService {
	@Autowired
	private GridMapMapper gridMapMapper;
	@Autowired
	private GridInfoMapper gridInfoMapper;

	
	

	 /**
	   * 调用此方法批量新增坐标组
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
			gridMapMapper.setDeleteFlag(map);
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
	
	/**
	   * 调用此方法新增坐标组
	   * @param GridMap
	   * @return
	   * @throws Exception
	   */
	
	@Override
	public boolean insertSelective(GridMap gridMap) throws Exception {
		
		try {
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
			
			
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("gridCode", gridMap.getGridCode());
			gridMapMapper.setDeleteFlag(map);
			return gridMapMapper.insertSelective(gridMap)==1;
		} catch (Exception e) {
		
			e.printStackTrace();
			throw new MyException("创建网格坐标异常");
		}
		
		
	}
	@Override
	public List<GridMap> getGridMapByOrgCode(String roleId,String orgCode) throws Exception {
		List<GridMap> returnList=new ArrayList<>();
		if(roleId==null || "".equals(roleId)||orgCode==null || "".equals(orgCode)) {
			throw new MyException("查询参数异常异常");
		}
	   Map<String,Object> map=new HashMap<>();
	   //如果是中层干部和董事长登录  可以看所有的网格地图数据
	   if("4".equals(roleId) ||"5".equals(roleId)) {
		   return gridMapMapper.getGridMapByOrgCode(map);
	   }
	   //如果时基层干部（支行长）登录只能看自己支行的网格地图
	   if("3".equals(roleId)) {
		   //查询出所有的gridCode
		   GridInfo gridInfo=new GridInfo();
		   gridInfo.setOrgCode(Long.parseLong(orgCode));
		  List<String> list=gridInfoMapper.getGridCodesByAccountIdOrOrgCode(gridInfo);
		  if(list.isEmpty()) {
			  throw new MyException("未查询到相关网格数据");
		  }
		  map.put("gridCodes", list);
		  returnList= gridMapMapper.getGridMapByOrgCode(map);
	   }
	   
		return returnList;
	}

}
