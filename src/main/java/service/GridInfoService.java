package service;

import java.util.List;
import java.util.Map;

import model.GridInfo;

public interface GridInfoService {
	 boolean deleteByPrimaryKey(Long id) throws Exception;
	 boolean insertSelective(GridInfo record) throws Exception;
	 boolean updateByPrimaryKeySelective(GridInfo record) throws Exception;
	 GridInfo getGridInfoByGridCode(Map<String,Object> map)throws Exception;
	
	  GridInfo selectByPrimaryKey(Long id)throws Exception;
	  List<Map<String,Object>> getGridInfoList(Map<String,Object> map)throws Exception;
}
